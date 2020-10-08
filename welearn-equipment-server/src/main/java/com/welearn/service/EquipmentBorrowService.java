package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.po.equipment.EquipmentBorrowAccessory;
import com.welearn.entity.qo.equipment.EquipmentBorrowQueryCondition;
import com.welearn.entity.vo.response.equipment.EquipmentBorrowInfo;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : EquipmentBorrowService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentBorrowService extends BaseService<EquipmentBorrow, EquipmentBorrowQueryCondition>{

    /**
     * 根据条件查询设备详情
     * @param condition 查询条件
     * @return 设备借用信息和设备信息
     */
    List<EquipmentBorrowInfo> searchInfo(EquipmentBorrowQueryCondition condition);

    /**
     * 设备借用申请自动提交
     * @param equipmentBorrow 设备报废申请
     * @return 申请信息
     */
    ApprovalApplication equipmentBorrowApplyAutoSubmit(@NotNull @Valid EquipmentBorrow equipmentBorrow, @EntityCheck(checkId = true) User user);

    /**
     * 当设备借用申请审批通过后
     * @param borrowId 借用id
     */
    void afterBorrowApplicationPass(String borrowId);

    /**
     * 当设备借用申请审批失败后
     * @param borrowId 借用id
     */
    void afterBorrowApplicationReject(String borrowId);

    /**
     * 借出确认
     * @param borrow 设备借用信息
     * @param accessories 设备附件借用清单
     */
    void loanOut(@NotNull @Valid EquipmentBorrow borrow, @NotNull List<EquipmentBorrowAccessory> accessories);

    /**
     * 借用归还
     * @param borrow 设备借用信息
     * @param accessories 设备附件借用清单
     */
    void giveBack(@NotNull @Valid EquipmentBorrow borrow, @NotNull List<EquipmentBorrowAccessory> accessories);

    /**
     * 取消借用
     * @param borrowId 借用id
     * @param borrowId 取消借用原因
     */
    void cancel(@NotBlank String borrowId, @NotBlank String reason);
}