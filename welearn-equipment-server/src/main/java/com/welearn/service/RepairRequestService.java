package com.welearn.service;

import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairCheck;
import com.welearn.entity.po.equipment.RepairRequest;
import com.welearn.entity.qo.equipment.RepairRequestQueryCondition;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Description : RepairRequestService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairRequestService extends BaseService<RepairRequest, RepairRequestQueryCondition>{

    /**
     * 取消报修
     * @param requestId 报修申请id
     * @param reason 取消原因
     */
    void cancel(@NotBlank String requestId, String reason, @NotNull User user);

    /**
     * 维修验收
     * @param repairCheck 验收结果
     * @param user 验收人
     */
    void check(@NotNull @Valid RepairCheck repairCheck, @EntityCheck(checkId = true) User user);

}