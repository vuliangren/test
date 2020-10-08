package com.welearn.service.impl;

import com.welearn.dictionary.equipment.OutsideDispatchResultConst;
import com.welearn.dictionary.equipment.RepairHelpQuotationApprovalStatusConst;
import com.welearn.entity.po.equipment.RepairDispatchOutside;
import com.welearn.entity.po.equipment.RepairHelpQuotationApproval;
import com.welearn.entity.qo.equipment.RepairHelpQuotationApprovalQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.RepairDispatchOutsideMapper;
import com.welearn.mapper.RepairHelpQuotationApprovalMapper;
import com.welearn.service.RepairDispatchOutsideService;
import com.welearn.service.RepairHelpQuotationApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Description : RepairHelpQuotationApprovalService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairHelpQuotationApprovalServiceImpl extends BaseServiceImpl<RepairHelpQuotationApproval,RepairHelpQuotationApprovalQueryCondition,RepairHelpQuotationApprovalMapper>
        implements RepairHelpQuotationApprovalService{
    
    @Autowired
    private RepairHelpQuotationApprovalMapper repairHelpQuotationApprovalMapper;

    @Autowired
    private RepairDispatchOutsideService repairDispatchOutsideService;

    @Override
    RepairHelpQuotationApprovalMapper getMapper() {
        return repairHelpQuotationApprovalMapper;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param contentId 申请内容id
     */
    @Override
    public void afterApplicationPass(String contentId) {
        RepairHelpQuotationApproval quotationApproval = this.select(contentId);
        if (Objects.isNull(quotationApproval))
            throw new BusinessVerifyFailedException("repairHelpQuotationApprovalId 非法");
        quotationApproval.setStatus(RepairHelpQuotationApprovalStatusConst.SUCCESS.ordinal());
        this.update(quotationApproval);
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param contentId 申请内容id
     */
    @Override
    public void afterApplicationReject(String contentId) {
        RepairHelpQuotationApproval quotationApproval = this.select(contentId);
        if (Objects.isNull(quotationApproval))
            throw new BusinessVerifyFailedException("repairHelpQuotationApprovalId 非法");
        quotationApproval.setStatus(RepairHelpQuotationApprovalStatusConst.FAILED.ordinal());
        this.update(quotationApproval);
    }
}
