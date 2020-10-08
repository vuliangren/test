package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.equipment.*;
import com.welearn.dictionary.notify.NoticeMethodConst;
import com.welearn.dictionary.notify.NoticeRefTypeConst;
import com.welearn.dictionary.notify.NoticeTypeConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.*;
import com.welearn.entity.po.notify.Notice;
import com.welearn.entity.qo.equipment.RepairDispatchInsideQueryCondition;
import com.welearn.entity.qo.equipment.RepairReplacementQueryCondition;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.entity.vo.response.equipment.RepairDispatchInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.apply.RepairHelpApplyFeignClient;
import com.welearn.feign.notify.NoticeFeignClient;
import com.welearn.mapper.EngineerMapper;
import com.welearn.mapper.RepairDispatchInsideMapper;
import com.welearn.mapper.RepairReplacementMapper;
import com.welearn.mapper.RepairRequestMapper;
import com.welearn.service.*;
import com.welearn.util.PaginateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static com.welearn.dictionary.equipment.RepairRequestStatusConst.*;

/**
 * Description : RepairDispatchInsideService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairDispatchInsideServiceImpl extends BaseServiceImpl<RepairDispatchInside,RepairDispatchInsideQueryCondition,RepairDispatchInsideMapper>
        implements RepairDispatchInsideService{
    
    @Autowired
    private RepairDispatchInsideMapper repairDispatchInsideMapper;

    @Autowired
    private RepairReplacementMapper repairReplacementMapper;

    @Autowired
    private RepairReplacementService repairReplacementService;

    @Autowired
    private RepairHelpApplyFeignClient repairHelpApplyFeignClient;

    @Autowired
    private RepairHelpApplyService repairHelpApplyService;

    @Autowired
    private EngineerMapper engineerMapper;

    @Autowired
    private RepairRequestMapper repairRequestMapper;

    @Autowired
    private NoticeFeignClient noticeFeignClient;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentPermissionService equipmentPermissionService;

    @Override
    RepairDispatchInsideMapper getMapper() {
        return repairDispatchInsideMapper;
    }

    // 内部派工工作时间分类
    public enum DispatchInsideWorkTimeConst {
        CREATE_DISPATCH_AT,
        RE_DISPATCH_APPLY_AT,
        RE_DISPATCH_CHECKED_AT,
        DISPATCH_CANCEL_AT,
        DISPATCH_RECEIVE_AT,
        START_REPAIR_AT,
        END_REPAIR_AT,
        ASK_OUTSIDE_HELP_AT,
        ASK_WARRANTY_HELP_AT,
        CHECK_SUCCESS,
        CHECK_FAILED,
        CHECK_APPLY_AT,
        WAIT_PART_AT,
    }

    private void updateWorkTime(RepairDispatchInside dispatchInside, DispatchInsideWorkTimeConst type){
        Map<String, Object> workTime = JSON.parseObject(dispatchInside.getWorkTimeJson());
        workTime.put(type.name(), new Date());
        dispatchInside.setWorkTimeJson(JSON.toJSONString(workTime));
    }

    /**
     * 查询报修派工详情信息
     *
     * @param queryCondition 查询条件
     * @return 报修派工详情列表
     */
    @Override
    public List<RepairDispatchInfo> searchInfo(RepairDispatchInsideQueryCondition queryCondition) {
        return PaginateUtil.page(()->repairDispatchInsideMapper.searchInfoByCondition(queryCondition));
    }


    /**
     * 报修派工
     * 允许同时给多个工程师派工, 当其中一个工程师领工后则取消其它工程师的派工
     *
     * @param requestId      报修id
     * @param engineerIdList 派工工程师id列表
     */
    @Override
    public void createDispatch(String requestId, List<String> engineerIdList) {
        RepairRequest repairRequest = repairRequestMapper.selectByPK(requestId);
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() > UN_RE_DISPATCH.ordinal())
            throw new BusinessVerifyFailedException("requestId 非法 或 当前状态不允许派工");
        for (String engineerId : engineerIdList) {
            Engineer engineer = engineerMapper.selectByPK(engineerId);
            if (Objects.isNull(engineer) || !engineer.getIsEnable())
                throw new BusinessVerifyFailedException("engineerIdList 存在 非法 id");
            // 对工程师进行派工
            RepairDispatchInside dispatchInside = new RepairDispatchInside();
            dispatchInside.setEngineerId(engineer.getId());
            dispatchInside.setEngineerName(engineer.getUserName());
            dispatchInside.setRequestId(requestId);
            // 记录开始派工时间
            Map<String, Object> workTime = new HashMap<>();
            workTime.put(DispatchInsideWorkTimeConst.CREATE_DISPATCH_AT.name(), new Date());
            dispatchInside.setWorkTimeJson(JSON.toJSONString(workTime));
            this.create(dispatchInside);
            // 检查设备维护权限
            Boolean verifyPermission = equipmentPermissionService.verify(EquipmentPermissionTypeConst.EMPLOYEE.ordinal(),
                    EquipmentPermissionCodeConst.MAINTAIN.ordinal(), repairRequest.getEquipmentId(), engineer.getUserId());
            if (!verifyPermission){
                EquipmentPermission permission = new EquipmentPermission();
                permission.setType(EquipmentPermissionTypeConst.EMPLOYEE.ordinal());
                permission.setPermission(EquipmentPermissionCodeConst.MAINTAIN.ordinal());
                permission.setEquipmentId(repairRequest.getEquipmentId());
                permission.setObtainAt(new Date());
                permission.setObtainReason("被派工维修此设备");
                permission.setCompanyId(engineer.getUserCompanyId());
                permission.setCompanyName(engineer.getUserCompanyName());
                permission.setDepartmentId(engineer.getUserDepartmentId());
                permission.setDepartmentName(engineer.getUserDepartmentName());
                permission.setEmployeeId(engineer.getUserId());
                permission.setEmployeeName(engineer.getUserName());
                equipmentPermissionService.create(permission);
            }
            // 发送消息通知
            if (StringUtils.isNotBlank(engineer.getUserId())){
                Notice notice = new Notice();
                notice.setType(NoticeTypeConst.USER_NOTICE.ordinal());
                notice.setMethod(NoticeMethodConst.MESSAGE.ordinal());
                notice.setTitle(String.format("%s - %s - 设备报修", "派工待领取", repairRequest.getEquipmentName()));
                notice.setReceiverId(engineer.getUserId());
                notice.setReceiverName(engineer.getUserName());
                notice.setRefId(requestId);
                notice.setRefType(NoticeRefTypeConst.ENGINEER_DISPATCH.name());
                notice.setRemark(dispatchInside.getId());
                noticeFeignClient.create(notice);
            }
        }
        // 更新报修状态
        repairRequest.setStatus(UN_RECEIVE.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
        // 修改设备状态
        Equipment equipment = new Equipment();
        equipment.setId(repairRequest.getEquipmentId());
        equipment.setRepairStatus(EquipmentRepairStatusConst.UN_RECEIVE.ordinal());
        equipmentService.update(equipment);
    }

    /**
     * 取消派工
     * 在派工未被领取前 或 工程师申请重派后 可调用, 并更新报修状态 至 0-待派工 或 1-待重派
     *
     * @param dispatchInsideId 派工id
     * @param reason    取消派工原因
     */
    @Override @Transactional
    public void cancelDispatch(String dispatchInsideId, String reason) {
        RepairDispatchInside dispatchInside = this.select(dispatchInsideId);
        if (Objects.isNull(dispatchInside) || !dispatchInside.getIsEnable())
            throw new BusinessVerifyFailedException("dispatchInsideId 非法 或 状态 非法");
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchInside.getRequestId());
        if (Objects.isNull(repairRequest))
            throw new BusinessVerifyFailedException("requestId 非法");
        // 查询该报修的所有派工信息
        RepairDispatchInsideQueryCondition condition = new RepairDispatchInsideQueryCondition();
        condition.setIsEnable(true);
        condition.setRequestId(dispatchInside.getRequestId());
        List<RepairDispatchInside> dispatchInsides = this.search(condition).stream()
                .filter(d -> !d.getId().equals(dispatchInsideId) && d.getStatus() == InsideDispatchStatusConst.UN_RECEIVE.ordinal())
                .collect(Collectors.toList());
        // 同意此工程师的重派申请
        if (dispatchInside.getIsReDispatch()){
            if (repairRequest.getStatus() < UN_RECEIVE.ordinal() || repairRequest.getStatus() > REPAIRING.ordinal())
                throw new BusinessVerifyFailedException("当前报修 状态 不允许 取消派工");
            updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.RE_DISPATCH_CHECKED_AT);
            dispatchInside.setReDispatchReason(String.format("工程师申请重派, 原因如下:\n%s", dispatchInside.getReDispatchDescription()));
            dispatchInside.setStatus(InsideDispatchStatusConst.RE_DISPATCHED.ordinal());
            // 更改报修状态为 待重派 如 无 其它派工
            if (dispatchInsides.isEmpty()) {
                repairRequest.setStatus(UN_RE_DISPATCH.ordinal());
                repairRequestMapper.updateByPK(repairRequest);
            }
            // 如有 则维持当前状态不变
        } else {
            if (repairRequest.getStatus() != UN_RECEIVE.ordinal())
                throw new BusinessVerifyFailedException("当前报修 状态 不允许 主动取消派工");
            updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.DISPATCH_CANCEL_AT);
            dispatchInside.setReDispatchReason(String.format("维修主管取消了此派工, 原因如下:\n%s", reason));
            dispatchInside.setStatus(InsideDispatchStatusConst.CANCEL.ordinal());
            // 主动取消此派工后如 无 其它派工
            if (dispatchInsides.isEmpty()){
                // 更改报修状态为 待派工
                repairRequest.setStatus(UN_DISPATCH.ordinal());
                repairRequestMapper.updateByPK(repairRequest);
            }
            // 如有 则维持当前状态不变
        }
        this.update(dispatchInside);
        // 查询工程师
        Engineer engineer = engineerMapper.selectByPK(dispatchInside.getEngineerId());
        if (Objects.nonNull(engineer) && StringUtils.isNotBlank(engineer.getUserId())){
            Notice notice = new Notice();
            notice.setType(NoticeTypeConst.USER_NOTICE.ordinal());
            notice.setMethod(NoticeMethodConst.MESSAGE.ordinal());
            notice.setTitle(String.format("%s - %s - 设备报修", dispatchInside.getIsReDispatch() ? "派工已重派" : "派工已取消", repairRequest.getEquipmentName()));
            notice.setReceiverId(engineer.getUserId());
            notice.setReceiverName(engineer.getUserName());
            notice.setRefId(repairRequest.getId());
            notice.setRefType(NoticeRefTypeConst.ENGINEER_DISPATCH.name());
            notice.setRemark(dispatchInside.getId());
            noticeFeignClient.create(notice);
        }
        // 修改设备状态
        Equipment equipment = new Equipment();
        equipment.setId(repairRequest.getEquipmentId());
        equipment.setRepairStatus(EquipmentRepairStatusConst.UN_DISPATCH.ordinal());
        equipmentService.update(equipment);
        // 尝试取消工程师的配件更换
        this.cancelDispatchInsideReplacement(dispatchInside);
    }

    /**
     * 申请重派
     * 收到派工信息时 工程师可主动申请重派
     * 在领工维修失败后也可申请重派
     * @param dispatchInsideId 派工id
     * @param reason           申请重派原因
     */
    @Override
    public void applyReDispatch(String dispatchInsideId, String reason) {
        RepairDispatchInside dispatchInside = this.select(dispatchInsideId);
        if (Objects.isNull(dispatchInside) || dispatchInside.getStatus() > InsideDispatchStatusConst.RECEIVED.ordinal())
            throw new BusinessVerifyFailedException("dispatchInsideId 非法 或 状态 非法");
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchInside.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() < UN_RECEIVE.ordinal() || repairRequest.getStatus() > REPAIRING.ordinal())
            throw new BusinessVerifyFailedException("repairRequestId 非法 或 当前报修 状态 不允许 取消派工");
        dispatchInside.setIsReDispatch(true);
        dispatchInside.setReDispatchDescription(reason);
        updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.RE_DISPATCH_APPLY_AT);
        this.update(dispatchInside);
        // TODO: 通知维修主管 有工程师申请重派
    }

    public void cancelDispatchInsideReplacement(RepairDispatchInside dispatchInside){
        RepairReplacementQueryCondition condition = new RepairReplacementQueryCondition();
        condition.setDispatchId(dispatchInside.getId());
        condition.setIsEnable(true);
        List<RepairReplacement> repairReplacements = repairReplacementMapper.selectByCondition(condition);
        for (RepairReplacement repairReplacement : repairReplacements) {
            repairReplacementService.cancel(repairReplacement);
        }
    }

    /**
     * 领取派工
     * 派工如同时发给多个工程师 则会取消其余的派工信息, 并更新报修状态 至 3-待维修
     * 如工程师之前曾发起申请重派, 又领工后会取消先前重派申请
     * @param dispatchInsideId 派工id
     */
    @Override
    public void receiveDispatch(String dispatchInsideId) {
        RepairDispatchInside dispatchInside = this.select(dispatchInsideId);
        if (Objects.isNull(dispatchInside) || !dispatchInside.getIsEnable() || dispatchInside.getStatus() != InsideDispatchStatusConst.UN_RECEIVE.ordinal())
            throw new BusinessVerifyFailedException("dispatchInsideId 非法 或 状态 非法");
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchInside.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != UN_RECEIVE.ordinal())
            throw new BusinessVerifyFailedException("requestId 非法 或 状态 非法");
        // 查询该报修的所有派工信息
        RepairDispatchInsideQueryCondition condition = new RepairDispatchInsideQueryCondition();
        condition.setIsEnable(true);
        condition.setRequestId(dispatchInside.getRequestId());
        List<RepairDispatchInside> dispatchInsides = this.search(condition).stream()
                .filter(d -> !d.getId().equals(dispatchInsideId) && d.getStatus() == InsideDispatchStatusConst.UN_RECEIVE.ordinal())
                .collect(Collectors.toList());
        for (RepairDispatchInside otherDispatch : dispatchInsides) {
            otherDispatch.setStatus(InsideDispatchStatusConst.CANCEL.ordinal());
            otherDispatch.setReDispatchReason("此派工已被系统取消, 有其它工程师抢先领取");
            updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.DISPATCH_CANCEL_AT);
            this.update(otherDispatch);
        }
        // 更新此派工信息
        dispatchInside.setStatus(InsideDispatchStatusConst.RECEIVED.ordinal());
        dispatchInside.setIsReDispatch(false);
        dispatchInside.setReDispatchDescription("");
        dispatchInside.setReceivedAt(new Date());
        updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.DISPATCH_RECEIVE_AT);
        this.update(dispatchInside);
        // 更新报修状态信息
        repairRequest.setStatus(UN_REPAIR.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
        // 提示被取消派工的工程师
        for (RepairDispatchInside otherDispatch : dispatchInsides) {
            // 查询工程师
            Engineer engineer = engineerMapper.selectByPK(otherDispatch.getEngineerId());
            if (Objects.nonNull(engineer) && StringUtils.isNotBlank(engineer.getUserId())){
                Notice notice = new Notice();
                notice.setType(NoticeTypeConst.USER_NOTICE.ordinal());
                notice.setMethod(NoticeMethodConst.MESSAGE.ordinal());
                notice.setTitle(String.format("%s - %s - 设备报修", "派工已取消", repairRequest.getEquipmentName()));
                notice.setReceiverId(engineer.getUserId());
                notice.setReceiverName(engineer.getUserName());
                notice.setRefId(repairRequest.getId());
                notice.setRefType(NoticeRefTypeConst.ENGINEER_DISPATCH.name());
                notice.setRemark(otherDispatch.getId());
                noticeFeignClient.create(notice);
            }
        }
        // 修改设备状态
        Equipment equipment = new Equipment();
        equipment.setId(repairRequest.getEquipmentId());
        equipment.setRepairStatus(EquipmentRepairStatusConst.UN_REPAIR.ordinal());
        equipmentService.update(equipment);
        // TODO: 提示维修主管 工程师已领工
    }

    /**
     * 开始维修
     *
     * @param dispatchInsideId 报修申请id
     * @param isTrue 报修是否属实
     * @param reason 取消原因
     */
    @Override
    public void startRepair(String dispatchInsideId, Boolean isTrue, String reason) {
        RepairDispatchInside dispatchInside = this.select(dispatchInsideId);
        // 要求 已领工状态 下的派工
        if (Objects.isNull(dispatchInside) || dispatchInside.getStatus() != InsideDispatchStatusConst.RECEIVED.ordinal())
            throw new BusinessVerifyFailedException("dispatchInside 非法 或 状态异常");
        // 要求 待维修状态 下的报修
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchInside.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != UN_REPAIR.ordinal())
            throw new BusinessVerifyFailedException("repairRequest 非法 或 状态异常");
        updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.START_REPAIR_AT);
        if (isTrue) {
            // 更改报修状态为维修中
            repairRequest.setStatus(REPAIRING.ordinal());
        } else {
            // 更改报修状态为待取消
            repairRequest.setStatus(UN_CANCEL.ordinal());
            repairRequest.setIsReal(false);
            repairRequest.setRemark(String.format("工程师 - %s 检查设备后未发现报修问题, 检查结果如下: \n%s\n", dispatchInside.getEngineerName(), reason));
            // 更改派工状态为已完工
            dispatchInside.setStatus(InsideDispatchStatusConst.FINISH.ordinal());
        }
        repairRequestMapper.updateByPK(repairRequest);
        this.update(dispatchInside);
    }

    /**
     * 结束维修
     * @param dispatchInsideId 内部派工id
     * @param result 维修结果:0-维修成功 1-等待配件 2-维修失败
     * @param reason 选填 维修失败时 进行申请重派需该字段
     */
    @Override
    public void endRepair(String dispatchInsideId, Integer result, String reason) {
        RepairDispatchInside dispatchInside = this.select(dispatchInsideId);
        // 要求 已领工状态 下的派工
        if (Objects.isNull(dispatchInside) || dispatchInside.getStatus() != InsideDispatchStatusConst.RECEIVED.ordinal())
            throw new BusinessVerifyFailedException("dispatchInside 非法 或 状态异常");
        // 要求 待维修状态 或 维修中 下的报修
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchInside.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != UN_REPAIR.ordinal() && repairRequest.getStatus() != REPAIRING.ordinal())
            throw new BusinessVerifyFailedException("repairRequest 非法 或 状态异常");
        updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.END_REPAIR_AT);
        // 查询配件更换
        RepairReplacementQueryCondition condition = new RepairReplacementQueryCondition();
        condition.setIsEnable(true);
        condition.setDispatchId(dispatchInsideId);
        List<RepairReplacement> repairReplacements = repairReplacementMapper.selectByCondition(condition);
        List<RepairReplacement> beforeUnReplace = repairReplacements.stream().filter(repairReplacement -> repairReplacement.getStatus() < RepairPartReplaceStatusConst.UN_REPLACE.ordinal())
                .collect(Collectors.toList());
        // 更新报修状态
        // 维修成功
        if (result == InsideDispatchResultConst.SUCCESS.ordinal()){
            // 检查是否有配件更换申请 状态 < 3, 存在则不允许完成
            if (!repairReplacements.isEmpty()){
                if (!beforeUnReplace.isEmpty()){
                    throw new BusinessVerifyFailedException("该内部派工存在未入库的采购配件, 须先取消相关的配件更换申请");
                }
            }
            updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.CHECK_APPLY_AT);
            repairRequest.setStatus(UN_CHECK.ordinal());
            // 修改设备状态
            Equipment equipment = new Equipment();
            equipment.setId(repairRequest.getEquipmentId());
            equipment.setRepairStatus(EquipmentRepairStatusConst.UN_CHECK.ordinal());
            equipmentService.update(equipment);
            // TODO: 通知报修部门人员进行验收, 短信通知报修人, 站内信通知报修人部门
        }
        // 等待配件
        else if (result == InsideDispatchResultConst.WAITING_PART.ordinal()){
            if (repairReplacements.isEmpty() || beforeUnReplace.isEmpty())
                throw new BusinessVerifyFailedException("无配件更换申请, 或配件待更换, 审批失败, 已取消");
            repairRequest.setStatus(UN_REPAIR.ordinal());
            repairRequest.setIsPartWaiting(true);
            updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.WAIT_PART_AT);
        }
        // 维修失败 申请重派
        else if (result == InsideDispatchResultConst.RE_DISPATCH.ordinal()){
            if (!repairReplacements.isEmpty() && !beforeUnReplace.isEmpty()){
                // TODO: 取消该部分配件更换申请
            }
            // 工程师申请重派
            dispatchInside.setIsReDispatch(true);
            dispatchInside.setReDispatchDescription(reason);
            updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.RE_DISPATCH_APPLY_AT);
            // 该工程师维修失败, 后续应申请重派
            repairRequest.setStatus(UN_REPAIR.ordinal());
            // TODO: 通知维修主管有工程师申请重派
        }
        repairRequestMapper.updateByPK(repairRequest);
        this.update(dispatchInside);
    }

    /**
     * 继续维修
     * @param dispatchInsideId 内部派工id
     */
    public void continueRepair(String dispatchInsideId){
        RepairDispatchInside dispatchInside = this.select(dispatchInsideId);
        // 要求 已领工状态 下的派工
        if (Objects.isNull(dispatchInside) || dispatchInside.getStatus() != InsideDispatchStatusConst.RECEIVED.ordinal())
            throw new BusinessVerifyFailedException("dispatchInside 非法 或 状态异常");
        // 要求 待维修状态
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchInside.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != UN_REPAIR.ordinal())
            throw new BusinessVerifyFailedException("repairRequest 非法 或 状态异常");
        repairRequest.setStatus(REPAIRING.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
    }

    /**
     * 请求援助
     * 根据是否保修期内决定是厂商报修 还是 外部援助
     *
     * @param dispatchInsideId 派工id
     */
    @Override
    public ApprovalApplication askHelp(String dispatchInsideId, String description, User user) {
        RepairDispatchInside dispatchInside = this.select(dispatchInsideId);
        // 要求 已领工状态 下的派工
        if (Objects.isNull(dispatchInside) || dispatchInside.getStatus() != InsideDispatchStatusConst.RECEIVED.ordinal())
            throw new BusinessVerifyFailedException("dispatchInside 非法 或 状态异常");
        // 要求 维修中状态 下的报修
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchInside.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != REPAIRING.ordinal())
            throw new BusinessVerifyFailedException("repairRequest 非法 或 状态异常");
        ApprovalApplication application = null;
        if (repairRequest.getGuaranteeRepairExpiredAt().getTime() < new Date().getTime()){
            updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.ASK_OUTSIDE_HELP_AT);
            repairRequest.setIsHelpApply(true);
            // 创建第三方维修申请
            RepairHelpApply repairHelpApply = new RepairHelpApply();
            repairHelpApply.setDescription(description);
            repairHelpApply.setDispatchId(dispatchInsideId);
            repairHelpApply.setRequestId(repairRequest.getId());
            repairHelpApply.setEngineerId(dispatchInside.getEngineerId());
            repairHelpApply.setEngineerName(dispatchInside.getEngineerName());
            repairHelpApplyService.create(repairHelpApply);
            // 请求审批
            Apply<RepairHelpApply> request = new Apply<>();
            request.setContent(repairHelpApply);
            request.setApplicantId(user.getId());
            request.setType(ApplicationTypeConst.FORM_APPLICATION.ordinal());
            application = repairHelpApplyFeignClient.apply(request).getData();
        } else {
            updateWorkTime(dispatchInside, DispatchInsideWorkTimeConst.ASK_WARRANTY_HELP_AT);
            repairRequest.setIsWarranty(true);
        }
        repairRequest.setStatus(UN_REPAIR.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
        return application;
    }

    /**
     * 维修验收成功
     * @param requestId 报修id
     */
    @Override
    public void check(String requestId, Boolean isSuccess){
        RepairDispatchInsideQueryCondition condition = new RepairDispatchInsideQueryCondition();
        condition.setIsEnable(true);
        condition.setRequestId(requestId);
        condition.setStatus(InsideDispatchStatusConst.RECEIVED.ordinal());
        List<RepairDispatchInside> repairDispatchInsides = this.search(condition);
        for (RepairDispatchInside repairDispatchInside : repairDispatchInsides) {
            if (isSuccess){
                repairDispatchInside.setStatus(InsideDispatchStatusConst.FINISH.ordinal());
                updateWorkTime(repairDispatchInside, DispatchInsideWorkTimeConst.CHECK_SUCCESS);
            } else {
                updateWorkTime(repairDispatchInside, DispatchInsideWorkTimeConst.CHECK_FAILED);
            }
            this.update(repairDispatchInside);
        }
    }
}
