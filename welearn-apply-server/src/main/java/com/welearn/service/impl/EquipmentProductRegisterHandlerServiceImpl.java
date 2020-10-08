package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.equipment.EquipmentProductStatusConst;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.equipment.EquipmentProductFeignClient;
import com.welearn.service.EquipmentProductRegisterHandlerService;
import com.welearn.util.UUIDGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Description : 设备产品注册 处理
 * Created by Setsuna Jin on 2018/11/12.
 */
@Service
public class EquipmentProductRegisterHandlerServiceImpl extends ApplicationServiceImpl<EquipmentProduct> implements EquipmentProductRegisterHandlerService {

    @Autowired
    private EquipmentProductFeignClient equipmentProductFeignClient;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(EquipmentProduct content) {
        if (StringUtils.isNotBlank(content.getCurrentId())){
            // 验证 更改设备内容时 的 currentId 是否合法
            String currentId = content.getCurrentId();
            EquipmentProduct current = equipmentProductFeignClient.select(currentId).getData();
            if (Objects.isNull(current))
                throw new BusinessVerifyFailedException("currentId 非法");
        }
        content.setIsEnable(false);
        // 通过feignClient 创建的对象 内部默认是没有id 的
        EquipmentProduct equipmentProduct = equipmentProductFeignClient.create(content).getData();
        content.setId(equipmentProduct.getId());
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        equipmentProductFeignClient.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(EquipmentProduct content) {
        equipmentProductFeignClient.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public EquipmentProduct selectContent(String contentId) {
        return equipmentProductFeignClient.select(contentId).getData();
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(EquipmentProduct content) {
        return String.format("%s %s %s", content.getEquipmentTypeName(), content.getModelNumber(), content.getManufacturerName());
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.EQUIPMENT_PRODUCT_REGISTER;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) {
        EquipmentProduct product = this.selectContent(contentId);
        product.setStatus(EquipmentProductStatusConst.SUCCESS.ordinal());
        product.setApplicationId(applicationId);
        // 根据现有内容 更新 新设备产品
        String currentId = product.getCurrentId();
        if (StringUtils.isNotBlank(currentId)){
            // 更新现有内容
            equipmentProductFeignClient.update(product);
            // 更新再用设备产品
            product.setId(currentId);
            product.setCurrentId(null);
            product.setIsEnable(true);
            equipmentProductFeignClient.update(product);
        }
        // 根据现有内容 创建 新设备产品
        else {
            String productId = UUIDGenerator.get(EquipmentProduct.class);
            product.setCurrentId(productId);
            // 更新现有内容
            equipmentProductFeignClient.update(product);
            // 创建新的设备产品
            product.setId(productId);
            product.setCurrentId(null);
            product.setIsEnable(true);
            equipmentProductFeignClient.create(product);
        }
        // 检查产品关联的设备, 修改其基本信息
        if (StringUtils.isNotBlank(currentId)) {
            equipmentProductFeignClient.updateEquipments(currentId);
        }
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) {
        EquipmentProduct product = this.selectContent(contentId);
        product.setApplicationId(applicationId);
        product.setStatus(EquipmentProductStatusConst.FAILED.ordinal());
    }
}
