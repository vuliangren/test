package com.welearn.service.impl;

import com.welearn.application.ApplicationService;
import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.equipment.RepairPartReplaceStatusConst;
import com.welearn.dictionary.equipment.RepairRequestStatusConst;
import com.welearn.dictionary.equipment.SparePartPriceType;
import com.welearn.dictionary.procurement.CapitalSourceConst;
import com.welearn.dictionary.procurement.ProcurementDetailStatusConst;
import com.welearn.dictionary.procurement.ProcurementTypeConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.po.equipment.SparePartType;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.equipment.SparePartTypeQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.equipment.RepairReplacementFeignClient;
import com.welearn.feign.equipment.SparePartTypeFeignClient;
import com.welearn.feign.notify.NoticeFeignClient;
import com.welearn.feign.procurement.ProcurementDetailFeignClient;
import com.welearn.service.ApprovalApplicationService;
import com.welearn.service.HighPricePartProcurementHandlerService;
import com.welearn.service.PartProcurementHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description : 高价配件采购申请
 * Created by Setsuna Jin on 2019/2/15.
 */
@Service
public class HighPricePartProcurementHandlerServiceImpl extends PartProcurementHandlerServiceImpl
        implements HighPricePartProcurementHandlerService {

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(RepairReplacement content) {
        return String.format("高价配件:%s-%s采购申请", content.getName(), content.getSpecification());
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.HIGH_PRICE_PART_PROCUREMENT;
    }

}
