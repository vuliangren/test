package com.welearn.task;

import com.welearn.dictionary.common.*;
import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.common.*;
import com.welearn.mapper.PositionMapper;
import com.welearn.mapper.RoleMapper;
import com.welearn.mapper.UserMapper;
import com.welearn.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description : 系统初始化检测任务
 * Created by Setsuna Jin on 2019/5/6.
 */
@Slf4j
@Component
public class SystemInitTask {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private DepartmentService departmentService;

    @Transactional
    @Scheduled(initialDelay = 1000*30, fixedRate = 31536000000L)
    public void run(){
        int roleCount = roleMapper.countAll();
        int userCount = userMapper.countAll();

//        if (roleCount != 0 || userCount != 0) {
        if (userCount != 0) {
            log.info("系统初始化检测: FALSE");
            return;
        } else {
            log.info("系统初始化检测: TRUE!");
        }
        // 判断为需要初始化系统
        User user = new User();
        user.setId("040f6af72c8abe6c454cbdc3cda2e2245919");
        user.setType(UserTypeConst.ANY.ordinal());
        user.setName("靳锐阳");
        user.setEmail("jinruiyang@shebei120.onaliyun.com");
        user.setTelephone("13250062013");
        user.setSex(UserSexConst.MAN.ordinal());

        Company company = new Company();
        company.setId("040124fe68a95bfc436e8e78fd23fba50585");
        company.setType(CompanyTypeConst.PLATFORM.ordinal());
        company.setLogoUrl("[]");
        company.setName("阜阳市锐阳医疗设备技术有限公司");
        company.setDescription("软件平台服务商");
        company.setPhone("15056851868");
        company.setCreatorId(user.getId());
        company.setAdminId(user.getId());
        companyService.create(company);
        log.info(" - 系统初始化: 创建平台公司完成");

        Department department = new Department();
        department.setCreatorId(user.getId());
        department.setAdminId(user.getId());
        department.setCompanyId(company.getId());
        department.setName("技术开发部");
        department.setDescription("负责锐阳设备质量管理系统的设计与实现");
        departmentService.create(department);
        log.info(" - 系统初始化: 创建平台部门完成");

//        List<Role> roles = new ArrayList<>();
//        Arrays.stream(SystemRoleConst.values()).forEach(role -> {
//            roles.add(roleService.create(new Role(role.getName(), role.getName(), user.getId(), role.getCode(),
//                    role.getCompanyType().ordinal())));
//        });
//        log.info(" - 系统初始化: 创建系统默认角色完成");
//
//        List<Position> positions = new ArrayList<>();
//        Arrays.stream(PositionConst.values()).forEach(position -> {
//            Position p = new Position();
//            p.setVisitorCompanyType(position.getCompanyType().ordinal());
//            p.setType(PositionTypeConst.SYSTEM_DEFINED_POSITION_DEPARTMENT.ordinal());
//            p.setName(position.getName());
//            p.setCode(position.getCode());
//            p.setDescription(position.getDescription());
//            positions.add(positionService.create(p));
//        });
//        log.info(" - 系统初始化: 创建系统默认职位完成");

        List<Role> roles = roleMapper.selectAll();
        List<Position> positions = positionMapper.selectAll();

        user.setCompanyId(company.getId());
        user.setDepartmentId(department.getId());
        userService.entry(
                user,
                roles.stream().filter(r -> r.getCode().equals(SystemRoleConst.PLATFORM_ADMIN.getCode()) || r.getCode().equals(SystemRoleConst.PLATFORM_DEPARTMENT_MANAGER.getCode())).map(BasePersistant::getId).collect(Collectors.toList()),
                positions.stream().filter(p -> p.getCode().equals(PositionConst.PLATFORM_SUPERVISOR.getCode())).map(BasePersistant::getId).collect(Collectors.toList()),
                user
        );
        log.info(" - 系统初始化: 创建系统管理员完成");
    }

}
