package com.welearn.service;

import com.welearn.entity.po.equipment.ReEquipmentEquipment;
import com.welearn.entity.qo.equipment.ReEquipmentEquipmentQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ReEquipmentEquipmentService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReEquipmentEquipmentService extends BaseService<ReEquipmentEquipment, ReEquipmentEquipmentQueryCondition>{

}