package com.welearn.service;

import com.welearn.entity.po.equipment.InspectionPlan;
import com.welearn.entity.qo.equipment.InspectionPlanQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : InspectionPlanService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface InspectionPlanService extends BaseService<InspectionPlan, InspectionPlanQueryCondition>{


}