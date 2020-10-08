package com.welearn.mapper;

import com.welearn.entity.po.equipment.SparePartType;
import com.welearn.entity.qo.equipment.SparePartTypeQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SparePartType Mapper Interface : ryme_equipment : spare_part_type
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/25 17:49:39
 * @see SparePartType
 */
@Mapper 
public interface SparePartTypeMapper extends BaseMapper<SparePartType, SparePartTypeQueryCondition> {
    
}