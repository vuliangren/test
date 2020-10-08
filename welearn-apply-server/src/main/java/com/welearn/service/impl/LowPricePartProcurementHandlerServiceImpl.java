package com.welearn.service.impl;

import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.service.LowPricePartProcurementHandlerService;
import com.welearn.service.OverPricePartProcurementHandlerService;
import org.springframework.stereotype.Service;

/**
 * Description : 低价配件采购申请
 * Created by Setsuna Jin on 2019/2/15.
 */
@Service
public class LowPricePartProcurementHandlerServiceImpl extends PartProcurementHandlerServiceImpl
        implements LowPricePartProcurementHandlerService {

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(RepairReplacement content) {
        return String.format("低价配件:%s-%s采购申请", content.getName(), content.getSpecification());
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.LOW_PRICE_PART_PROCUREMENT;
    }
}
