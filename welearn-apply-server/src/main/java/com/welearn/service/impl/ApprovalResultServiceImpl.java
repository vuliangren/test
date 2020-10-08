package com.welearn.service.impl;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.apply.ApprovalResult;
import com.welearn.entity.qo.apply.ApprovalResultQueryCondition;
import com.welearn.generator.ControllerGenerator;
import com.welearn.mapper.ApprovalResultMapper;
import com.welearn.service.ApprovalResultService;
import com.welearn.service.EquipmentYearPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ApprovalResultService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ApprovalResultServiceImpl extends BaseServiceImpl<ApprovalResult,ApprovalResultQueryCondition,ApprovalResultMapper>
        implements ApprovalResultService{
    
    @Autowired
    private ApprovalResultMapper approvalResultMapper;
    
    @Override
    ApprovalResultMapper getMapper() {
        return approvalResultMapper;
    }

}
