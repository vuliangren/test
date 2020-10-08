package com.welearn.mapper;

import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.qo.equipment.SparePartInItemQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SparePartInItem Mapper Interface : ryme_equipment : spare_part_in_item
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/1 10:24:29
 * @see com.welearn.entity.po.equipment.SparePartInItem
 */
@Mapper 
public interface SparePartInItemMapper extends BaseMapper<SparePartInItem, SparePartInItemQueryCondition> {
    
}