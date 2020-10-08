package com.welearn.service;

import com.welearn.entity.po.common.Permission;
import com.welearn.entity.po.common.Role;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.PermissionQueryCondition;
import com.welearn.entity.vo.response.common.RoleRefPermissions;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : 权限业务接口
 * Created by Setsuna Jin on 2018/1/7.
 */
@Validated
public interface PermissionService extends BaseService<Permission,PermissionQueryCondition> {

    /**
     * 获取系统中可用的角色与权限间的关联信息
     * @return RoleRefPermissions
     */
    RoleRefPermissions roleRefPermissions();

    /**
     * 用户拥有的权限
     * @param userId 用户id
     * @return 权限列表
     */
    List<Permission> userPermissions(@NotBlank String userId);

    /**
     * 角色拥有的权限
     * @param roleId 角色
     * @return 权限列表
     */
    List<Permission> rolePermissions(@NotBlank String roleId);

    /**
     * 判断用户是否具备某个权限
     * @param userId 用户
     * @param permissionId 权限
     * @return 是否具有
     */
    Boolean checkPermission(@NotBlank String userId, @NotBlank String permissionId);

    /**
     * 判断用户是否具备某个权限
     * @param userId 用户
     * @param permissionCode 权限
     * @return 是否具有
     */
    Boolean checkPermissionCode(@NotBlank String userId,
                                @NotBlank String permissionCode);

    /**
     * 根据权限的编码查询权限
     * @param code 权限编码
     * @return 权限
     */
    Permission selectByCode(@NotBlank String code);

    /**
     * 根据服务名称获取其定义权限
     * @param serviceName 服务名称
     * @return 权限列表
     */
    List<Permission> selectServicePermission(@NotBlank String serviceName);
}
