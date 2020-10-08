package com.welearn.service;

import com.welearn.entity.po.equipment.SparePartOutBill;
import com.welearn.entity.po.equipment.SparePartOutItem;
import com.welearn.entity.qo.equipment.SparePartOutItemQueryCondition;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : SparePartOutItemService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SparePartOutItemService extends BaseService<SparePartOutItem, SparePartOutItemQueryCondition>{

    /**
     * 批量创建
     * @param items 出库项列表
     */
    void batchCreate(List<SparePartOutItem> items);
}