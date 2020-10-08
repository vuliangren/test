package com.welearn.dictionary.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.welearn.dictionary.common.CompanyTypeConst.*;

/**
 * Description : 职位常量
 * Created by Setsuna Jin on 2018/9/13.
 */
@Getter
@AllArgsConstructor
public enum PositionConst {
    // 供应商  ----------------------------------------------------------------------------------------------------------
    PLATFORM_SUPERVISOR(PLATFORM, "部门主管", "platform_supervisor", "负责管理整个平台部门", 1, false),
    PLATFORM_EMPLOYEE(PLATFORM, "部门员工", "platform_base_employee", "平台部门里的一般员工", 0, true),
    // 医院 ------------------------------------------------------------------------------------------------------------
    HOSPITAL_SUPERVISOR(HOSPITAL, "科室主管", "hospital_supervisor", "负责管理整个医院科室", 1, false),
    HOSPITAL_EMPLOYEE(HOSPITAL, "科室员工", "hospital_base_employee", "医院科室里的一般员工", 0, true),
    // 平台 -------------------------------------------------------------------------------------------------------------
    SUPPLIER_SUPERVISOR(SUPPLIER, "部门主管", "supplier_supervisor", "负责管理供应商创建的部门", 1, false),
    SUPPLIER_EMPLOYEE(SUPPLIER, "部门员工", "supplier_base_employee", "供应商创建部门的一般员工", 0, true),
    ;

    private CompanyTypeConst companyType;
    private String name;
    private String code;
    private String description;
    // 部门职位数量限制 0-不限制
    private Integer limitCount;
    // 是否是部门默认职位
    private Boolean isDefault;

    public static List<PositionConst> getCompanyDefaultPositons(Integer companyType){
        return Arrays.stream(PositionConst.values()).filter(
                positionConst -> positionConst.getCompanyType().ordinal() == companyType && positionConst.getIsDefault()
        ).collect(Collectors.toList());
    }

    public static PositionConst getCompanyDefaultBasePositon(Integer companyType){
        return Arrays.stream(PositionConst.values()).filter(
                positionConst -> positionConst.getCompanyType().ordinal() == companyType && positionConst.getIsDefault() && positionConst.getCode().contains("_base_employee")
        ).findFirst().get();
    }
}
