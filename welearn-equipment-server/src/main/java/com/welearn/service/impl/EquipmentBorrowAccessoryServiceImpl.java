package com.welearn.service.impl;

import com.welearn.entity.po.equipment.EquipmentBorrowAccessory;
import com.welearn.entity.qo.equipment.EquipmentBorrowAccessoryQueryCondition;
import com.welearn.entity.vo.response.equipment.EquipmentBorrowAccessoryInfo;
import com.welearn.mapper.EquipmentBorrowAccessoryMapper;
import com.welearn.service.EquipmentBorrowAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : EquipmentBorrowAccessoryService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentBorrowAccessoryServiceImpl extends BaseServiceImpl<EquipmentBorrowAccessory,EquipmentBorrowAccessoryQueryCondition,EquipmentBorrowAccessoryMapper>
        implements EquipmentBorrowAccessoryService{
    
    @Autowired
    private EquipmentBorrowAccessoryMapper equipmentBorrowAccessoryMapper;
    
    @Override
    EquipmentBorrowAccessoryMapper getMapper() {
        return equipmentBorrowAccessoryMapper;
    }

    /**
     * 查询设备借用附件详情信息
     *
     * @param condition 附件借用查询条件
     * @return 设备附件及附件借用信息
     */
    @Override
    public List<EquipmentBorrowAccessoryInfo> searchInfo(EquipmentBorrowAccessoryQueryCondition condition) {
        return equipmentBorrowAccessoryMapper.searchInfo(condition);
    }
}
