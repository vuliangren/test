package com.welearn.service.impl;

import com.welearn.dictionary.equipment.RepairHelpApplyStatusConst;
import com.welearn.entity.po.equipment.RepairHelpApply;
import com.welearn.entity.po.equipment.RepairRequest;
import com.welearn.entity.qo.equipment.RepairHelpApplyQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.RepairHelpApplyMapper;
import com.welearn.mapper.RepairRequestMapper;
import com.welearn.service.RepairHelpApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.welearn.dictionary.equipment.RepairRequestStatusConst.UN_REPAIR;

/**
 * Description : RepairHelpApplyService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairHelpApplyServiceImpl extends BaseServiceImpl<RepairHelpApply,RepairHelpApplyQueryCondition,RepairHelpApplyMapper>
        implements RepairHelpApplyService{

    @Autowired
    private RepairRequestMapper repairRequestMapper;

    @Autowired
    private RepairHelpApplyMapper repairHelpApplyMapper;
    
    @Override
    RepairHelpApplyMapper getMapper() {
        return repairHelpApplyMapper;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param repairHelpApplyId 申请内容id
     */
    @Override
    public void afterApplicationPass(String repairHelpApplyId) {
        RepairHelpApply helpApply = this.select(repairHelpApplyId);
        if (Objects.isNull(helpApply))
            throw new BusinessVerifyFailedException("repairHelpApplyId 非法");
        helpApply.setStatus(RepairHelpApplyStatusConst.SUCCESS.ordinal());
        this.update(helpApply);
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param repairHelpApplyId 申请内容id
     */
    @Override @Transactional
    public void afterApplicationReject(String repairHelpApplyId) {
        RepairHelpApply helpApply = this.select(repairHelpApplyId);
        if (Objects.isNull(helpApply))
            throw new BusinessVerifyFailedException("repairHelpApplyId 非法");
        helpApply.setStatus(RepairHelpApplyStatusConst.FAILED.ordinal());
        this.update(helpApply);
        // 修改报修状态为 维修中
        RepairRequest repairRequest = repairRequestMapper.selectByPK(helpApply.getRequestId());
        if (Objects.isNull(repairRequest))
            throw new BusinessVerifyFailedException("RepairHelpApply.requestId 非法");
        repairRequest.setStatus(UN_REPAIR.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
    }
}
