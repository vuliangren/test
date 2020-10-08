package com.welearn.service.impl;

import com.welearn.entity.po.equipment.ReMaintenanceRecordMethod;
import com.welearn.entity.qo.equipment.ReMaintenanceRecordMethodQueryCondition;
import com.welearn.mapper.ReMaintenanceRecordMethodMapper;
import com.welearn.service.ReMaintenanceRecordMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReMaintenanceRecordMethodService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReMaintenanceRecordMethodServiceImpl extends BaseServiceImpl<ReMaintenanceRecordMethod,ReMaintenanceRecordMethodQueryCondition,ReMaintenanceRecordMethodMapper>
        implements ReMaintenanceRecordMethodService{
    
    @Autowired
    private ReMaintenanceRecordMethodMapper reMaintenanceRecordMethodMapper;
    
    @Override
    ReMaintenanceRecordMethodMapper getMapper() {
        return reMaintenanceRecordMethodMapper;
    }

}
