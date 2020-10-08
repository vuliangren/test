package com.welearn.service;

import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.qo.equipment.SparePartInItemQueryCondition;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Description : SparePartInItemService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SparePartInItemService extends BaseService<SparePartInItem, SparePartInItemQueryCondition>{
    /**
     * 配件入库成功
     * @param sparePartInBill 入库单
     */
    void finish(@NotNull SparePartInBill sparePartInBill);

    /**
     * 配件入库失败
     * @param sparePartInBill 入库单
     */
    void failed(@NotNull SparePartInBill sparePartInBill);
}