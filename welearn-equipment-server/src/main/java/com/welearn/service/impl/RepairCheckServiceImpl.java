package com.welearn.service.impl;

import com.welearn.entity.po.equipment.RepairCheck;
import com.welearn.entity.qo.equipment.RepairCheckQueryCondition;
import com.welearn.mapper.RepairCheckMapper;
import com.welearn.service.RepairCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : RepairCheckService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairCheckServiceImpl extends BaseServiceImpl<RepairCheck,RepairCheckQueryCondition,RepairCheckMapper>
        implements RepairCheckService{
    
    @Autowired
    private RepairCheckMapper repairCheckMapper;
    
    @Override
    RepairCheckMapper getMapper() {
        return repairCheckMapper;
    }

}
