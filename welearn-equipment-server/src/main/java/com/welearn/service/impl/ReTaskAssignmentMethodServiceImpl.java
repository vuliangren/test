package com.welearn.service.impl;

import com.welearn.entity.po.equipment.ReTaskAssignmentMethod;
import com.welearn.entity.qo.equipment.ReTaskAssignmentMethodQueryCondition;
import com.welearn.mapper.ReTaskAssignmentMethodMapper;
import com.welearn.service.ReTaskAssignmentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReTaskAssignmentMethodService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReTaskAssignmentMethodServiceImpl extends BaseServiceImpl<ReTaskAssignmentMethod,ReTaskAssignmentMethodQueryCondition,ReTaskAssignmentMethodMapper>
        implements ReTaskAssignmentMethodService{
    
    @Autowired
    private ReTaskAssignmentMethodMapper reTaskAssignmentMethodMapper;
    
    @Override
    ReTaskAssignmentMethodMapper getMapper() {
        return reTaskAssignmentMethodMapper;
    }

}
