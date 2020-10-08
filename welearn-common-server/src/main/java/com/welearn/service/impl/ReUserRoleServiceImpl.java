package com.welearn.service.impl;

import com.welearn.entity.po.common.ReUserRole;
import com.welearn.entity.qo.common.ReUserRoleQueryCondition;
import com.welearn.mapper.ReUserRoleMapper;
import com.welearn.service.ReUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReUserRoleService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReUserRoleServiceImpl extends BaseServiceImpl<ReUserRole, ReUserRoleQueryCondition ,ReUserRoleMapper>
        implements ReUserRoleService{
    
    @Autowired
    private ReUserRoleMapper reUserRoleMapper;
    
    @Override
    ReUserRoleMapper getMapper() {
        return reUserRoleMapper;
    }

}
