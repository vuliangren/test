package com.welearn.service.impl;

import com.welearn.entity.po.apply.LargeEquipmentApplication;
import com.welearn.entity.qo.apply.LargeEquipmentApplicationQueryCondition;
import com.welearn.mapper.LargeEquipmentApplicationMapper;
import com.welearn.service.LargeEquipmentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : LargeEquipmentApplicationService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class LargeEquipmentApplicationServiceImpl extends BaseServiceImpl<LargeEquipmentApplication,LargeEquipmentApplicationQueryCondition,LargeEquipmentApplicationMapper>
        implements LargeEquipmentApplicationService{
    
    @Autowired
    private LargeEquipmentApplicationMapper largeEquipmentApplicationMapper;
    
    @Override
    LargeEquipmentApplicationMapper getMapper() {
        return largeEquipmentApplicationMapper;
    }

}
