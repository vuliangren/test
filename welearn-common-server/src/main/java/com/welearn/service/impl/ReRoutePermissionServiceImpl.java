package com.welearn.service.impl;

import com.welearn.entity.po.common.ReRoutePermission;
import com.welearn.entity.qo.common.ReRoutePermissionQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.ReRoutePermissionMapper;
import com.welearn.service.ReRoutePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description : ReRoutePermissionService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReRoutePermissionServiceImpl extends BaseServiceImpl<ReRoutePermission,ReRoutePermissionQueryCondition,ReRoutePermissionMapper>
        implements ReRoutePermissionService{
    
    @Autowired
    private ReRoutePermissionMapper reRoutePermissionMapper;
    
    @Override
    ReRoutePermissionMapper getMapper() {
        return reRoutePermissionMapper;
    }

    @Override
    public ReRoutePermission create(ReRoutePermission reRoutePermission) {
        ReRoutePermissionQueryCondition condition = new ReRoutePermissionQueryCondition();
        condition.setIsEnable(true);
        condition.setClientType(reRoutePermission.getClientType());
        condition.setPath(reRoutePermission.getPath());
        condition.setPermission(reRoutePermission.getPermission());
        List<ReRoutePermission> list = reRoutePermissionMapper.selectByCondition(condition);
        if (Objects.nonNull(list) && list.isEmpty()) {
            return super.create(reRoutePermission);
        } else {
            throw new BusinessVerifyFailedException("客户端路由请勿重复关联同一个权限");
        }
    }

    /**
     * 根据角色编码 查询与之关联的路由 所 关联权限编码
     *
     * @param roleCode   角色编码
     * @param clientType 客户端类型
     * @return 权限编码
     */
    @Override
    public List<String> selectPermissionCodeByRoleCode(String roleCode, Integer clientType) {
        return reRoutePermissionMapper.selectPermissionCodeByRoleCode(roleCode, clientType);
    }

    /**
     * 批量创建和更新 一个路由的权限关联
     * 将路由现有关联与参数进行对比, 相同的不变, 其余的创建或删除
     *
     * @param clientType       客户端类型
     * @param path             路由地址
     * @param permissionCodes 权限关联
     */
    @Override
    public void batchCreateAndUpdate(Integer clientType, String path, List<String> permissionCodes) {
        ReRoutePermissionQueryCondition condition = new ReRoutePermissionQueryCondition();
        condition.setIsEnable(true);
        condition.setClientType(clientType);
        condition.setPath(path);
        List<ReRoutePermission> reRoutePermissions = reRoutePermissionMapper.selectByCondition(condition);
        List<String> oldPermissionCodes = reRoutePermissions.stream()
                .map(ReRoutePermission::getPermission).collect(Collectors.toList());
        Set<String> tmpPermissionCodes = new HashSet<>(oldPermissionCodes);
        Set<String> newPermissionCodes = new HashSet<>(permissionCodes);

        // 获得需要新增的权限编码
        newPermissionCodes.removeAll(oldPermissionCodes);
        newPermissionCodes.forEach(permissionCode -> {
            this.create(new ReRoutePermission(clientType, path, permissionCode));
        });

        // 获得需要删除的权限编码
        tmpPermissionCodes.removeAll(permissionCodes);
        tmpPermissionCodes.forEach(permissionCode -> {
            reRoutePermissions.stream().filter(reRoutePermission -> reRoutePermission.getPermission().equals(permissionCode))
                    .forEach(reRoutePermission -> this.delete(reRoutePermission.getId()));
        });
    }
}
