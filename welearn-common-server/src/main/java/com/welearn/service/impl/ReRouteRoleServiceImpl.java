package com.welearn.service.impl;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.dto.common.RouteInfo;
import com.welearn.entity.dto.common.RouteRole;
import com.welearn.entity.po.common.ReRouteHelp;
import com.welearn.entity.po.common.ReRouteRole;
import com.welearn.entity.qo.common.ReRouteHelpQueryCondition;
import com.welearn.entity.qo.common.ReRouteRoleQueryCondition;
import com.welearn.mapper.ReRouteHelpMapper;
import com.welearn.mapper.ReRouteRoleMapper;
import com.welearn.service.ReRouteRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description : ReRouteRoleService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class ReRouteRoleServiceImpl extends BaseServiceImpl<ReRouteRole,ReRouteRoleQueryCondition,ReRouteRoleMapper>
        implements ReRouteRoleService{
    
    @Autowired
    private ReRouteRoleMapper reRouteRoleMapper;

    @Autowired
    private ReRouteHelpMapper reRouteHelpMapper;

    @Override
    ReRouteRoleMapper getMapper() {
        return reRouteRoleMapper;
    }

    /**
     * 获取系统路由角色的关系
     * 注意: 不能调用 service 层方法, 本方法无须认证信息即可访问
     * @return Map<String, RouteRoleItem>
     */
    @Override @Cacheable(value = "antdRouteInfos", key = "'antdRouteInfos'")
    public Map<String, RouteInfo> routeInfos() {
        // 查询路由角色关系
        List<RouteRole> routeRoles = reRouteRoleMapper.selectAllDistinctPathAndRole(ClientTypeConst.WEB.ordinal());
        Map<String, RouteInfo> routeInfos = new HashMap<>();
        for (RouteRole routeRole : routeRoles) {
            RouteInfo routeInfo = routeInfos.get(routeRole.getPath());
            if (Objects.isNull(routeInfo)){
                routeInfo = new RouteInfo();
                routeInfo.setAuthority(new ArrayList<>());
                routeInfos.put(routeRole.getPath(), routeInfo);
            }
            routeInfo.getAuthority().add(routeRole.getRole());
        }
        // TODO: 此部分数据暂时对前端无用, 没必要加载时传给前端
        // 查询路由帮助信息
//        ReRouteHelpQueryCondition condition = new ReRouteHelpQueryCondition();
//        condition.setIsEnable(true);
//        condition.setClientType(ClientTypeConst.WEB.ordinal());
//        List<ReRouteHelp> reRouteHelps = reRouteHelpMapper.selectByCondition(condition);
//        for (ReRouteHelp reRouteHelp : reRouteHelps) {
//            RouteInfo routeInfo = routeInfos.get(reRouteHelp.getPath());
//            if (Objects.isNull(routeInfo)){
//                routeInfo = new RouteInfo();
//                routeInfos.put(reRouteHelp.getPath(), routeInfo);
//            }
//            routeInfo.setPageHelp(reRouteHelp);
//        }
        return routeInfos;
    }
}
