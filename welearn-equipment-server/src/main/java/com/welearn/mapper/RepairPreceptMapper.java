package com.welearn.mapper;

import com.welearn.entity.po.equipment.RepairPrecept;
import com.welearn.entity.qo.equipment.RepairPreceptQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * RepairPrecept Mapper Interface : ryme_equipment : repair_precept
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:14:01
 * @see com.welearn.entity.po.equipment.RepairPrecept
 */
@Mapper 
public interface RepairPreceptMapper extends BaseMapper<RepairPrecept, RepairPreceptQueryCondition> {
    
}