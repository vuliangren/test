package com.welearn.service;

import com.welearn.entity.po.equipment.RepairEvaluation;
import com.welearn.entity.qo.equipment.RepairEvaluationQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : RepairEvaluationService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairEvaluationService extends BaseService<RepairEvaluation, RepairEvaluationQueryCondition>{

}