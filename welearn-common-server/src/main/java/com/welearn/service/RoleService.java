package com.welearn.service;

import com.welearn.entity.po.common.*;
import com.welearn.entity.qo.common.RoleQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.error.exception.DbOperationFailedException;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Stream;

/**
 * Description : 角色业务接口
 * 角色可以有子角色，子角色拥有父角色的全部或部分权限
 * Created by Setsuna Jin on 2018/1/7.
 */
@Validated
public interface RoleService extends BaseService<Role,RoleQueryCondition> {

    /**
     * 获取用户拥有的角色
     * @param userId 用户
     * @return 角色列表
     */
    List<Role> userRoles(@NotBlank String userId);

    /**
     * 角色批量绑定权限, 比对参数与数据库中已绑定的权限, 对已绑定的执行删除,对未绑定的执行添加
     * @param roleId 角色id
     * @param permissionIds 权限id列表
     */
    void bindPermissions(@NotBlank String roleId,
                         @NotNull List<String> permissionIds)
            throws DbOperationFailedException;

    /**
     * 角色批量绑定权限, 将 角色 关联的 客户端路由 绑定的权限 和 角色自身现有权限, 进行合并去重后更新角色权限信息
     * @param roleCode 角色编码
     * @param clientType 客户端类型
     */
    void bindRoutePermissions(@NotBlank String roleCode, @NotNull Integer clientType);

    /**
     * 角色解绑权限
     * @param roleId 角色
     * @param permissionId 权限
     */
    void unbindPermission(@NotBlank String roleId,
                          @NotBlank String permissionId)
            throws BusinessVerifyFailedException, DbOperationFailedException;

    /**
     * 用户绑定角色列表 TODO: 验证角色的派生关系, 防止越级分配权限
     * @param userId 用户id
     * @param roleIds 角色id
     */
    void bindUser(@NotBlank String userId, @NotNull List<String> roleIds);

    /**
     * 用户解绑角色
     *
     * @param userId  用户
     * @param roleId  角色
     */
    void unbindUser(@NotBlank String userId, @NotBlank String roleId)
            throws BusinessVerifyFailedException;

    /**
     * 根据角色的 code 获取 角色
     * @param code 角色编码
     * @return 角色
     */
    Role selectByCode(@NotBlank String code);

    /**
     * 根据角色编码给用户绑定一个新角色
     * @param userId 用户id
     * @param code 角色编码
     */
    void bindUserWithCode(String userId, String code);

    /**
     * 根据角色编码给用户解绑一个新角色
     * @param userId 用户id
     * @param code 角色编码
     */
    void unbindUserWithCode(String userId, String code);
}
