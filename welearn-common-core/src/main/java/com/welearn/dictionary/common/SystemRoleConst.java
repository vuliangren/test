package com.welearn.dictionary.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.welearn.dictionary.common.CompanyTypeConst.*;

/**
 * Description : 系统默认角色常量
 * Created by Setsuna Jin on 2019/5/6.
 */
@Getter
@AllArgsConstructor
public enum SystemRoleConst {
    // 平台角色 ----------------------------------
    PLATFORM_ADMIN(PLATFORM, "platform_admin", "系统管理员", ""),
    PLATFORM_ENGINEER(PLATFORM, "platform_engineer", "工程师", ""),
    PLATFORM_DEPARTMENT_MANAGER(PLATFORM, "platform_department_manager", "部门主管", ""),
    PLATFORM_DEPARTMENT_EMPLOYEE(PLATFORM, "platform_department_employee", "部门员工", ""),
    // 医院角色 ----------------------------------
    HOSPITAL_ADMIN(HOSPITAL, "hospital_admin", "系统管理员", ""),
    HOSPITAL_REPAIR_MANAGER(HOSPITAL, "hospital_repair_manager", "维修主管", ""),
    HOSPITAL_FINANCE_MANAGER(HOSPITAL, "hospital_finance_manager", "财务主管", ""),
    HOSPITAL_PROCUREMENT_MANAGER(HOSPITAL, "hospital_procurement_manager", "采购主管", ""),
    HOSPITAL_MAINTAIN_MANAGER(HOSPITAL, "hospital_maintain_manager", "维护主管", ""),
    HOSPITAL_DEPARTMENT_MANAGER(HOSPITAL, "hospital_department_manager", "科室主管", ""),
    HOSPITAL_DEPARTMENT_EMPLOYEE(HOSPITAL, "hospital_department_employee", "科室员工", ""),
    HOSPITAL_REPAIR_REPORTER(HOSPITAL, "hospital_repair_reporter", "报修人员", ""),
    // 厂商角色 ----------------------------------
    SUPPLIER_ADMIN(SUPPLIER, "supplier_admin", "系统管理员", ""),
    SUPPLIER_DEPARTMENT_MANAGER(SUPPLIER, "supplier_department_manager", "部门主管", ""),
    SUPPLIER_DEPARTMENT_EMPLOYEE(SUPPLIER, "supplier_department_employee", "部门员工", ""),
    ;
    private CompanyTypeConst companyType;
    private String code;
    private String name;
    // TODO: 完善描述内容
    private String description;
}
