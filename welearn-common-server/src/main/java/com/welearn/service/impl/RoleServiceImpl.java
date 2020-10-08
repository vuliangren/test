package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.controller.RoleController;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.common.*;
import com.welearn.entity.qo.common.ReUserRoleQueryCondition;
import com.welearn.entity.qo.common.RoleQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.error.exception.DbOperationFailedException;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.mapper.*;
import com.welearn.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description :
 * TODO: 验证角色的派生关系, 防止越级分配权限, 给权限添加关联关系(构建权限树, 定点是根权限, 每个权限可有一个或多个父节点)
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,RoleQueryCondition,RoleMapper> implements RoleService {

    // TODO: 系统数据初始化后应该检查该字段是否和数据库符合
    private static final String AdminRoleId = "040d7d4216c939124107a1c519924b6f85f1";

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ReUserRoleMapper reUserRoleMapper;
    @Autowired
    private ReRolePermissionMapper reRolePermissionMapper;
    @Autowired
    private CacheCleanService cacheCleanService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReUserRoleService reUserRoleService;
    @Autowired
    private ReRolePermissionService reRolePermissionService;
    @Autowired
    private ReRoutePermissionMapper reRoutePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    RoleMapper getMapper() {
        return roleMapper;
    }

    /**
     * 获取用户拥有的角色
     *
     * @param userId 用户
     * @return 角色列表
     */
    @Override
    public List<Role> userRoles(String userId) {
        return roleMapper.selectByUserId(userId);
    }

    /**
     * 角色权限的绑定及修改
     *
     * @param roleId        角色id
     * @param permissionIds 权限id列表
     */
    @Override
    public void bindPermissions(String roleId, List<String> permissionIds) throws DbOperationFailedException {
        this.checkIsAdminRoleId(roleId);
        Set<String> bindedPermissionIds = reRolePermissionMapper.selectByRoleId(roleId).stream().map(BasePersistant::getId).collect(Collectors.toSet());
        Set<String> bindingPermissionIds = new HashSet<>(permissionIds);
        // 新增角色权限关联
        bindingPermissionIds.removeAll(bindedPermissionIds);
        bindingPermissionIds.forEach(permissionId -> {
            reRolePermissionService.create(new ReRolePermission(roleId, permissionId));
        });
        // 删除角色权限关联
        bindedPermissionIds.removeAll(permissionIds);
        bindedPermissionIds.forEach(permissionId -> {
            reRolePermissionService.delete(reRolePermissionMapper.selectByRoleIdAndPermissionId(roleId, permissionId).getId());
        });
    }

    /**
     * 角色批量绑定权限, 将 角色 关联的 客户端路由 绑定的权限 和 角色自身现有权限, 进行合并去重后更新角色权限信息
     * 注意: 仅做增量计算, 不会进行删除操作, 修改客户端路由绑定的权限后 需要手动删除角色对应的权限
     * @param roleCode     角色编码
     * @param clientType 客户端类型
     */
    @Override
    public void bindRoutePermissions(String roleCode, Integer clientType) {
        Role role = this.selectByCode(roleCode);
        if (Objects.isNull(role))
            throw new BusinessVerifyFailedException("roleCode 非法");
        this.checkIsAdminRoleId(role.getId());
        Set<String> rolePermissionCodes = reRolePermissionMapper.selectByRoleId(role.getId()).stream().map(Permission::getCode).collect(Collectors.toSet());
        Set<String> routePermissionCodes = new HashSet<>(reRoutePermissionMapper.selectPermissionCodeByRoleCode(roleCode, clientType));
        // 得到 要新建的权限编码
        routePermissionCodes.removeAll(rolePermissionCodes);
        // 添加 角色权限关联信息
        routePermissionCodes.forEach( permissionCode -> {
            Permission permission = permissionMapper.selectByCode(permissionCode);
            if (Objects.nonNull(permission) && permission.getIsEnable()){
                reRolePermissionService.create(new ReRolePermission(role.getId(), permission.getId()));
            }
        });
    }

    /**
     * 角色解绑权限 无关联会直接删除
     *
     * @param roleId       角色
     * @param permissionId 权限
     */
    @Override
    public void unbindPermission(String roleId, String permissionId) throws BusinessVerifyFailedException {
        this.checkIsAdminRoleId(roleId);
        ReRolePermission ref = reRolePermissionMapper.selectByRoleIdAndPermissionId(roleId, permissionId);
        if (Objects.isNull(ref))
            throw new BusinessVerifyFailedException(String.format("can not found ReRolePermission " +
                    "where roleId:%s and permissionId:%s", roleId, permissionId));
        else
            reRolePermissionService.delete(ref.getId());
    }

    /**
     * 用户绑定角色列表
     *
     * @param userId  用户id
     * @param roleIds 角色id
     */
    @Override
    public void bindUser(String userId, List<String> roleIds) {
        this.checkIsAdminRoleId(roleIds);
        User user = userMapper.selectByPK(userId);
        if (Objects.isNull(user))
            return;
        List<ReUserRole> rurs = reUserRoleMapper.selectByUserId(userId);
        Set<String> bindedRoleIds = rurs.stream().map(ReUserRole::getRoleId).collect(Collectors.toSet());
        // 新增用户角色关联
        Set<String> bindingRoleIds = new HashSet<>(roleIds);
        bindingRoleIds.removeAll(bindedRoleIds);
        bindingRoleIds.forEach(roleId -> {
            reUserRoleService.create(new ReUserRole(userId,roleId));
        });
        // 禁用用户角色关联
        bindedRoleIds.removeAll(roleIds);
        bindedRoleIds.forEach(roleId -> {
            rurs.forEach(rur -> {
                if (rur.getRoleId().equals(roleId))
                    reUserRoleService.disable(rur.getId());
            });
        });
        cacheCleanService.deleteAuthCheckCache(user);
    }

    /**
     * 用户解绑角色
     *
     * @param userId  用户
     * @param roleId  角色
     */
    @Override
    public void unbindUser(String userId, String roleId) throws BusinessVerifyFailedException {
        this.checkIsAdminRoleId(roleId);
        User user = userMapper.selectByPK(userId);
        ReUserRole reUserRole = reUserRoleMapper.selectByUserIdAndRoleId(userId,roleId);
        if (Objects.isNull(user) || Objects.isNull(reUserRole))
            throw new BusinessVerifyFailedException(String.format("can not found ReUserRole " +
                "where userId:%s and roleId:%s", userId, roleId));
        else
            reUserRoleService.disable(reUserRole.getId());
        cacheCleanService.deleteAuthCheckCache(user);
    }

    /**
     * 根据角色的 code 获取 角色
     *
     * @param code 角色编码
     * @return 角色
     */
    @Override
    public Role selectByCode(String code) {
        return roleMapper.selectByCode(code);
    }

    /**
     * 根据角色编码给用户绑定一个新角色
     *
     * @param userId 用户
     * @param code   角色编码
     */
    @Override
    public void bindUserWithCode(String userId, String code) {
        User user = userMapper.selectByPK(userId);
        Role role = this.selectByCode(code);
        this.checkIsAdminRoleId(role.getId());
        ReUserRoleQueryCondition condition = new ReUserRoleQueryCondition();
        condition.setUserId(userId);
        condition.setRoleId(role.getId());
        condition.setIsEnable(true);
        List<ReUserRole> reUserRoles = reUserRoleService.search(condition);
        if (Objects.nonNull(user) && Objects.nonNull(reUserRoles) && reUserRoles.size() == 0) {
            reUserRoleService.create(new ReUserRole(userId,role.getId()));
            cacheCleanService.deleteAuthCheckCache(user);
        } else {
            log.error("User bind role error, the role may have binded");
        }
    }

    /**
     * 根据角色编码给用户解绑一个新角色
     *
     * @param userId 用户id
     * @param code   角色编码
     */
    @Override
    public void unbindUserWithCode(String userId, String code) {
        User user = userMapper.selectByPK(userId);
        Role role = this.selectByCode(code);
        this.checkIsAdminRoleId(role.getId());
        ReUserRoleQueryCondition condition = new ReUserRoleQueryCondition();
        condition.setUserId(userId);
        condition.setRoleId(role.getId());
        condition.setIsEnable(true);
        List<ReUserRole> reUserRoles = reUserRoleService.search(condition);
        if (Objects.nonNull(user) && Objects.nonNull(reUserRoles) && reUserRoles.size() == 1) {
            reUserRoleService.disable(condition);
            cacheCleanService.deleteAuthCheckCache(user);
        } else {
            log.error("User unbind role error, the role may have unbinded");
        }
    }

    private void checkIsAdminRoleId(String roleId) {
        if (AdminRoleId.equals(roleId))
            throw new BusinessVerifyFailedException("操作非法");
    }

    private void checkIsAdminRoleId(List<String> roleIds) {
        if (roleIds.indexOf(AdminRoleId) != -1)
            throw new BusinessVerifyFailedException("操作非法");
    }
}
