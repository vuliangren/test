package com.welearn.service.impl;

import com.welearn.entity.po.common.Permission;
import com.welearn.entity.qo.common.PermissionQueryCondition;
import com.welearn.entity.vo.response.common.RoleRefPermissions;
import com.welearn.mapper.PermissionMapper;
import com.welearn.mapper.ReRolePermissionMapper;
import com.welearn.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,PermissionQueryCondition,PermissionMapper>
        implements PermissionService {
    private final PermissionMapper permissionMapper;
    private final ReRolePermissionMapper reRolePermissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper, ReRolePermissionMapper reRolePermissionMapper) {
        this.permissionMapper = permissionMapper;
        this.reRolePermissionMapper = reRolePermissionMapper;
    }

    @Override
    PermissionMapper getMapper() {
        return permissionMapper;
    }

    /**
     * 获取系统中可用的角色与权限间的关联信息
     *
     * @return RoleRefPermissions
     */
    @Override
    public RoleRefPermissions roleRefPermissions() {
        return new RoleRefPermissions(reRolePermissionMapper.selectAllEnabled(), permissionMapper.selectAllEnabled());
    }

    /**
     * 用户拥有的权限
     *
     * @param userId 用户id
     * @return 权限列表
     */
    @Override
    public List<Permission> userPermissions(String userId) {
        return reRolePermissionMapper.selectByUserId(userId);
    }

    /**
     * 角色拥有的权限
     *
     * @param roleId 角色id
     * @return 权限列表
     */
    @Override
    public List<Permission> rolePermissions(String roleId) {
        return reRolePermissionMapper.selectByRoleId(roleId);
    }

    /**
     * 判断用户是否具备某个权限
     *
     * @param userId       用户id
     * @param permissionId 权限id
     * @return 是否具有
     */
    @Override
    public Boolean checkPermission(String userId, String permissionId) {
        return checkPermissionCode(userId, permissionId);
    }

    /**
     * 判断用户是否具备某个权限
     *
     * @param userId         用户
     * @param permissionCode 权限
     * @return 是否具有
     */
    @Override
    public Boolean checkPermissionCode(String userId, String permissionCode) {
        return reRolePermissionMapper.selectByUserIdAndPermissionCode(userId, permissionCode).size() > 0;
    }

    /**
     * 根据权限的编码查询权限
     *
     * @param code 权限编码
     * @return 权限
     */
    @Override
    public Permission selectByCode(String code) {
        return permissionMapper.selectByCode(code);
    }

    /**
     * 根据服务名称获取其定义权限
     *
     * @param serviceName 服务名称
     * @return 权限列表
     */
    @Override
    public List<Permission> selectServicePermission(String serviceName) {
        return permissionMapper.selectByService(serviceName);
    }


}
