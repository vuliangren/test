package com.welearn.service;

import com.welearn.entity.po.equipment.EquipmentAccessory;
import com.welearn.entity.qo.equipment.EquipmentAccessoryQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : EquipmentAccessoryService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentAccessoryService extends BaseService<EquipmentAccessory, EquipmentAccessoryQueryCondition>{

}