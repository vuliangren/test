package com.welearn.service;

import com.welearn.entity.po.equipment.EquipmentTypeDescription;
import com.welearn.entity.qo.equipment.EquipmentTypeDescriptionQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : EquipmentTypeDescriptionService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentTypeDescriptionService extends BaseService<EquipmentTypeDescription, EquipmentTypeDescriptionQueryCondition>{

}