package com.welearn.mapper;

import com.welearn.entity.po.equipment.SparePart;
import com.welearn.entity.qo.equipment.SparePartQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SparePart Mapper Interface : ryme_equipment : spare_part
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/25 17:49:37
 * @see SparePart
 */
@Mapper 
public interface SparePartMapper extends BaseMapper<SparePart, SparePartQueryCondition> {
    
}