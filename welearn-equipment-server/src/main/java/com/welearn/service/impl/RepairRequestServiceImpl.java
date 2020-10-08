package com.welearn.service.impl;

import com.welearn.dictionary.equipment.*;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.*;
import com.welearn.entity.qo.equipment.RepairDispatchInsideQueryCondition;
import com.welearn.entity.qo.equipment.RepairDispatchOutsideQueryCondition;
import com.welearn.entity.qo.equipment.RepairReplacementQueryCondition;
import com.welearn.entity.qo.equipment.RepairRequestQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.RepairCheckMapper;
import com.welearn.mapper.RepairReplacementMapper;
import com.welearn.mapper.RepairRequestMapper;
import com.welearn.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.equipment.RepairRequestStatusConst.*;

/**
 * Description : RepairRequestService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairRequestServiceImpl extends BaseServiceImpl<RepairRequest,RepairRequestQueryCondition,RepairRequestMapper>
        implements RepairRequestService{
    
    @Autowired
    private RepairRequestMapper repairRequestMapper;

    @Autowired
    private RepairPreceptService repairPreceptService;

    @Autowired
    private RepairDispatchInsideService repairDispatchInsideService;

    @Autowired
    private RepairReplacementMapper repairReplacementMapper;

    @Autowired
    private RepairDispatchOutsideService repairDispatchOutsideService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private RepairCheckService repairCheckService;

    @Override
    RepairRequestMapper getMapper() {
        return repairRequestMapper;
    }


    @Override @Transactional
    public RepairRequest create(RepairRequest repairRequest) {
        // 创建报修信息
        repairRequest = super.create(repairRequest);
        // 更新报预案的命中数
        RepairPrecept precept = repairPreceptService.select(repairRequest.getPreceptId());
        precept.setHitCount(precept.getHitCount() + 1);
        repairPreceptService.update(precept);
        // TODO: 报修信息 根据服务公司可进行转移
        Equipment equipment = equipmentService.select(repairRequest.getEquipmentId());
        if (Objects.isNull(equipment) || equipment.getEquipmentStatus() > EquipmentStatusConst.RUNNING.ordinal())
            throw new BusinessVerifyFailedException("报修设备状态非法");
        equipment.setEquipmentStatus(EquipmentStatusConst.FAULT.ordinal());
        equipment.setRepairStatus(EquipmentRepairStatusConst.UN_DISPATCH.ordinal());
        equipmentService.update(equipment);
        return repairRequest;
    }

    /**
     * 取消报修
     *
     * @param requestId 报修申请id
     * @param reason    取消原因
     * @param user      取消人
     */
    @Override
    public void cancel(String requestId, String reason, User user) {
        // 要求 0-待派工 1-待重派 2-待领工 或 8-待取消 状态 下的报修 才可取消
        RepairRequest repairRequest = this.select(requestId);
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() >= UN_REPAIR.ordinal() && repairRequest.getStatus() != UN_CANCEL.ordinal())
            throw new BusinessVerifyFailedException("repairRequest 非法 或 状态异常");
        if (Objects.isNull(user) || !user.getDepartmentId().equals(repairRequest.getReporterDepartmentId()))
            throw new BusinessVerifyFailedException("您无权限取消该报修");
        // 处理报修取消备注
        String remark = String.format("部门人员 - %s 取消了该报修", user.getName());
        if (StringUtils.isNotBlank(reason))
            remark += String.format(", 取消原因:\n%s", reason);
        remark += "\n";
        // 需要将派工取消掉
        RepairDispatchInsideQueryCondition condition = new RepairDispatchInsideQueryCondition();
        condition.setRequestId(requestId);
        condition.setIsEnable(true);
        condition.setStatus(InsideDispatchStatusConst.UN_RECEIVE.ordinal());
        List<RepairDispatchInside> dispatchInsides = repairDispatchInsideService.search(condition);
        for (RepairDispatchInside dispatchInside : dispatchInsides) {
            dispatchInside.setStatus(InsideDispatchStatusConst.CANCEL.ordinal());
            // TODO: 建议以后添加备注字段信息
            dispatchInside.setReDispatchReason(remark);
            repairDispatchInsideService.update(dispatchInside);
            // TODO: 消息通知工程师派工已取消
        }
        // 修改设备状态
        Equipment equipment = new Equipment();
        equipment.setId(repairRequest.getEquipmentId());
        equipment.setEquipmentStatus(EquipmentStatusConst.RUNNING.ordinal());
        equipment.setRepairStatus(EquipmentRepairStatusConst.OK.ordinal());
        equipmentService.update(equipment);
        // 更新报修信息
        repairRequest.setRemark(StringUtils.isBlank(repairRequest.getRemark()) ? remark : repairRequest.getRemark() + remark);
        repairRequest.setIsCancel(true);
        repairRequest.setStatus(CANCEL.ordinal());
        repairRequest.setFinishedAt(new Date());
        this.update(repairRequest);
    }

    /**
     * 维修完成时对 待更换 状态的 配件更换申请 进行更新
     * @param requestId 报修id
     */
    private void finishRepairReplacement(String requestId){
        RepairReplacementQueryCondition condition = new RepairReplacementQueryCondition();
        condition.setIsEnable(true);
        condition.setRequestId(requestId);
        condition.setStatus(RepairPartReplaceStatusConst.UN_REPLACE.ordinal());
        List<RepairReplacement> repairReplacements = repairReplacementMapper.selectByCondition(condition);
        for (RepairReplacement repairReplacement : repairReplacements) {
            repairReplacement.setStatus(RepairPartReplaceStatusConst.FINISH.ordinal());
            repairReplacementMapper.updateByPK(repairReplacement);
        }
    }

    /**
     * 维修验收
     *
     * @param repairCheck 验收结果
     * @param user    验收人
     */
    @Override
    public void check(RepairCheck repairCheck, User user) {
        RepairRequest repairRequest = this.select(repairCheck.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != UN_CHECK.ordinal())
            throw new BusinessVerifyFailedException("repairRequest 非法 或 状态异常");
        if (!user.getId().equals(repairCheck.getAcceptorId()))
            throw new BusinessVerifyFailedException("repairCheck.userId 非法");
        repairCheck.setAcceptorName(user.getName());
        // 验收通过
        if (repairCheck.getResult()){
            if (StringUtils.isBlank(repairCheck.getSignatureId()))
                throw new BusinessVerifyFailedException("repairCheck 无签字信息");
            repairRequest.setFinishedAt(new Date());
            repairRequest.setStatus(RepairRequestStatusConst.UN_EVALUATE.ordinal());
            // 更新配件更换状态
            this.finishRepairReplacement(repairRequest.getId());
            // 更新内部 外部派工的信息
            repairDispatchOutsideService.check(repairRequest.getId(), true);
            repairDispatchInsideService.check(repairRequest.getId(), true);
            // 更新设备状态
            Equipment equipment = equipmentService.select(repairRequest.getEquipmentId());
            if (Objects.isNull(equipment) || equipment.getEquipmentStatus() != EquipmentStatusConst.FAULT.ordinal())
                throw new BusinessVerifyFailedException("报修设备 状态非法");
            equipment.setEquipmentStatus(EquipmentStatusConst.RUNNING.ordinal());
            equipment.setRepairStatus(EquipmentRepairStatusConst.OK.ordinal());
            equipmentService.update(equipment);
        }
        // 验收失败
        else {
            if (StringUtils.isBlank(repairCheck.getRemark()))
                throw new BusinessVerifyFailedException("repairCheck 验收失败需有备注说明");
            repairRequest.setStatus(RepairRequestStatusConst.REPAIRING.ordinal());
            // 将外部派工的维修结果回滚
            repairDispatchOutsideService.check(repairRequest.getId(), false);
            repairDispatchInsideService.check(repairRequest.getId(), false);
        }
        this.update(repairRequest);
        repairCheckService.create(repairCheck);
    }

}
