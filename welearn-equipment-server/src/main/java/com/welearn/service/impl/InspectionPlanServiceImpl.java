package com.welearn.service.impl;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.equipment.InspectionPlan;
import com.welearn.entity.po.equipment.InspectionRecord;
import com.welearn.entity.qo.equipment.InspectionPlanQueryCondition;
import com.welearn.generator.ControllerGenerator;
import com.welearn.mapper.InspectionPlanMapper;
import com.welearn.service.InspectionPlanService;
import com.welearn.service.InspectionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : InspectionPlanService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class InspectionPlanServiceImpl extends BaseServiceImpl<InspectionPlan,InspectionPlanQueryCondition,InspectionPlanMapper>
        implements InspectionPlanService{
    
    @Autowired
    private InspectionPlanMapper inspectionPlanMapper;
    
    @Override
    InspectionPlanMapper getMapper() {
        return inspectionPlanMapper;
    }

    @Override
    public InspectionPlan create(InspectionPlan inspectionPlan){
        Integer count = inspectionPlanMapper.countByCompanyId(inspectionPlan.getCompanyId());
        inspectionPlan.setNo(String.format("#%06d", ++count));
        return super.create(inspectionPlan);
    }
}
