package com.welearn.service;

import com.welearn.entity.po.equipment.ReEquipmentTypeItemDescription;
import com.welearn.entity.qo.equipment.ReEquipmentTypeItemDescriptionQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ReEquipmentTypeItemDescriptionService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReEquipmentTypeItemDescriptionService extends BaseService<ReEquipmentTypeItemDescription, ReEquipmentTypeItemDescriptionQueryCondition>{

}