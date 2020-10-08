package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.Permission;
import com.welearn.entity.qo.common.PermissionQueryCondition;
import com.welearn.entity.vo.request.common.CheckPermission;
import com.welearn.entity.vo.request.common.CheckPermissionCode;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.common.RoleRefPermissions;
import com.welearn.service.PermissionService;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/permission")
public class PermissionController extends BaseController<Permission, PermissionQueryCondition, PermissionService>{
    @RequestMapping(value = "/checkPermission")
    @ApiOperation(value = "checkPermission", httpMethod = "POST")
    public CommonResponse<Boolean> checkPermission(@RequestBody CheckPermission checkPermission)  {
        Boolean result = service.checkPermission(checkPermission.getUserId(), checkPermission.getPermissionId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/checkPermissionCode")
    @ApiOperation(value = "checkPermissionCode", httpMethod = "POST")
    public CommonResponse<Boolean> checkPermissionCode(@RequestBody CheckPermissionCode checkPermissionCode)  {
        Boolean result = service.checkPermissionCode(checkPermissionCode.getUserId(), checkPermissionCode.getPermissionCode());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/roleRefPermissions")
    @ApiOperation(value = "roleRefPermissions", httpMethod = "POST")
    public CommonResponse<RoleRefPermissions> roleRefPermissions()  {
        RoleRefPermissions result = service.roleRefPermissions();
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectServicePermission")
    @ApiOperation(value = "selectServicePermission", httpMethod = "POST")
    public CommonResponse<List<Permission>> selectServicePermission(@RequestBody String string)  {
        List<Permission> result = service.selectServicePermission(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectByCode")
    @ApiOperation(value = "selectByCode", httpMethod = "POST")
    public CommonResponse<Permission> selectByCode(@RequestBody String string)  {
        Permission result = service.selectByCode(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/rolePermissions")
    @ApiOperation(value = "rolePermissions", httpMethod = "POST")
    public CommonResponse<List<Permission>> rolePermissions(@RequestBody String string)  {
        List<Permission> result = service.rolePermissions(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/userPermissions")
    @ApiOperation(value = "userPermissions", httpMethod = "POST")
    public CommonResponse<List<Permission>> userPermissions(@RequestBody String string)  {
        List<Permission> result = service.userPermissions(string);
        return new CommonResponse<>(result);
    }

}