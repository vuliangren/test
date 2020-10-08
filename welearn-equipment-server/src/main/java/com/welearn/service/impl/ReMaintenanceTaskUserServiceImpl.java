package com.welearn.service.impl;

import com.welearn.entity.po.equipment.ReMaintenanceTaskUser;
import com.welearn.entity.qo.equipment.ReMaintenanceTaskUserQueryCondition;
import com.welearn.mapper.ReMaintenanceTaskUserMapper;
import com.welearn.service.ReMaintenanceTaskUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReMaintenanceTaskUserService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReMaintenanceTaskUserServiceImpl extends BaseServiceImpl<ReMaintenanceTaskUser,ReMaintenanceTaskUserQueryCondition,ReMaintenanceTaskUserMapper>
        implements ReMaintenanceTaskUserService{
    
    @Autowired
    private ReMaintenanceTaskUserMapper reMaintenanceTaskUserMapper;
    
    @Override
    ReMaintenanceTaskUserMapper getMapper() {
        return reMaintenanceTaskUserMapper;
    }

}
