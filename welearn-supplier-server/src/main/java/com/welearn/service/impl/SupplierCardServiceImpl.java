package com.welearn.service.impl;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.supplier.SupplierCard;
import com.welearn.entity.qo.supplier.SupplierCardQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.generator.ControllerGenerator;
import com.welearn.mapper.SupplierCardMapper;
import com.welearn.service.SupplierCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description : SupplierCardService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SupplierCardServiceImpl extends BaseServiceImpl<SupplierCard,SupplierCardQueryCondition,SupplierCardMapper>
        implements SupplierCardService{
    
    @Autowired
    private SupplierCardMapper supplierCardMapper;
    
    @Override
    SupplierCardMapper getMapper() {
        return supplierCardMapper;
    }

    /**
     * 如果不存在则创建
     *
     * @param supplierCard 供应商卡片
     */
    @Override
    public void createIfNotExist(SupplierCard supplierCard) {
        SupplierCardQueryCondition condition = new SupplierCardQueryCondition();
        condition.setNameCn(supplierCard.getNameCn());
        condition.setIsEnable(true);
        List<SupplierCard> result = this.search(condition);
        if (Objects.nonNull(result) && result.isEmpty()){
            this.create(supplierCard);
        }
    }
}
