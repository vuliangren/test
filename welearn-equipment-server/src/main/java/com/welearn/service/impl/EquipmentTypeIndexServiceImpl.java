package com.welearn.service.impl;

import com.welearn.entity.po.equipment.EquipmentTypeIndex;
import com.welearn.entity.qo.equipment.EquipmentTypeIndexQueryCondition;
import com.welearn.mapper.EquipmentTypeIndexMapper;
import com.welearn.service.EquipmentTypeIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : EquipmentTypeIndexService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentTypeIndexServiceImpl extends BaseServiceImpl<EquipmentTypeIndex,EquipmentTypeIndexQueryCondition,EquipmentTypeIndexMapper>
        implements EquipmentTypeIndexService{
    
    @Autowired
    private EquipmentTypeIndexMapper equipmentTypeIndexMapper;
    
    @Override
    EquipmentTypeIndexMapper getMapper() {
        return equipmentTypeIndexMapper;
    }

}
