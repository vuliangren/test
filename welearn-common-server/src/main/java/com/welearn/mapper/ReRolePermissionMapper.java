package com.welearn.mapper;

import com.welearn.entity.po.common.Permission;
import com.welearn.entity.po.common.ReRolePermission;
import com.welearn.entity.qo.common.ReRolePermissionQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ReRolePermission Mapper Interface : welearn-common : re_role_permission
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/1/6 23:08:28
 * @see ReRolePermission
 */
@Mapper public interface ReRolePermissionMapper extends BaseMapper<ReRolePermission, ReRolePermissionQueryCondition> {
    
    /**
     * 根据角色id和权限id查询角色权限关联信息
     * @param roleId 角色id
     * @param permissionId 权限id
     * @return 角色权限关联
     */
    ReRolePermission selectByRoleIdAndPermissionId(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    /**
     * 根据角色id查询角色权限关联信息
     * @param roleId 角色id
     * @return 权限列表
     */
    List<Permission> selectByRoleId(@Param("roleId") String roleId);

    /**
     * 根据用户id查询角色权限关联信息
     * @param userId 用户id
     * @return 权限列表
     */
    List<Permission> selectByUserId(@Param("userId") String userId);

    /**
     * 获取全部可用的角色与权限关联
     * @return 角色权限关联列表
     */
    List<ReRolePermission> selectAllEnabled();

    /**
     * 根据 userId 和 permissionCode 获取关联信息
     * @param userId 用户id
     * @param permissionCode 权限code
     * @return 关联列表
     */
    List<ReRolePermission> selectByUserIdAndPermissionCode(@Param("userId") String userId, @Param("permissionCode") String permissionCode);
}