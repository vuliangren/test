package com.welearn.dictionary.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/25.
 */
@AllArgsConstructor
public enum CacheNameConst {
    DEFAULT("default", 600L),
    TOKEN_PACKAGE("tokenPackage", 604800L),
    TOKEN("token", 86400L),
    AUTH_CHECK("authCheck", 86400L),
    ROUTE_AUTH_CACHE("routeAuthCache", 60L),
    // Antd 前端路由权限信息
    ANTD_ROUTE_INFOS("antdRouteInfos", 86400L),
    // 国家信息
    COUNTRY("country", 2592000L),
    // 公司设备 数据统计分析 25个小时缓存
    EQUIPMENTS_STATISTICS("equipmentsStatistics", 90000L),
    // 单个设备 数据统计分析 25个小时缓存
    EQUIPMENT_STATISTICS("equipmentStatistics", 90000L),
    // 工程师数据统计分析 25个小时缓存
    ENGINEER_STATISTICS("engineerStatistics", 90000L),
    // 位置信息缓存
    LOCATION("location", 21600L),
    EQUIPMENT_LOCATION("equipmentLocation", 10800L),
    ;
    // ------------------------------------------------------------------------
    @Getter
    private String name;
    @Getter
    private Long expiredSeconds;

    public static Map<String, Long> getCacheExpires(){
        Map<String, Long> cacheExpires = new HashMap<>();
        for (CacheNameConst cacheNameConst : values())
            cacheExpires.put(cacheNameConst.getName(), cacheNameConst.expiredSeconds);
        return cacheExpires;
    }
}
