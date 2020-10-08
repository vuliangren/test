package com.welearn.dictionary.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static com.welearn.dictionary.common.CompanyTypeConst.*;

/**
 * Description : 部门tag
 * Created by Setsuna Jin on 2018/10/31.
 */
@Getter
@AllArgsConstructor
public enum DepartmentTagConst {
    HOSPITAL_DEFAULT(HOSPITAL, "hospital_default", "普通科室", 0),
    EQUIPMENT_DIVISION(HOSPITAL, "equipment_division", "设备科", 1),
    EQUIPMENT_COMMITTEE(HOSPITAL, "equipment_committee", "装备委员会", 1),

    SUPPLIER_DEFAULT(SUPPLIER, "supplier_default", "一般部门", 0),
    SALES_DIVISION(SUPPLIER, "sales_division", "销售部", 0),


    PLATFORM_DEFAULT(PLATFORM, "platform_default", "一般部门", 0),

    ;
    private CompanyTypeConst type;
    private String name;
    private String description;
    private int limit;

    /**
     * 根据类型查找
     * @param type CompanyTypeConst
     * @return Map<String, String>
     */
    public static Map<String, String> searchByType(CompanyTypeConst type){
        Map<String, String> tags = new HashMap<>();
        Arrays.stream(values()).filter(tag -> tag.type.equals(type)).forEach( tag -> {
            tags.put(tag.getName(), tag.getDescription());
        });
        return tags;
    }

    public static Map<String, DepartmentTagConst> search(CompanyTypeConst type){
        Map<String, DepartmentTagConst> tags = new HashMap<>();
        Arrays.stream(values()).filter(tag -> tag.type.equals(type)).forEach( tag -> {
            tags.put(tag.getName(), tag);
        });
        return tags;
    }

    /**
     * 根据名称查找
     * @param name tagName
     * @return DepartmentTagConst
     */
    public static DepartmentTagConst searchByName(String name){
        List<DepartmentTagConst> tags = Arrays.stream(values()).filter(tag -> tag.name.equals(name)).collect(Collectors.toList());
        if (tags.isEmpty())
            return null;
        else return tags.get(0);
    }
}
