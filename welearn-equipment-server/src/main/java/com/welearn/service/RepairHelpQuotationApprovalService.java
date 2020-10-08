package com.welearn.service;

import com.welearn.entity.po.equipment.RepairHelpQuotationApproval;
import com.welearn.entity.qo.equipment.RepairHelpQuotationApprovalQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : RepairHelpQuotationApprovalService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairHelpQuotationApprovalService extends BaseService<RepairHelpQuotationApproval, RepairHelpQuotationApprovalQueryCondition>{
    /**
     * 当申请通过审批后执行的回调
     * @param contentId     申请内容id
     */
    void afterApplicationPass(String contentId);

    /**
     * 当申请审批失败后执行的回调
     * @param contentId     申请内容id
     */
    void afterApplicationReject(String contentId);
}