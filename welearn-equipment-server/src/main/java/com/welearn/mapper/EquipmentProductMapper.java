package com.welearn.mapper;

import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.qo.equipment.EquipmentProductQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * EquipmentProduct Mapper Interface : ryme_equipment : equipment_product
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/10 10:22:57
 * @see com.welearn.entity.po.equipment.EquipmentProduct
 */
@Mapper 
public interface EquipmentProductMapper extends BaseMapper<EquipmentProduct, EquipmentProductQueryCondition> {
    /**
     * 查询 设备产品 技术验收时所需数据
     * @param id 产品id
     * @return EquipmentProduct
     */
    EquipmentProduct selectTechnologyAcceptanceInfo(@Param("id") String id);
}