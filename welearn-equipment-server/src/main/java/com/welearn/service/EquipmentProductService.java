package com.welearn.service;

import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.qo.equipment.EquipmentProductQueryCondition;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Description : EquipmentProductService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentProductService extends BaseService<EquipmentProduct, EquipmentProductQueryCondition>{
    /**
     * 根据 productIds 查询技术验收需要的参数
     * @param productIds productIds
     * @return Map<String, EquipmentProduct>
     */
    Map<String, EquipmentProduct> selectTechnologyAcceptanceInfo(List<String> productIds);

    /**
     * 更新 产品关联的设备的信息, 一般用于产品信息更新审批通过后执行
     * @param productId 产品id
     */
    void updateEquipments(String productId);
}