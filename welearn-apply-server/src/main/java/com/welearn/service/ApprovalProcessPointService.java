package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.qo.apply.ApprovalProcessPointQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ApprovalProcessPointService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ApprovalProcessPointService extends BaseService<ApprovalProcessPoint, ApprovalProcessPointQueryCondition>{

    /**
     * 获取申请的审批节点数量
     * @param companyId 公司id
     * @param code 申请的编码
     * @return 节点数量
     */
    Integer pointCount(@NotBlank String companyId, @NotBlank String code);
}