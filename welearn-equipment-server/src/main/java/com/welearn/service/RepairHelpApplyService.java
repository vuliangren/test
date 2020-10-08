package com.welearn.service;

import com.welearn.entity.po.equipment.RepairHelpApply;
import com.welearn.entity.qo.equipment.RepairHelpApplyQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : RepairHelpApplyService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairHelpApplyService extends BaseService<RepairHelpApply, RepairHelpApplyQueryCondition>{
    /**
     * 当申请通过审批后执行的回调
     * @param repairHelpApplyId     申请内容id
     */
    void afterApplicationPass(String repairHelpApplyId);

    /**
     * 当申请审批失败后执行的回调
     * @param repairHelpApplyId     申请内容id
     */
    void afterApplicationReject(String repairHelpApplyId);
}