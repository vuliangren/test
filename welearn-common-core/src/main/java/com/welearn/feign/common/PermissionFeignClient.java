package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.common.Permission;
import com.welearn.entity.qo.common.PermissionQueryCondition;
import com.welearn.entity.vo.request.common.CheckPermission;
import com.welearn.entity.vo.request.common.CheckPermissionCode;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.common.RoleRefPermissions;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-common-service / com.welearn.controller.PermissionController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface PermissionFeignClient {

    @RequestMapping(value = "/permission/checkPermission")
    CommonResponse<Boolean> checkPermission(CheckPermission checkPermission);

    @RequestMapping(value = "/permission/selectServicePermission")
    CommonResponse<List<Permission>> selectServicePermission(String string);

    @RequestMapping(value = "/permission/roleRefPermissions")
    CommonResponse<RoleRefPermissions> roleRefPermissions();

    @RequestMapping(value = "/permission/checkPermissionCode")
    CommonResponse<Boolean> checkPermissionCode(CheckPermissionCode checkPermissionCode);

    @RequestMapping(value = "/permission/userPermissions")
    CommonResponse<List<Permission>> userPermissions(String string);

    @RequestMapping(value = "/permission/selectByCode")
    CommonResponse<Permission> selectByCode(String string);

    @RequestMapping(value = "/permission/rolePermissions")
    CommonResponse<List<Permission>> rolePermissions(String string);

    @RequestMapping(value = "/permission/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/permission/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/permission/update")
    CommonResponse update(Permission entity);

    @RequestMapping(value = "/permission/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/permission/create")
    CommonResponse create(Permission entity);

    @RequestMapping(value = "/permission/search")
    CommonResponse<List<Permission>> search(PermissionQueryCondition queryCondition);

    @RequestMapping(value = "/permission/select")
    CommonResponse<Permission> select(String id);
}