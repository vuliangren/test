package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.ReRoutePermission;
import com.welearn.entity.qo.common.ReRoutePermissionQueryCondition;
import com.welearn.entity.vo.request.common.BatchCreateAndUpdate;
import com.welearn.entity.vo.request.common.SelectPermissionCodeByRoleCode;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.ReRoutePermissionService;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/reRoutePermission")
@Api(value = "reRoutePermission 接口")
public class ReRoutePermissionController extends BaseController<ReRoutePermission, ReRoutePermissionQueryCondition, ReRoutePermissionService>{
    @RequestMapping(value = "/selectPermissionCodeByRoleCode")
    @ApiOperation(value = "selectPermissionCodeByRoleCode", httpMethod = "POST")
    public CommonResponse<List<String>> selectPermissionCodeByRoleCode(@RequestBody SelectPermissionCodeByRoleCode selectPermissionCodeByRoleCode)  {
        List<String> result = service.selectPermissionCodeByRoleCode(selectPermissionCodeByRoleCode.getRoleCode(), selectPermissionCodeByRoleCode.getClientType());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/batchCreateAndUpdate")
    @ApiOperation(value = "batchCreateAndUpdate", httpMethod = "POST")
    public CommonResponse batchCreateAndUpdate(@RequestBody BatchCreateAndUpdate batchCreateAndUpdate)  {
        service.batchCreateAndUpdate(batchCreateAndUpdate.getClientType(), batchCreateAndUpdate.getPath(), batchCreateAndUpdate.getPermissionCodes());
        return CommonResponse.getSuccessResponse();
    }

}