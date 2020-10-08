package com.welearn.service.impl;

import com.welearn.controller.EquipmentTypeController;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.equipment.EquipmentTypeDescription;
import com.welearn.entity.qo.equipment.EquipmentTypeDescriptionQueryCondition;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.mapper.EquipmentTypeDescriptionMapper;
import com.welearn.service.EquipmentTypeDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : EquipmentTypeDescriptionService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentTypeDescriptionServiceImpl extends BaseServiceImpl<EquipmentTypeDescription,EquipmentTypeDescriptionQueryCondition,EquipmentTypeDescriptionMapper>
        implements EquipmentTypeDescriptionService{
    
    @Autowired
    private EquipmentTypeDescriptionMapper equipmentTypeDescriptionMapper;
    
    @Override
    EquipmentTypeDescriptionMapper getMapper() {
        return equipmentTypeDescriptionMapper;
    }
}
