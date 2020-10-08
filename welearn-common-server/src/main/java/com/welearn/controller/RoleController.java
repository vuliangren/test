package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.vo.request.common.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.Role;
import com.welearn.entity.qo.common.RoleQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.error.exception.DbOperationFailedException;
import com.welearn.service.RoleService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/role")
public class RoleController extends BaseController<Role, RoleQueryCondition, RoleService>{
    @RequestMapping(value = "/unbindUserWithCode")
    @ApiOperation(value = "unbindUserWithCode", httpMethod = "POST")
    public CommonResponse unbindUserWithCode(@RequestBody UnbindUserWithCode unbindUserWithCode)  {
        service.unbindUserWithCode(unbindUserWithCode.getUserId(), unbindUserWithCode.getCode());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/userRoles")
    @ApiOperation(value = "userRoles", httpMethod = "POST")
    public CommonResponse<List<Role>> userRoles(@RequestBody String string)  {
        List<Role> result = service.userRoles(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectByCode")
    @ApiOperation(value = "selectByCode", httpMethod = "POST")
    public CommonResponse<Role> selectByCode(@RequestBody String string)  {
        Role result = service.selectByCode(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/bindPermissions")
    @ApiOperation(value = "bindPermissions", httpMethod = "POST")
    public CommonResponse bindPermissions(@RequestBody BindPermissions bindPermissions) throws DbOperationFailedException {
        service.bindPermissions(bindPermissions.getRoleId(), bindPermissions.getPermissionIds());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/unbindPermission")
    @ApiOperation(value = "unbindPermission", httpMethod = "POST")
    public CommonResponse unbindPermission(@RequestBody UnbindPermission unbindPermission) throws BusinessVerifyFailedException, DbOperationFailedException {
        service.unbindPermission(unbindPermission.getRoleId(), unbindPermission.getPermissionId());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/bindUser")
    @ApiOperation(value = "bindUser", httpMethod = "POST")
    public CommonResponse bindUser(@RequestBody BindUser bindUser)  {
        service.bindUser(bindUser.getUserId(), bindUser.getRoleIds());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/unbindUser")
    @ApiOperation(value = "unbindUser", httpMethod = "POST")
    public CommonResponse unbindUser(@RequestBody UnbindUser unbindUser) throws BusinessVerifyFailedException {
        service.unbindUser(unbindUser.getUserId(), unbindUser.getRoleId());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/bindUserWithCode")
    @ApiOperation(value = "bindUserWithCode", httpMethod = "POST")
    public CommonResponse bindUserWithCode(@RequestBody BindUserWithCode bindUserWithCode)  {
        service.bindUserWithCode(bindUserWithCode.getUserId(), bindUserWithCode.getCode());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/bindRoutePermissions")
    @ApiOperation(value = "bindRoutePermissions", httpMethod = "POST")
    public CommonResponse bindRoutePermissions(@RequestBody BindRoutePermissions bindRoutePermissions)  {
        service.bindRoutePermissions(bindRoutePermissions.getRoleCode(), bindRoutePermissions.getClientType());
        return CommonResponse.getSuccessResponse();
    }
}