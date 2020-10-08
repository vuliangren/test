package com.welearn.service;

import com.welearn.entity.po.equipment.RepairCostItem;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.qo.equipment.RepairCostItemQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : RepairCostItemService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairCostItemService extends BaseService<RepairCostItem, RepairCostItemQueryCondition>{

    /**
     * 创建维修配件消费项目
     * @param replacement 维修配件更换
     * @param price 价格
     */
    void createSparePartCostItem(RepairReplacement replacement, Double price);
}