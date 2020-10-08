package com.welearn.service;

import com.welearn.entity.po.supplier.SupplierCard;
import com.welearn.entity.qo.supplier.SupplierCardQueryCondition;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Description : SupplierCardService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SupplierCardService extends BaseService<SupplierCard, SupplierCardQueryCondition>{

    /**
     * 如果不存在则创建
     * @param supplierCard 供应商卡片
     */
    void createIfNotExist(@NotNull SupplierCard supplierCard);
}