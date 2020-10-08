package com.welearn.service.impl;

import com.welearn.entity.po.common.ReRolePermission;
import com.welearn.entity.qo.common.ReRolePermissionQueryCondition;
import com.welearn.mapper.ReRolePermissionMapper;
import com.welearn.service.ReRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReRolePermissionService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReRolePermissionServiceImpl extends BaseServiceImpl<ReRolePermission, ReRolePermissionQueryCondition,ReRolePermissionMapper>
        implements ReRolePermissionService{
    
    @Autowired
    private ReRolePermissionMapper reRolePermissionMapper;
    
    @Override
    ReRolePermissionMapper getMapper() {
        return reRolePermissionMapper;
    }

}
