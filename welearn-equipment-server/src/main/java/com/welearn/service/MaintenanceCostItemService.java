package com.welearn.service;

import com.welearn.entity.po.equipment.MaintenanceCostItem;
import com.welearn.entity.qo.equipment.MaintenanceCostItemQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : MaintenanceCostItemService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface MaintenanceCostItemService extends BaseService<MaintenanceCostItem, MaintenanceCostItemQueryCondition>{

}