package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.dto.common.RouteInfo;
import com.welearn.generator.ControllerGenerator;
import com.welearn.service.ReRoutePermissionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.ReRouteRole;
import com.welearn.entity.qo.common.ReRouteRoleQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.ReRouteRoleService;

import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/reRouteRole")
@Api(value = "reRouteRole 接口")
public class ReRouteRoleController extends BaseController<ReRouteRole, ReRouteRoleQueryCondition, ReRouteRoleService>{

    @RequestMapping(value = "/routeInfos")
    @ApiOperation(value = "获取系统路由详情信息(权限列表)", httpMethod = "POST")
    public CommonResponse<Map<String, RouteInfo>> routeInfos() {
        Map<String, RouteInfo> result = service.routeInfos();
        return new CommonResponse<>(result);
    }
}