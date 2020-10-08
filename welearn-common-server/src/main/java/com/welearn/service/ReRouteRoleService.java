package com.welearn.service;

import com.welearn.entity.dto.common.RouteInfo;
import com.welearn.entity.po.common.ReRouteRole;
import com.welearn.entity.qo.common.ReRouteRoleQueryCondition;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

/**
 * Description : ReRouteRoleService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReRouteRoleService extends BaseService<ReRouteRole, ReRouteRoleQueryCondition>{

    /**
     * 获取系统路由详情信息
     * @return Map<String, RouteRoleItem>
     */
    Map<String/* path **/, RouteInfo> routeInfos();
}