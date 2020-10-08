package com.welearn.mapper;

import com.welearn.entity.po.equipment.SparePartOutItem;
import com.welearn.entity.qo.equipment.SparePartOutItemQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SparePartOutItem Mapper Interface : ryme_equipment : spare_part_out_item
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/28 15:28:00
 * @see SparePartOutItem
 */
@Mapper 
public interface SparePartOutItemMapper extends BaseMapper<SparePartOutItem, SparePartOutItemQueryCondition> {
    
}