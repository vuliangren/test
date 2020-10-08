package com.welearn.service.impl;

import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.equipment.RepairPartReplaceStatusConst;
import com.welearn.dictionary.equipment.SparePartPriceType;
import com.welearn.dictionary.procurement.ProcurementDetailStatusConst;
import com.welearn.dictionary.procurement.ProcurementStatusConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairCostItem;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.equipment.RepairReplacementQueryCondition;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.apply.ApprovalApplicationFeignClient;
import com.welearn.feign.apply.HighPricePartProcurementApplicationFeignClient;
import com.welearn.feign.apply.LowPricePartProcurementApplicationFeignClient;
import com.welearn.feign.apply.OverPricePartProcurementApplicationFeignClient;
import com.welearn.feign.procurement.ProcurementDetailFeignClient;
import com.welearn.mapper.RepairReplacementMapper;
import com.welearn.service.RepairCostItemService;
import com.welearn.service.RepairReplacementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Objects;

import static com.welearn.dictionary.equipment.RepairPartReplaceStatusConst.*;

/**
 * Description : RepairReplacementService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class RepairReplacementServiceImpl extends BaseServiceImpl<RepairReplacement,RepairReplacementQueryCondition,RepairReplacementMapper>
        implements RepairReplacementService{
    
    @Autowired
    private RepairReplacementMapper repairReplacementMapper;

    @Autowired
    private RepairCostItemService repairCostItemService;

    @Autowired
    private ProcurementDetailFeignClient procurementDetailFeignClient;

    @Autowired
    private HighPricePartProcurementApplicationFeignClient highPricePartProcurementApplicationFeignClient;

    @Autowired
    private LowPricePartProcurementApplicationFeignClient lowPricePartProcurementApplicationFeignClient;

    @Autowired
    private OverPricePartProcurementApplicationFeignClient overPricePartProcurementApplicationFeignClient;

    @Override
    RepairReplacementMapper getMapper() {
        return repairReplacementMapper;
    }

    /**
     * 取消配件更换申请
     * 尽量去取消掉, 如无法取消则标记为待取消, 等待用户手动取消
     * @param replacement 配件更换申请
     */
    @Override
    public void cancel(RepairReplacement replacement) {
        if (replacement.getStatus() < UN_STOCK_IN.ordinal()){
            if (replacement.getType() == SparePartPriceType.BARGAIN.ordinal()){
                replacement.setStatus(CANCEL.ordinal());
            } else {
                if (Objects.nonNull(replacement.getApplyId())){
                    if (replacement.getType() == SparePartPriceType.LOW.ordinal())
                        lowPricePartProcurementApplicationFeignClient.cancel(replacement.getApplyId());
                    else if (replacement.getType() == SparePartPriceType.HIGH.ordinal())
                        highPricePartProcurementApplicationFeignClient.cancel(replacement.getApplyId());
                    else if (replacement.getType() == SparePartPriceType.OVER.ordinal())
                        overPricePartProcurementApplicationFeignClient.cancel(replacement.getApplyId());
                }
                // 已进入采购项目执行环节, 需手动取消
                if (Objects.nonNull(replacement.getProcurementId())){
                    replacement.setStatus(UN_CANCEL.ordinal());
                } else {
                    // 已进入待采购列表中, 标记为已取消状态
                    if (Objects.nonNull(replacement.getProcurementDetailId())){
                        ProcurementDetail procurementDetail = procurementDetailFeignClient.select(replacement.getProcurementDetailId()).getData();
                        if (Objects.nonNull(procurementDetail)){
                            procurementDetail.setStatus(ProcurementDetailStatusConst.CANCEL.ordinal());
                            procurementDetailFeignClient.update(procurementDetail);
                            // TODO 通知采购负责人 状态已取消
                        }
                    }
                }
                replacement.setStatus(CANCEL.ordinal());
            }
        } else if ( replacement.getStatus() < APPROVAL_FAIL.ordinal()){
            replacement.setStatus(UN_CANCEL.ordinal());
        }
        this.update(replacement);
    }

    /**
     * 取消配件更换申请
     *
     * @param replacementId 配件更换申请id
     */
    @Override
    public void cancel(String replacementId) {
        RepairReplacement repairReplacement = this.select(replacementId);
        if (Objects.isNull(repairReplacement) || !repairReplacement.getIsEnable())
            throw new BusinessVerifyFailedException("replacementId 非法");
        this.cancel(repairReplacement);
    }

    /**
     * 手动确认取消配件更换申请, 需签字确认
     *
     * @param replacementId 配件更换申请id
     * @param signatureId 签字id
     */
    @Override
    public void cancelCheck(String replacementId, String signatureId) {
        RepairReplacement repairReplacement = this.select(replacementId);
        if (Objects.isNull(repairReplacement) || !repairReplacement.getIsEnable() || repairReplacement.getStatus() != UN_CANCEL.ordinal())
            throw new BusinessVerifyFailedException("replacementId 非法 或 状态非法");
        // TODO: 待添加到 RepairReplacement 表中去
        log.info("工程师: %s 手动确认 取消了配件更换申请:%s, 签名id: %s", repairReplacement.getEngineerId(), replacementId, signatureId);
        repairReplacement.setStatus(CANCEL.ordinal());
        this.update(repairReplacement);
    }

    /**
     * 配件采购完成
     *
     * @param replacementId 配件更换申请id
     * @param price         实际采购价格
     */
    @Override @Transactional
    public void procurementFinish(String replacementId, Double price) {
        RepairReplacement replacement = this.select(replacementId);
        if (Objects.isNull(replacement) || replacement.getStatus() >= UN_REPLACE.ordinal())
            throw new BusinessVerifyFailedException("replacementId 非法 或 状态异常");
        // 更改配件更换状态为 待更换
        replacement.setStatus(UN_REPLACE.ordinal());
        this.update(replacement);
        // 添加维修消费记录
        repairCostItemService.createSparePartCostItem(replacement, price);
    }

    /**
     * 自动根据维修配件更换信息 提交更换申请
     *
     * @param repairReplacement 维修配件更换
     * @return 申请
     */
    @Override
    public ApprovalApplication replacementApplyAutoSubmit(RepairReplacement repairReplacement, User user) {
        repairReplacement.setStatus(RepairPartReplaceStatusConst.UN_APPROVAL.ordinal());
        // 提交入库申请
        Apply<RepairReplacement> request = new Apply<>();
        request.setContent(repairReplacement);
        request.setApplicantId(user.getId());
        request.setType(ApplicationTypeConst.FORM_APPLICATION.ordinal());
        if (repairReplacement.getType() == SparePartPriceType.LOW.ordinal())
            return lowPricePartProcurementApplicationFeignClient.apply(request).getData();
        else if (repairReplacement.getType() == SparePartPriceType.HIGH.ordinal())
            return highPricePartProcurementApplicationFeignClient.apply(request).getData();
        else if (repairReplacement.getType() == SparePartPriceType.OVER.ordinal())
            return overPricePartProcurementApplicationFeignClient.apply(request).getData();
        else
            throw new BusinessVerifyFailedException("repairReplacement.type 非法");
    }
}
