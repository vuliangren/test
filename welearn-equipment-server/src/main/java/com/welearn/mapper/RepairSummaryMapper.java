package com.welearn.mapper;

import com.welearn.entity.po.equipment.RepairSummary;
import com.welearn.entity.qo.equipment.RepairSummaryQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * RepairSummary Mapper Interface : ryme_equipment : repair_summary
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:14:04
 * @see com.welearn.entity.po.equipment.RepairSummary
 */
@Mapper 
public interface RepairSummaryMapper extends BaseMapper<RepairSummary, RepairSummaryQueryCondition> {
    
}