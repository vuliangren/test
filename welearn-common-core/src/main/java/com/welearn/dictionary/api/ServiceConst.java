package com.welearn.dictionary.api;

import com.alibaba.fastjson.JSON;
import com.welearn.error.exception.ProgramCheckFailedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务类型
 * TODO:对于新加入的服务需先在此添加枚举
 */
@Getter
@AllArgsConstructor
public enum ServiceConst {
    // UUID 按十六进制编号 --------------------------------------

    CONFIG_SERVER("config", "配置服务", 0),
    ROUTE_SERVER("route", "路由服务", 1),
    DISCOVER_SERVER("discover", "服务发现", 2),
    AUTHORIZATION_SERVER( "authorization", "授权认证服务", 3),
    COMMON_SERVER( "common", "基础核心服务", 4),
    NOTIFY_SERVER("notify", "消息通知服务", 5),
    STORAGE_SERVER("storage", "文件存储服务", 6),
    APPLY_SERVER("apply", "申请审批服务", 7),
    EQUIPMENT_SERVER("equipment", "设备管理服务", 8),
    PROCUREMENT_SERVER("procurement", "采购管理服务", 9),
    FINANCE_SERVER("finance", "资产管理服务", 10),
    ALINK_SERVER("alink", "阿里物联云服务", 11),
    PAYMENT_SERVER("payment", "支付管理服务", 12),
    SUPPLIER_SERVER("supplier", "供应管理服务", 13),
    EXPORT_SERVER("export", "数据导出服务", 14),
    ;
    // --------------------------------------------------------
    private String serviceName;
    private String description;
    private Integer uuid;

    public String getServiceApplicationName(){
        return String.format("welearn-%s-server", serviceName);
    }

    public static ServiceConst get(String applicationName){
        for (ServiceConst service : ServiceConst.values()) {
            if (service.getServiceApplicationName().equals(applicationName))
                return service;
        }
        throw new ProgramCheckFailedException("applicationName is illegal");
    }

    public static ServiceConst getByServiceName(String serviceName){
        for (ServiceConst service : ServiceConst.values()) {
            if (service.getServiceName().equals(serviceName))
                return service;
        }
        throw new ProgramCheckFailedException("serviceName is illegal");
    }

    public static Map<String, String> getServiceMap(){
        HashMap<String, String> serviceMap = new HashMap<>();
        for (ServiceConst service : ServiceConst.values()) {
            serviceMap.put(service.getServiceApplicationName(), service.getDescription());
        }
        return serviceMap;
    }
}