package com.welearn.mapper;

import com.welearn.entity.po.equipment.EquipmentTypeDescription;
import com.welearn.entity.qo.equipment.EquipmentTypeDescriptionQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * EquipmentTypeDescription Mapper Interface : ryme_equipment : equipment_type_description
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/19 14:01:59
 * @see com.welearn.entity.po.equipment.EquipmentTypeDescription
 */
@Mapper 
public interface EquipmentTypeDescriptionMapper extends BaseMapper<EquipmentTypeDescription, EquipmentTypeDescriptionQueryCondition> {
    
}