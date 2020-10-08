package com.welearn.service.impl;

import com.welearn.entity.po.common.Floor;
import com.welearn.entity.qo.common.FloorQueryCondition;
import com.welearn.mapper.FloorMapper;
import com.welearn.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : FloorService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class FloorServiceImpl extends BaseServiceImpl<Floor,FloorQueryCondition,FloorMapper>
        implements FloorService{
    
    @Autowired
    private FloorMapper floorMapper;
    
    @Override
    FloorMapper getMapper() {
        return floorMapper;
    }

}
