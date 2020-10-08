package com.welearn.service.impl;

import com.welearn.entity.po.equipment.SparePartUsage;
import com.welearn.entity.qo.equipment.SparePartUsageQueryCondition;
import com.welearn.mapper.SparePartUsageMapper;
import com.welearn.service.SparePartUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : SparePartUsageService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SparePartUsageServiceImpl extends BaseServiceImpl<SparePartUsage,SparePartUsageQueryCondition,SparePartUsageMapper>
        implements SparePartUsageService{
    
    @Autowired
    private SparePartUsageMapper sparePartUsageMapper;
    
    @Override
    SparePartUsageMapper getMapper() {
        return sparePartUsageMapper;
    }

}
