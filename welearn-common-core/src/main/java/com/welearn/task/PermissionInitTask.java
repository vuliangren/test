package com.welearn.task;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.dictionary.common.PermissionTypeConst;
import com.welearn.dictionary.common.SystemRoleConst;
import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.common.Permission;
import com.welearn.entity.po.common.ReRolePermission;
import com.welearn.entity.po.common.Role;
import com.welearn.entity.qo.common.PermissionQueryCondition;
import com.welearn.entity.vo.request.common.BindPermissions;
import com.welearn.entity.vo.response.common.RoleRefPermissions;
import com.welearn.feign.common.PermissionFeignClient;
import com.welearn.feign.common.RoleFeignClient;
import com.welearn.util.PackageScannerUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description : 服务权限初始化及更新
 * 基于自定义注解生成权限, 注解在业务层代码上, 通过AOP验证权限进行拦截
 * Created by Setsuna Jin on 2018/04/14.
 *
 * 修改权限获取的实现, 优化权限认证性能
 * 基于Spring的 RequestMappingHandlerMapping 读取项目的 Controller 映射的路径
 * 将权限验证过程 放在 route 服务中进行, 优化内部服务的运行性能
 * Created by Setsuna Jin on 2018/03/31.
 */
@Slf4j
@Component
public class PermissionInitTask {

    @Autowired
    private PermissionFeignClient permissionFeignClient;

    @Autowired
    private RoleFeignClient roleFeignClient;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Value("${spring.application.name}")
    private String applicationName;

    // 防止重复执行
    private Boolean isPermissionInitTaskRun = false;

    /**
     * 延时60秒后执行
     */
    @Scheduled(initialDelay = 1000*60, fixedRate = 31536000000L)
    public void run(){
        if (this.isPermissionInitTaskRun)
            return;
        this.processUpdateServicePermissions();
        this.processAdminRoleBindAllPermissions();
        this.isPermissionInitTaskRun = true;
    }

    /**
     * 检验当前代码架构里的权限信息 和 数据库 是否一致, 如有改变对其进行 添加 启用 或 禁用
     */
    private void processUpdateServicePermissions() {
        log.info("开始执行权限初始化任务");
        List<Permission> servicePermissions =
                permissionFeignClient.selectServicePermission(applicationName).getData();

        Map<String, Permission> servicePermissionIndex = new HashMap<>();
        for (Permission permission : servicePermissions) {
            servicePermissionIndex.put(permission.getCode(), permission);
        }
        Set<String> dbPermissionNames = servicePermissionIndex.keySet();
        Map<String, Permission> currentPermissions = this.getPackageCurrentPermissions();
        Set<String> currentPermissionNames = currentPermissions.keySet();

        // 取得交集， 对未启用权限执行启用
        Set<String> intersections = new HashSet<>(dbPermissionNames);
        intersections.retainAll(currentPermissionNames);
        for (String permissionName : intersections){
            Permission permission = servicePermissionIndex.get(permissionName);
            if (!permission.getIsEnable())
                permissionFeignClient.enable(permission.getId());
        }
        log.info("启用旧权限数量:{}", intersections.size());
        // 取得数据库中权限和当前权限的差集, 对启用权限执行未启用
        Set<String> difference = new HashSet<>(dbPermissionNames);
        difference.removeAll(intersections);
        for (String permissionName : difference){
            Permission permission = servicePermissionIndex.get(permissionName);
            if (permission.getIsEnable())
                permissionFeignClient.disable(permission.getId());
        }
        log.info("禁用旧权限数量:{}", difference.size());
        // 取得当前权限和数据库中权限的差集, 对其执行添加权限操作
        difference.clear();
        difference.addAll(currentPermissionNames);
        difference.removeAll(intersections);
        for (String permissionName : difference){
            permissionFeignClient.create(currentPermissions.get(permissionName));
        }
        log.info("创建新权限数量:{}", difference.size());
    }

    /**
     * 当权限信息发生变化时, 自动更新 系统管理员 角色 关联所有权限
     */
    private void processAdminRoleBindAllPermissions(){
        Role adminRole = roleFeignClient.selectByCode(SystemRoleConst.PLATFORM_ADMIN.getCode()).getData();
        if (!Objects.isNull(adminRole)){
            log.info("开始更新 系统管理员 角色绑定的权限信息");
            PermissionQueryCondition condition = new PermissionQueryCondition();
            condition.setIsEnable(true);
            List<Permission> enablePermissions = permissionFeignClient.search(condition).getData();
            roleFeignClient.bindPermissions(new BindPermissions(
                    adminRole.getId(),
                    enablePermissions.stream().map(BasePersistant::getId).collect(Collectors.toList())
            ));
            log.info("系统当前权限总数:{}", enablePermissions.size());
        }
    }

    private Map<String, Permission> getPackageCurrentPermissions() {
        ServiceConst service = ServiceConst.get(applicationName);
        Map<String, Permission> permissionNames = new HashMap<>();

        // 2019/03/31 基于Spring的 RequestMappingHandlerMapping 读取项目的 Controller 映射的路径
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.handlerMapping.getHandlerMethods();
        handlerMethods.forEach((key, value) -> {
            // 获取 Swagger2 注解的说明
            ApiOperation apiOperation = value.getMethod().getAnnotation(ApiOperation.class);
            String description = Objects.nonNull(apiOperation) ? apiOperation.value() : null;
            // 获取控制器的实体名
            String entityName = value.getBeanType().getSimpleName().replace("Controller", "");
            // 创建 Permission 并存入 Map 中
            key.getPatternsCondition().getPatterns().forEach(pattern -> {
                // 只处理 com.welearn.controller 包下的 Controller
                if (!value.getBeanType().getName().startsWith("com.welearn.controller") || "/error".equals(pattern))
                    return;
                String code = String.format("/%s%s", service.getServiceName(), pattern);
                Permission p = new Permission();
                p.setCode(code);
                p.setName(StringUtils.isBlank(description) ? code : String.format("%s # %s", entityName, description));
                p.setService(applicationName);
                p.setType(PermissionTypeConst.SERVICE_PERMISSION.ordinal());
                p.setDescription(String.format("%s.%s", value.getBeanType().getName(), value.getMethod().getName()));
                permissionNames.put(code, p);
            });
        });

        // 2018/04/14 基于自定义的注解 PermissionCheck 读取方法名, 实现业务层的权限控制和自动创建
        /** 已废弃如下代码, 留下此部分代码仅作参考作用
        Set<MetadataReader> serviceImplClasses = PackageScannerUtil.doScan("com.welearn.service.impl");
        for (MetadataReader serviceImplClass : serviceImplClasses) {
            Class clazz = Class.forName(serviceImplClass.getClassMetadata().getClassName());
            if ("BaseServiceImpl".equals(clazz.getSimpleName()))
                continue;
            Method[] serviceMethods = clazz.getMethods();
            for (Method serviceMethod : serviceMethods) {
                if (!serviceMethod.isAnnotationPresent(PermissionCheck.class))
                    continue;
                PermissionCheck check = serviceMethod.getAnnotation(PermissionCheck.class);
                String code = String.format(
                        PermissionCheckProcessAOP.PERMISSION_NAME_TEMPLATE,
                        service.getServiceName(), clazz.getSimpleName().replace("ServiceImpl",""), serviceMethod.getName());
                String name = StringUtils.isBlank(check.name()) ? code : check.name();
                String description = StringUtils.isBlank(check.description()) ? "" : check.description();

                Permission p = new Permission();
                p.setCode(code);
                p.setName(name);
                p.setService(applicationName);
                p.setType(PermissionTypeConst.SERVICE_PERMISSION.ordinal());
                p.setDescription(description);

                permissionNames.put(code, p);
            }
        }*/

        return permissionNames;
    }
}
