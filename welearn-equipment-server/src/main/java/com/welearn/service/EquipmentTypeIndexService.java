package com.welearn.service;

import com.welearn.entity.po.equipment.EquipmentTypeIndex;
import com.welearn.entity.qo.equipment.EquipmentTypeIndexQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : EquipmentTypeIndexService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentTypeIndexService extends BaseService<EquipmentTypeIndex, EquipmentTypeIndexQueryCondition>{

}