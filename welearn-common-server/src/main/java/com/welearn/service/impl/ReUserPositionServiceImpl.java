package com.welearn.service.impl;

import com.welearn.entity.po.common.ReUserPosition;
import com.welearn.entity.qo.common.ReUserPositionQueryCondition;
import com.welearn.mapper.ReUserPositionMapper;
import com.welearn.service.ReUserPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReUserPositionService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReUserPositionServiceImpl extends BaseServiceImpl<ReUserPosition,ReUserPositionQueryCondition,ReUserPositionMapper>
        implements ReUserPositionService{
    
    @Autowired
    private ReUserPositionMapper reUserPositionMapper;
    
    @Override
    ReUserPositionMapper getMapper() {
        return reUserPositionMapper;
    }

}
