package com.welearn.service.impl;

import com.welearn.entity.po.common.ReRouteHelp;
import com.welearn.entity.qo.common.ReRouteHelpQueryCondition;
import com.welearn.mapper.ReRouteHelpMapper;
import com.welearn.service.ReRouteHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReRouteHelpService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReRouteHelpServiceImpl extends BaseServiceImpl<ReRouteHelp,ReRouteHelpQueryCondition,ReRouteHelpMapper>
        implements ReRouteHelpService{
    
    @Autowired
    private ReRouteHelpMapper reRouteHelpMapper;
    
    @Override
    ReRouteHelpMapper getMapper() {
        return reRouteHelpMapper;
    }

}
