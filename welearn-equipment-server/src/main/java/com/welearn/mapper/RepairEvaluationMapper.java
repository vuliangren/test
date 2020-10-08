package com.welearn.mapper;

import com.welearn.entity.po.equipment.RepairEvaluation;
import com.welearn.entity.qo.equipment.RepairEvaluationQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * RepairEvaluation Mapper Interface : ryme_equipment : repair_evaluation
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:14:00
 * @see com.welearn.entity.po.equipment.RepairEvaluation
 */
@Mapper 
public interface RepairEvaluationMapper extends BaseMapper<RepairEvaluation, RepairEvaluationQueryCondition> {
    
}