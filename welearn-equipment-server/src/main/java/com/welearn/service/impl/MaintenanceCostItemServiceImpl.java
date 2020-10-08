package com.welearn.service.impl;

import com.welearn.entity.po.equipment.MaintenanceCostItem;
import com.welearn.entity.qo.equipment.MaintenanceCostItemQueryCondition;
import com.welearn.mapper.MaintenanceCostItemMapper;
import com.welearn.service.MaintenanceCostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : MaintenanceCostItemService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class MaintenanceCostItemServiceImpl extends BaseServiceImpl<MaintenanceCostItem,MaintenanceCostItemQueryCondition,MaintenanceCostItemMapper>
        implements MaintenanceCostItemService{
    
    @Autowired
    private MaintenanceCostItemMapper maintenanceCostItemMapper;
    
    @Override
    MaintenanceCostItemMapper getMapper() {
        return maintenanceCostItemMapper;
    }

}
