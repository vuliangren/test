package com.welearn.service.impl;

import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.qo.equipment.EquipmentProductQueryCondition;
import com.welearn.entity.qo.equipment.EquipmentQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.EquipmentMapper;
import com.welearn.mapper.EquipmentProductMapper;
import com.welearn.service.EquipmentProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description : EquipmentProductService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentProductServiceImpl extends BaseServiceImpl<EquipmentProduct,EquipmentProductQueryCondition,EquipmentProductMapper>
        implements EquipmentProductService{
    
    @Autowired
    private EquipmentProductMapper equipmentProductMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    EquipmentProductMapper getMapper() {
        return equipmentProductMapper;
    }

    /**
     * 根据 productIds 查询技术验收需要的参数
     *
     * @param productIds productIds
     * @return Map<String, EquipmentProduct>
     */
    @Override
    public Map<String, EquipmentProduct> selectTechnologyAcceptanceInfo(List<String> productIds) {
        return productIds.stream()
                .map(productId -> equipmentProductMapper.selectTechnologyAcceptanceInfo(productId))
                .collect(Collectors.toMap(BasePersistant::getId, product -> product));
    }

    /**
     * 更新 产品关联的设备的信息, 一般用于产品信息更新审批通过后执行
     *
     * @param productId 产品id
     */
    @Override
    public void updateEquipments(String productId) {
        EquipmentProduct product = this.select(productId);
        if (Objects.isNull(product) || !product.getIsEnable())
            throw new BusinessVerifyFailedException("productId 非法");
        EquipmentQueryCondition condition = new EquipmentQueryCondition();
        condition.setIsEnable(true);
        condition.setEquipmentProductId(productId);
        List<Equipment> equipments = equipmentMapper.selectByCondition(condition);
        equipments.forEach(equipment -> {
            Equipment e = new Equipment();
            e.updateWithProduct(product);
        });
    }
}
