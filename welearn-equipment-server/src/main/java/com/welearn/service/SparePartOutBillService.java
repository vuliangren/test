package com.welearn.service;

import com.welearn.entity.po.equipment.SparePartOutBill;
import com.welearn.entity.qo.equipment.SparePartOutBillQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : SparePartOutBillService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SparePartOutBillService extends BaseService<SparePartOutBill, SparePartOutBillQueryCondition>{

    /**
     * 出库单接受人签字确认
     * @param sparePartOutBillId 出库单
     * @param recipientSignatureId 接受人签字
     */
    void finish(String sparePartOutBillId, String recipientSignatureId);
}