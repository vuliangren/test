package com.welearn.mapper;

import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.entity.qo.equipment.SparePartInBillQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SparePartInBill Mapper Interface : ryme_equipment : spare_part_in_bill
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/1 10:24:28
 * @see com.welearn.entity.po.equipment.SparePartInBill
 */
@Mapper 
public interface SparePartInBillMapper extends BaseMapper<SparePartInBill, SparePartInBillQueryCondition> {
    
}