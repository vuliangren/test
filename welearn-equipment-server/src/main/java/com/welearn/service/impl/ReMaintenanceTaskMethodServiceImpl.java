package com.welearn.service.impl;

import com.welearn.entity.po.equipment.ReMaintenanceTaskMethod;
import com.welearn.entity.qo.equipment.ReMaintenanceTaskMethodQueryCondition;
import com.welearn.mapper.ReMaintenanceTaskMethodMapper;
import com.welearn.service.ReMaintenanceTaskMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReMaintenanceTaskMethodService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReMaintenanceTaskMethodServiceImpl extends BaseServiceImpl<ReMaintenanceTaskMethod,ReMaintenanceTaskMethodQueryCondition,ReMaintenanceTaskMethodMapper>
        implements ReMaintenanceTaskMethodService{
    
    @Autowired
    private ReMaintenanceTaskMethodMapper reMaintenanceTaskMethodMapper;
    
    @Override
    ReMaintenanceTaskMethodMapper getMapper() {
        return reMaintenanceTaskMethodMapper;
    }

}
