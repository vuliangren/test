package com.welearn.dictionary.common;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description : 企业 默认参数配置项
 * Created by Setsuna Jin on 2018/10/31.
 */
@Getter
@AllArgsConstructor
public enum CompanyConfigConst {

    // ---------------------------平台配置项----------------------------------

    // ---------------------------医院配置项----------------------------------
    // 年度设备计划 科室申请 开始时间
    EYPDAS("EYPDAS","year-02-01 00:00:00", CompanyTypeConst.HOSPITAL),
    // 年度设备计划 科室申请 结束时间
    EYPDAE("EYPDAE","year-11-01 00:00:00", CompanyTypeConst.HOSPITAL),
    // 年度设备计划 高价设备价格标准 (超过时采购申请需附带计划论证信息)
    EYP_HIGH_PRICE_EQUIPMENT("EYP_HIGH_PRICE_EQUIPMENT",50000, CompanyTypeConst.HOSPITAL),
    // 廉价配件默认起始价格
    PRICE_BARGAIN_SPARE_PART("PRICE_BARGAIN_SPARE_PART",0, CompanyTypeConst.HOSPITAL),
    // 低价配件默认起始价格
    PRICE_LOW_SPARE_PART("PRICE_LOW_SPARE_PART",500, CompanyTypeConst.HOSPITAL),
    // 高价配件默认起始价格
    PRICE_HIGH_SPARE_PART("PRICE_HIGH_SPARE_PART",5000, CompanyTypeConst.HOSPITAL),
    // 超额配件默认起始价格
    PRICE_OVER_SPARE_PART("PRICE_OVER_SPARE_PART",50000, CompanyTypeConst.HOSPITAL),
    // 是否使用默认的库存管理系统
    USE_DEFAULT_STOCK("USE_DEFAULT_STOCK",false, CompanyTypeConst.HOSPITAL),
    // 是否使用默认的资产管理系统
    USE_DEFAULT_ASSET("USE_DEFAULT_ASSET",false, CompanyTypeConst.HOSPITAL),
    // 是否使用默认的发票管理系统
    USE_DEFAULT_INVOICE("USE_DEFAULT_INVOICE",false, CompanyTypeConst.HOSPITAL),
    // 是否使用默认的配件库存系统
    USE_DEFAULT_SPARE_PART_STOCK("USE_DEFAULT_SPARE_PART_STOCK",false, CompanyTypeConst.HOSPITAL),
    // 是否使用默认的配件采购系统
    USE_DEFAULT_SPARE_PART_PROCUREMENT("USE_DEFAULT_SPARE_PART_PROCUREMENT",false, CompanyTypeConst.HOSPITAL),
    // 科室间设备借用申请审批流程
    EQUIPMENT_BORROW_APPROVAL_TYPE("EQUIPMENT_BORROW_APPROVAL_TYPE", 0, CompanyTypeConst.HOSPITAL),
    // 设备借用固定资产折旧费用倍率
    EQUIPMENT_BORROW_MULTIPLYING_POWER("EQUIPMENT_BORROW_MULTIPLYING_POWER", 1.0, CompanyTypeConst.HOSPITAL);
    // --------------------------供应商配置项---------------------------------

    ;
    @Getter
    private String key;
    private Object value;
    private CompanyTypeConst type;

    /**
     * 获取默认配置项
     * @return Map<String, String>
     */
    public static Map<String, Object> getDefaultConfig(CompanyTypeConst type){
        return Arrays.stream(CompanyConfigConst.values())
                .filter(c -> c.type == type)
                .collect(Collectors.toMap(c -> c.key, c -> c.value));
    }

    /**
     * 读取企业配置项
     * @return Map<String, String>
     */
    public static Map<String, Object> parseConfigJson(String configJson, CompanyTypeConst type){
        if (StringUtils.isBlank(configJson))
            return getDefaultConfig(type);
        Map<String, Object> defaultConfig = getDefaultConfig(type);
        Map<String, Object> companyConfig = JSON.parseObject(configJson);
        for (String key : defaultConfig.keySet()) {
            Object value = companyConfig.get(key);
            if (Objects.nonNull(value))
                defaultConfig.put(key, value);
        }
        return defaultConfig;
    }
}
