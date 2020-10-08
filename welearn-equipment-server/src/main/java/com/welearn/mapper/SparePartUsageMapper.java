package com.welearn.mapper;

import com.welearn.entity.po.equipment.SparePartUsage;
import com.welearn.entity.qo.equipment.SparePartUsageQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SparePartUsage Mapper Interface : ryme_equipment : spare_part_usage
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/25 17:49:40
 * @see SparePartUsage
 */
@Mapper 
public interface SparePartUsageMapper extends BaseMapper<SparePartUsage, SparePartUsageQueryCondition> {
    
}