package com.welearn.service;

import com.welearn.entity.po.equipment.EquipmentBorrowAccessory;
import com.welearn.entity.qo.equipment.EquipmentBorrowAccessoryQueryCondition;
import com.welearn.entity.vo.response.equipment.EquipmentBorrowAccessoryInfo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : EquipmentBorrowAccessoryService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentBorrowAccessoryService extends BaseService<EquipmentBorrowAccessory, EquipmentBorrowAccessoryQueryCondition>{

    /**
     * 查询设备借用附件详情信息
     * @param condition 附件借用查询条件
     * @return 设备附件及附件借用信息
     */
    List<EquipmentBorrowAccessoryInfo> searchInfo(EquipmentBorrowAccessoryQueryCondition condition);
}