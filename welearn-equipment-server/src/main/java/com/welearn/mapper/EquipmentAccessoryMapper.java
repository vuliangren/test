package com.welearn.mapper;

import com.welearn.entity.po.equipment.EquipmentAccessory;
import com.welearn.entity.qo.equipment.EquipmentAccessoryQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * EquipmentAccessory Mapper Interface : ryme_equipment : equipment_accessory
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/6 20:33:27
 * @see com.welearn.entity.po.equipment.EquipmentAccessory
 */
@Mapper 
public interface EquipmentAccessoryMapper extends BaseMapper<EquipmentAccessory, EquipmentAccessoryQueryCondition> {
    
}