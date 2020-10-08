package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.qo.equipment.RepairReplacementQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Description : RepairReplacementService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairReplacementService extends BaseService<RepairReplacement, RepairReplacementQueryCondition>{

    /**
     * 取消配件更换申请
     * @param replacement 配件更换申请
     */
    void cancel(@NotNull RepairReplacement replacement);

    /**
     * 取消配件更换申请
     * @param replacementId 配件更换申请id
     */
    void cancel(@NotBlank String replacementId);

    /**
     * 廉价配件采购完成
     * @param replacementId 配件更换申请id
     * @param price         实际采购价格
     */
    void procurementFinish(@NotBlank String replacementId, @NotNull Double price);

    /**
     * 自动根据维修配件更换信息 提交更换申请
     * @param repairReplacement 维修配件更换
     * @return 申请
     */
    ApprovalApplication replacementApplyAutoSubmit(@Valid RepairReplacement repairReplacement, User user);

    /**
     * 手动确认取消配件更换申请, 需签字确认
     *
     * @param replacementId 配件更换申请id
     * @param signatureId 签字id
     */
    void cancelCheck(String replacementId, String signatureId);
}