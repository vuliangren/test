package com.welearn.service.impl;

import com.welearn.entity.po.equipment.ReEquipmentTypeItemDescription;
import com.welearn.entity.qo.equipment.ReEquipmentTypeItemDescriptionQueryCondition;
import com.welearn.mapper.ReEquipmentTypeItemDescriptionMapper;
import com.welearn.service.ReEquipmentTypeItemDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReEquipmentTypeItemDescriptionService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReEquipmentTypeItemDescriptionServiceImpl extends BaseServiceImpl<ReEquipmentTypeItemDescription,ReEquipmentTypeItemDescriptionQueryCondition,ReEquipmentTypeItemDescriptionMapper>
        implements ReEquipmentTypeItemDescriptionService{
    
    @Autowired
    private ReEquipmentTypeItemDescriptionMapper reEquipmentTypeItemDescriptionMapper;
    
    @Override
    ReEquipmentTypeItemDescriptionMapper getMapper() {
        return reEquipmentTypeItemDescriptionMapper;
    }

}
