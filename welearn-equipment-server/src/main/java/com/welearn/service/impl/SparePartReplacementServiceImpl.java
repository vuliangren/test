package com.welearn.service.impl;

import com.welearn.entity.po.equipment.SparePartReplacement;
import com.welearn.entity.qo.equipment.SparePartReplacementQueryCondition;
import com.welearn.mapper.SparePartReplacementMapper;
import com.welearn.service.SparePartReplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : SparePartReplacementService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SparePartReplacementServiceImpl extends BaseServiceImpl<SparePartReplacement,SparePartReplacementQueryCondition,SparePartReplacementMapper>
        implements SparePartReplacementService{
    
    @Autowired
    private SparePartReplacementMapper sparePartReplacementMapper;
    
    @Override
    SparePartReplacementMapper getMapper() {
        return sparePartReplacementMapper;
    }

}
