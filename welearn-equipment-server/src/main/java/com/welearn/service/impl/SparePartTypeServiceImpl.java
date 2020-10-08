package com.welearn.service.impl;

import com.welearn.entity.po.equipment.SparePartType;
import com.welearn.entity.qo.equipment.SparePartTypeQueryCondition;
import com.welearn.mapper.SparePartTypeMapper;
import com.welearn.service.SparePartTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : SparePartTypeService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SparePartTypeServiceImpl extends BaseServiceImpl<SparePartType,SparePartTypeQueryCondition,SparePartTypeMapper>
        implements SparePartTypeService{
    
    @Autowired
    private SparePartTypeMapper sparePartTypeMapper;
    
    @Override
    SparePartTypeMapper getMapper() {
        return sparePartTypeMapper;
    }

}
