package com.welearn.service.impl;

import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.equipment.EquipmentBorrowApplyStatusConst;
import com.welearn.dictionary.equipment.EquipmentBorrowStatusConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.po.equipment.EquipmentBorrowAccessory;
import com.welearn.entity.qo.equipment.EquipmentBorrowQueryCondition;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.entity.vo.response.equipment.EquipmentBorrowInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.apply.EquipmentBorrowApplyFeignClient;
import com.welearn.mapper.EquipmentBorrowMapper;
import com.welearn.service.EquipmentBorrowAccessoryService;
import com.welearn.service.EquipmentBorrowService;
import com.welearn.service.EquipmentService;
import com.welearn.util.PaginateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description : EquipmentBorrowService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentBorrowServiceImpl extends BaseServiceImpl<EquipmentBorrow,EquipmentBorrowQueryCondition,EquipmentBorrowMapper>
        implements EquipmentBorrowService{
    
    @Autowired
    private EquipmentBorrowMapper equipmentBorrowMapper;

    @Autowired
    private EquipmentBorrowAccessoryService equipmentBorrowAccessoryService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentBorrowApplyFeignClient equipmentBorrowApplyFeignClient;

    @Override
    EquipmentBorrowMapper getMapper() {
        return equipmentBorrowMapper;
    }

    /**
     * 根据条件查询设备详情
     *
     * @param condition 查询条件
     * @return 设备借用信息和设备信息
     */
    @Override
    public List<EquipmentBorrowInfo> searchInfo(EquipmentBorrowQueryCondition condition) {
        return PaginateUtil.page(() -> equipmentBorrowMapper.searchInfo(condition));
    }

    /**
     * 设备借用申请自动提交
     *
     * @param equipmentBorrow 设备报废申请
     * @param user 申请人
     * @return 申请信息
     */
    @Override
    public ApprovalApplication equipmentBorrowApplyAutoSubmit(EquipmentBorrow equipmentBorrow, User user) {
        Apply<EquipmentBorrow> request = new Apply<>();
        request.setContent(equipmentBorrow);
        request.setApplicantId(user.getId());
        request.setType(ApplicationTypeConst.FORM_APPLICATION.ordinal());
        return equipmentBorrowApplyFeignClient.apply(request).getData();
    }

    /**
     * 当设备借用申请审批通过后
     *
     * @param borrowId 借用id
     */
    @Override
    public void afterBorrowApplicationPass(String borrowId) {
        EquipmentBorrow equipmentBorrow = this.select(borrowId);
        if (Objects.isNull(equipmentBorrow) || equipmentBorrow.getStatus() != EquipmentBorrowApplyStatusConst.UN_APPROVAL.ordinal())
            throw new BusinessVerifyFailedException("borrowId 非法 或 状态异常");
        equipmentBorrow.setStatus(EquipmentBorrowApplyStatusConst.UN_LOAN_OUT.ordinal());
        this.update(equipmentBorrow);
        // 更新设备状态
        Equipment equipment = new Equipment();
        equipment.setId(equipmentBorrow.getEquipmentId());
        equipment.setBorrowStatus(EquipmentBorrowStatusConst.UN_LOAN_OUT.ordinal());
        equipmentService.update(equipment);
    }

    /**
     * 当设备借用申请审批失败后
     *
     * @param borrowId 借用id
     */
    @Override
    public void afterBorrowApplicationReject(String borrowId) {
        EquipmentBorrow equipmentBorrow = this.select(borrowId);
        if (Objects.isNull(equipmentBorrow) || equipmentBorrow.getStatus() != EquipmentBorrowApplyStatusConst.UN_APPROVAL.ordinal())
            throw new BusinessVerifyFailedException("borrowId 非法 或 状态异常");
        equipmentBorrow.setStatus(EquipmentBorrowApplyStatusConst.APPROVAL_FAILED.ordinal());
        this.update(equipmentBorrow);
        // 更新设备状态
        Equipment equipment = new Equipment();
        equipment.setId(equipmentBorrow.getEquipmentId());
        equipment.setBorrowStatus(EquipmentBorrowStatusConst.OK.ordinal());
        equipmentService.update(equipment);
    }

    /**
     * 借出确认
     * @param borrow 设备借用信息
     * @param accessories 设备附件借用清单
     */
    @Override
    public void loanOut(EquipmentBorrow borrow, List<EquipmentBorrowAccessory> accessories) {
        EquipmentBorrow equipmentBorrow = this.select(borrow.getId());
        if (Objects.isNull(equipmentBorrow) || equipmentBorrow.getStatus() != EquipmentBorrowApplyStatusConst.UN_LOAN_OUT.ordinal())
            throw new BusinessVerifyFailedException("borrow 非法 或 状态异常");
        equipmentBorrow.setStatus(EquipmentBorrowApplyStatusConst.UN_GIVE_BACK.ordinal());
        equipmentBorrow.setLoanOutAt(new Date());
        equipmentBorrow.setAccessoryCount(accessories.size());
        // 借入单和备注
        equipmentBorrow.setLoanOutBill(borrow.getLoanOutBill());
        equipmentBorrow.setLoanOutRemark(borrow.getLoanOutRemark());
        // 借入人员信息
        equipmentBorrow.setBorrowUserId(borrow.getBorrowUserId());
        equipmentBorrow.setBorrowUserName(borrow.getBorrowUserName());
        equipmentBorrow.setBorrowUserSignatureId(borrow.getBorrowUserSignatureId());
        // 借出人员信息
        equipmentBorrow.setLoanOutUserId(borrow.getLoanOutUserId());
        equipmentBorrow.setLoanOutUserName(borrow.getLoanOutUserName());
        equipmentBorrow.setLoanOutUserSignatureId(borrow.getLoanOutUserSignatureId());
        this.update(equipmentBorrow);
        // 添加设备附件借用清单
        for (EquipmentBorrowAccessory accessory : accessories) {
            accessory.setEquipmentId(borrow.getEquipmentId());
            accessory.setBorrowId(borrow.getId());
            accessory.setBorrowUserId(borrow.getBorrowUserId());
            accessory.setLoanOutUserId(borrow.getLoanOutUserId());
            equipmentBorrowAccessoryService.create(accessory);
        }
        // 更新设备状态
        Equipment equipment = new Equipment();
        equipment.setId(equipmentBorrow.getEquipmentId());
        equipment.setBorrowStatus(EquipmentBorrowStatusConst.UN_GIVE_BACK.ordinal());
        equipmentService.update(equipment);
        // TODO: 通知借入人员 设备已借出请及时归还
    }

    /**
     * 借用归还
     * @param borrow 设备借用信息
     * @param accessories 设备附件借用清单
     */
    @Override
    public void giveBack(EquipmentBorrow borrow, List<EquipmentBorrowAccessory> accessories) {
        EquipmentBorrow equipmentBorrow = this.select(borrow.getId());
        if (Objects.isNull(equipmentBorrow) || equipmentBorrow.getStatus() != EquipmentBorrowApplyStatusConst.UN_GIVE_BACK.ordinal() ||
            borrow.getStatus() < EquipmentBorrowApplyStatusConst.CHECK_SUCCESS.ordinal() ||
            borrow.getStatus() > EquipmentBorrowApplyStatusConst.CHECK_FAILED.ordinal()
        )
            throw new BusinessVerifyFailedException("borrow 非法 或 状态异常");
        equipmentBorrow.setStatus(borrow.getStatus());
        // 归还信息
        equipmentBorrow.setGiveBackBill(borrow.getGiveBackBill());
        equipmentBorrow.setGiveBackRealAt(new Date());
        equipmentBorrow.setGiveBackUserId(borrow.getGiveBackUserId());
        equipmentBorrow.setGiveBackUserName(borrow.getGiveBackUserName());
        equipmentBorrow.setGiveBackUserSignatureId(borrow.getGiveBackUserSignatureId());
        // 验收信息
        equipmentBorrow.setCheckRemark(borrow.getCheckRemark());
        equipmentBorrow.setCheckResult(borrow.getCheckResult());
        equipmentBorrow.setCheckUserId(borrow.getCheckUserId());
        equipmentBorrow.setCheckUserName(borrow.getCheckUserName());
        equipmentBorrow.setCheckUserSignatureId(borrow.getCheckUserSignatureId());
        equipmentBorrow.setSatisfaction(borrow.getSatisfaction());
        // 借用花费计算
        long borrowHours = (equipmentBorrow.getGiveBackRealAt().getTime() - equipmentBorrow.getLoanOutAt().getTime()) / 1000 / 3600;
        equipmentBorrow.setDayCount(borrowHours * 1.0 / 24);
        equipmentBorrow.setSumPrice(equipmentBorrow.getDayCount() * equipmentBorrow.getDayPrice());
        this.update(equipmentBorrow);
        // 更新设备附件借用清单
        for (EquipmentBorrowAccessory accessory : accessories) {
            accessory.setCheckedAt(equipmentBorrow.getGiveBackRealAt());
            accessory.setCheckUserId(equipmentBorrow.getCheckUserId());
            accessory.setCheckUserName(equipmentBorrow.getCheckUserName());
            equipmentBorrowAccessoryService.update(accessory);
        }
        // 更新设备状态
        Equipment equipment = new Equipment();
        equipment.setId(borrow.getEquipmentId());
        equipment.setBorrowStatus(EquipmentBorrowStatusConst.OK.ordinal());
        equipmentService.update(equipment);
        // TODO: 通知归还人员 设备已归还 成功 或 失败 及 说明
    }

    /**
     * 取消借用
     * 只有当状态为待领取时可取消
     * @param borrowId 借用id
     */
    @Override
    public void cancel(String borrowId, String reason) {
        EquipmentBorrow equipmentBorrow = this.select(borrowId);
        if (Objects.isNull(equipmentBorrow) || equipmentBorrow.getStatus() != EquipmentBorrowApplyStatusConst.UN_LOAN_OUT.ordinal())
            throw new BusinessVerifyFailedException("borrow 非法 或 状态异常");
        equipmentBorrow.setStatus(EquipmentBorrowApplyStatusConst.CANCEL.ordinal());
        equipmentBorrow.setBorrowRemark(reason);
        this.update(equipmentBorrow);
        // 更新设备状态
        Equipment equipment = new Equipment();
        equipment.setId(equipmentBorrow.getEquipmentId());
        equipment.setBorrowStatus(EquipmentBorrowStatusConst.OK.ordinal());
        equipmentService.update(equipment);
        // TODO: 通知借出科室 设备借用已取消
    }
}
