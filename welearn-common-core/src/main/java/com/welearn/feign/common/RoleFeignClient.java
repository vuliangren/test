package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.vo.request.common.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.common.Role;
import com.welearn.entity.qo.common.RoleQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-common-service / com.welearn.controller.RoleController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface RoleFeignClient {

    @RequestMapping(value = "/role/bindRoutePermissions")
    CommonResponse bindRoutePermissions(BindRoutePermissions bindRoutePermissions);

    @RequestMapping(value = "/role/unbindUserWithCode")
    CommonResponse unbindUserWithCode(UnbindUserWithCode unbindUserWithCode);

    @RequestMapping(value = "/role/bindUserWithCode")
    CommonResponse bindUserWithCode(BindUserWithCode bindUserWithCode);

    @RequestMapping(value = "/role/unbindUser")
    CommonResponse unbindUser(UnbindUser unbindUser);

    @RequestMapping(value = "/role/selectByCode")
    CommonResponse<Role> selectByCode(String string);

    @RequestMapping(value = "/role/bindPermissions")
    CommonResponse bindPermissions(BindPermissions bindPermissions);

    @RequestMapping(value = "/role/userRoles")
    CommonResponse<List<Role>> userRoles(String string);

    @RequestMapping(value = "/role/bindUser")
    CommonResponse bindUser(BindUser bindUser);

    @RequestMapping(value = "/role/unbindPermission")
    CommonResponse unbindPermission(UnbindPermission unbindPermission);

    @RequestMapping(value = "/role/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/role/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/role/update")
    CommonResponse update(Role entity);

    @RequestMapping(value = "/role/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/role/create")
    CommonResponse<Role> create(Role entity);

    @RequestMapping(value = "/role/search")
    CommonResponse<List<Role>> search(RoleQueryCondition queryCondition);

    @RequestMapping(value = "/role/select")
    CommonResponse<Role> select(String id);
}