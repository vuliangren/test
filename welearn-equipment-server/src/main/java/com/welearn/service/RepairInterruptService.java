package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairInterrupt;
import com.welearn.entity.qo.equipment.RepairInterruptQueryCondition;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Description : RepairInterruptService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairInterruptService extends BaseService<RepairInterrupt, RepairInterruptQueryCondition>{

    /**
     * 当申请审批通过后执行的回调
     * @param contentId     申请内容id
     */
    void afterApplicationPass(@NotBlank String contentId);

    /**
     * 当申请审批失败后执行的回调
     * @param contentId     申请内容id
     */
    void afterApplicationReject(@NotBlank String contentId);

    /**
     * 自动提交维修中止申请
     * @param repairInterrupt 维修中止申请
     * @return 申请
     */
    ApprovalApplication repairInterruptAutoSubmit(@Valid RepairInterrupt repairInterrupt, @EntityCheck(checkId = true) User user);
}