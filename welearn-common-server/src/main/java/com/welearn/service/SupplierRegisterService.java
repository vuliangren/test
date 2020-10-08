package com.welearn.service;

import com.welearn.entity.po.common.SupplierRegister;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.SupplierRegisterQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Description : SupplierRegisterService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SupplierRegisterService extends BaseService<SupplierRegister, SupplierRegisterQueryCondition>{

    /**
     * 供应商注册审批
     * @param supplierRegisterId 注册申请 id
     * @param isPassed 审批是否通过
     * @param approver 审批人
     * @param result 审批结果描述
     */
    void approval(@NotBlank String supplierRegisterId, @NotNull Boolean isPassed, @NotNull User approver, String result);
}