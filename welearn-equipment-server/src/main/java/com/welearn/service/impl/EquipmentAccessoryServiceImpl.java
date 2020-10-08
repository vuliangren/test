package com.welearn.service.impl;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.equipment.EquipmentAccessory;
import com.welearn.entity.qo.equipment.EquipmentAccessoryQueryCondition;
import com.welearn.generator.ControllerGenerator;
import com.welearn.mapper.EquipmentAccessoryMapper;
import com.welearn.service.EquipmentAccessoryService;
import com.welearn.service.EquipmentGuaranteeRepairRecordService;
import com.welearn.service.EquipmentProductService;
import com.welearn.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : EquipmentAccessoryService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentAccessoryServiceImpl extends BaseServiceImpl<EquipmentAccessory,EquipmentAccessoryQueryCondition,EquipmentAccessoryMapper>
        implements EquipmentAccessoryService{
    
    @Autowired
    private EquipmentAccessoryMapper equipmentAccessoryMapper;
    
    @Override
    EquipmentAccessoryMapper getMapper() {
        return equipmentAccessoryMapper;
    }
}
