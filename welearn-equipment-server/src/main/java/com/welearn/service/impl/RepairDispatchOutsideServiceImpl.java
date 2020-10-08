package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.equipment.OutsideDispatchMailStatusConst;
import com.welearn.dictionary.equipment.OutsideDispatchRepairMethodConst;
import com.welearn.dictionary.equipment.OutsideDispatchResultConst;
import com.welearn.dictionary.equipment.RepairPartReplaceStatusConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.apply.Contract;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.*;
import com.welearn.entity.qo.apply.ContractQueryCondition;
import com.welearn.entity.qo.equipment.RepairDispatchOutsideQueryCondition;
import com.welearn.entity.qo.equipment.RepairReplacementQueryCondition;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.apply.ContractFeignClient;
import com.welearn.feign.apply.RepairHelpQuotationApprovalFeignClient;
import com.welearn.mapper.*;
import com.welearn.service.RepairDispatchOutsideDetailService;
import com.welearn.service.RepairDispatchOutsideService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static com.welearn.dictionary.equipment.RepairRequestStatusConst.*;

/**
 * Description : RepairDispatchOutsideService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairDispatchOutsideServiceImpl extends BaseServiceImpl<RepairDispatchOutside,RepairDispatchOutsideQueryCondition,RepairDispatchOutsideMapper>
        implements RepairDispatchOutsideService{
    
    @Autowired
    private RepairDispatchOutsideMapper repairDispatchOutsideMapper;

    @Autowired
    private RepairRequestMapper repairRequestMapper;

    @Autowired
    private EngineerMapper engineerMapper;

    @Autowired
    private ContractFeignClient contractFeignClient;

    @Autowired
    private RepairReplacementMapper repairReplacementMapper;

    @Autowired
    private RepairDispatchOutsideDetailService repairDispatchOutsideDetailService;

    @Autowired
    RepairHelpQuotationApprovalFeignClient repairHelpQuotationApprovalFeignClient;

    @Override
    RepairDispatchOutsideMapper getMapper() {
        return repairDispatchOutsideMapper;
    }

    // 外部派工工作时间分类
    public enum DispatchOutsideWorkTimeConst {
        CREATE_DISPATCH_AT,
        EQUIPMENT_MAIL_SEND_AT,
        QUOTATION_APPROVAL_APPLY_AT,
        EQUIPMENT_MAIL_RECEIVE_AT,
        SIGN_CONTRACT_AT,
        START_REPAIR_AT,
        END_REPAIR_AT,
        END_REPAIR_SUCCESS_AT,
        END_REPAIR_WAITING_PART_AT,
        END_REPAIR_FAILED_AT,
        END_REPAIR_CANCEL_AT,
        BREAK_CONTRACT_AT,
        CHECK_SUCCESS_AT,
        CHECK_FAILED_AT,
    }


    private void updateWorkTime(RepairDispatchOutside dispatchInside, DispatchOutsideWorkTimeConst type){
        Map<String, Object> workTime = null;
        if (StringUtils.isBlank(dispatchInside.getWorkTimeJson()))
            workTime = new HashMap<>();
        else
            workTime = JSON.parseObject(dispatchInside.getWorkTimeJson());
        workTime.put(type.name(), new Date());
        dispatchInside.setWorkTimeJson(JSON.toJSONString(workTime));
    }

    /**
     * 报修外部派工
     * 允许同时给多个工程师派工, 对于外部工程师只做登记用
     *
     * @param dispatchOutside 派工基本信息
     * @param engineerIdList 派工工程师id列表
     */
    @Override @Transactional
    public void createDispatch(RepairDispatchOutside dispatchOutside, List<String> engineerIdList) {
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchOutside.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != UN_REPAIR.ordinal() ||
                !(repairRequest.getIsWarranty() || repairRequest.getIsHelpApply()))
            throw new BusinessVerifyFailedException("requestId 非法 或 当前状态不允许外部派工");
        // 记录开始派工时间
        updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.CREATE_DISPATCH_AT);
        if (StringUtils.isNotBlank(dispatchOutside.getMailSendInfo()))
            updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.EQUIPMENT_MAIL_SEND_AT);
        // 检查是否之前已经有该公司的外部派工信息 如果有则继承原先合同
        RepairDispatchOutsideQueryCondition condition = new RepairDispatchOutsideQueryCondition();
        condition.setIsFinish(true);
        condition.setRequestId(repairRequest.getId());
        condition.setCompanyName(dispatchOutside.getCompanyName());
        condition.setIsEnable(true);
        List<RepairDispatchOutside> finishedRecords = this.search(condition);
        if (Objects.nonNull(finishedRecords) && !finishedRecords.isEmpty()){
            RepairDispatchOutside finishedRecord = finishedRecords.get(0);
            dispatchOutside.setContractId(finishedRecord.getContractId());
        }
        this.create(dispatchOutside);
        // 关联派工工程师
        if (Objects.nonNull(engineerIdList) && !engineerIdList.isEmpty()){
            for (String engineerId : engineerIdList) {
                Engineer engineer = engineerMapper.selectByPK(engineerId);
                if (Objects.isNull(engineer) || !engineer.getIsEnable())
                    throw new BusinessVerifyFailedException("engineerIdList 存在 非法 id");
                // 对工程师进行派工
                RepairDispatchOutsideDetail repairDispatchOutsideDetail = new RepairDispatchOutsideDetail();
                repairDispatchOutsideDetail.setEngineerId(engineer.getId());
                repairDispatchOutsideDetail.setEngineerName(engineer.getUserName());
                repairDispatchOutsideDetail.setRequestId(dispatchOutside.getRequestId());
                repairDispatchOutsideDetail.setDispatchOutsideId(dispatchOutside.getId());
                repairDispatchOutsideDetailService.create(repairDispatchOutsideDetail);
            }
        }
        // 更新报修状态
        if (repairRequest.getIsWarranty())
            repairRequest.setStatus(WARRANTY_UN_REPAIR.ordinal());
        if (repairRequest.getIsHelpApply())
            repairRequest.setStatus(HELP_APPLY_UN_REPAIR.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
    }

    /**
     * 报价审批
     *
     * @param repairHelpQuotationApproval 外援维修报价审批
     * @param user                        用户
     */
    @Override
    public ApprovalApplication quotationApproval(RepairHelpQuotationApproval repairHelpQuotationApproval, User user) {
        RepairDispatchOutside dispatchOutside = this.select(repairHelpQuotationApproval.getOutsideDispatchId());
        if (Objects.isNull(dispatchOutside) || StringUtils.isNotBlank(dispatchOutside.getContractId()))
            throw new BusinessVerifyFailedException("dispatchOutsideId 非法 或 状态 异常");
        updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.QUOTATION_APPROVAL_APPLY_AT);
        if (dispatchOutside.getMethod().equals(OutsideDispatchRepairMethodConst.MAIL_SEND.ordinal())){
            if (dispatchOutside.getMailStatus().equals(OutsideDispatchMailStatusConst.SEND.ordinal())){
                dispatchOutside.setMailStatus(OutsideDispatchMailStatusConst.CHECK.ordinal());
            }
        }
        this.update(dispatchOutside);
        // 请求审批
        Apply<RepairHelpQuotationApproval> request = new Apply<>();
        request.setContent(repairHelpQuotationApproval);
        request.setApplicantId(user.getId());
        request.setType(ApplicationTypeConst.FORM_APPLICATION.ordinal());
        return repairHelpQuotationApprovalFeignClient.apply(request).getData();
    }

    /**
     * 设备快递已寄件
     * @param dispatchOutsideId 外部派工id
     * @param mailSendInfoJson 寄件信息
     */
    @Override
    public void mailSend(String dispatchOutsideId, String mailSendInfoJson){
        RepairDispatchOutside dispatchOutside = this.select(dispatchOutsideId);
        if (Objects.isNull(dispatchOutside) || dispatchOutside.getMailStatus() != OutsideDispatchMailStatusConst.UN_SEND.ordinal()
                || StringUtils.isNotBlank(dispatchOutside.getMailSendInfo()))
            throw new BusinessVerifyFailedException("dispatchOutsideId 非法 或 状态 异常");
        dispatchOutside.setMailSendInfo(mailSendInfoJson);
        // 存在合同ID 保修期内的 直接变为已确认状态
        if (StringUtils.isNotBlank(dispatchOutside.getContractId()))
            dispatchOutside.setMailStatus(OutsideDispatchMailStatusConst.CHECK.ordinal());
        else
            dispatchOutside.setMailStatus(OutsideDispatchMailStatusConst.SEND.ordinal());
        updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.EQUIPMENT_MAIL_SEND_AT);
        this.update(dispatchOutside);
    }

    /**
     * 设备快递已收件
     * @param dispatchOutsideId 外部派工id
     * @param mailReceiveInfoJson 收件信息
     */
    @Override
    public void mailReceive(String dispatchOutsideId, String mailReceiveInfoJson){
        RepairDispatchOutside dispatchOutside = this.select(dispatchOutsideId);
        if (Objects.isNull(dispatchOutside))
            throw new BusinessVerifyFailedException("dispatchOutsideId 非法 或 状态 异常");
        dispatchOutside.setMailReceiveInfo(mailReceiveInfoJson);
        dispatchOutside.setMailStatus(OutsideDispatchMailStatusConst.RECEIVE.ordinal());
        updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.EQUIPMENT_MAIL_RECEIVE_AT);
        this.update(dispatchOutside);
    }

    /**
     * 签订合同
     *
     * @param dispatchOutsideId 外部派工id
     * @param contract          合同
     */
    @Override
    public void signContract(String dispatchOutsideId, Contract contract) {
        RepairDispatchOutside dispatchOutside = this.select(dispatchOutsideId);
        if (Objects.isNull(dispatchOutside) || StringUtils.isNotBlank(dispatchOutside.getContractId()))
            throw new BusinessVerifyFailedException("dispatchOutsideId 非法 或 状态 异常");
        if (dispatchOutside.getMethod().equals(OutsideDispatchRepairMethodConst.MAIL_SEND.ordinal())){
            if (!dispatchOutside.getMailStatus().equals(OutsideDispatchMailStatusConst.CHECK.ordinal()))
                throw new BusinessVerifyFailedException("设备未邮寄");
            // 自动开始维修
            this.startRepair(dispatchOutsideId);
        }
        contract = contractFeignClient.create(contract).getData();
        dispatchOutside.setContractId(contract.getId());
        updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.SIGN_CONTRACT_AT);
        this.update(dispatchOutside);
    }

    /**
     * 外部派工开始维修
     * @param dispatchOutsideId 外部派工id
     */
    @Override @Transactional
    public void startRepair(String dispatchOutsideId) {
        RepairDispatchOutside dispatchOutside = this.select(dispatchOutsideId);
        if (Objects.isNull(dispatchOutside) || dispatchOutside.getIsFinish())
            throw new BusinessVerifyFailedException("dispatchOutsideId 非法 或 当前状态 不允许 开始维修");
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchOutside.getRequestId());
        // 待保修厂商或第三方维修 或 维修中时均可
        if (Objects.isNull(repairRequest) || !(
                repairRequest.getStatus() == WARRANTY_UN_REPAIR.ordinal() && repairRequest.getIsWarranty() ||
                repairRequest.getStatus() == HELP_APPLY_UN_REPAIR.ordinal() && repairRequest.getIsHelpApply() ||
                repairRequest.getStatus() == REPAIRING.ordinal()))
            throw new BusinessVerifyFailedException("requestId 非法 或 当前状态 不允许 开始维修");
        // 记录开始维修时间
        updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.START_REPAIR_AT);
        // 更新外部派工信息
        dispatchOutside.setIsArrive(true);
        this.update(dispatchOutside);
        // 更新报修状态
        repairRequest.setStatus(REPAIRING.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
    }

    /**
     * 外派工程师结束维修
     *
     * @param dispatchOutsideId 外部派工id
     * @param result            维修结果:0-维修成功 1-等待配件 2-维修失败
     */
    @Override @Transactional
    public void endRepair(String dispatchOutsideId, Integer result, String mailReceiveInfo) {
        RepairDispatchOutside dispatchOutside = this.select(dispatchOutsideId);
        if (Objects.isNull(dispatchOutside) || dispatchOutside.getIsFinish())
            throw new BusinessVerifyFailedException("dispatchOutsideId 非法 或 当前状态 不允许 结束维修");
        RepairRequest repairRequest = repairRequestMapper.selectByPK(dispatchOutside.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != REPAIRING.ordinal())
            throw new BusinessVerifyFailedException("requestId 非法 或 当前状态 不允许 结束维修");
        // 处理邮寄维修时 对快递状态的更新
        if (dispatchOutside.getMethod().equals(OutsideDispatchRepairMethodConst.MAIL_SEND.ordinal())){
            if (StringUtils.isBlank(mailReceiveInfo))
                throw new BusinessVerifyFailedException("邮寄维修时 结束维修需要 mailReceiveInfo");
            if (result == OutsideDispatchResultConst.WAITING_PART.ordinal())
                throw new BusinessVerifyFailedException("邮寄维修时 不应以 等待配件 方式 结束维修");
            dispatchOutside.setMailReceiveInfo(mailReceiveInfo);
            dispatchOutside.setMailStatus(OutsideDispatchMailStatusConst.UN_RECEIVE.ordinal());
        }
        // 记录结束维修时间
        updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.END_REPAIR_AT);
        // 更新外部派工信息
        dispatchOutside.setResult(result);
        // 查询配件更换
        RepairReplacementQueryCondition condition = new RepairReplacementQueryCondition();
        condition.setIsEnable(true);
        condition.setOutsideDispatchId(dispatchOutsideId);
        List<RepairReplacement> repairReplacements = repairReplacementMapper.selectByCondition(condition);
        List<RepairReplacement> beforeUnReplace = repairReplacements.stream().filter(repairReplacement -> repairReplacement.getStatus() < RepairPartReplaceStatusConst.UN_REPLACE.ordinal())
                .collect(Collectors.toList());
        List<RepairReplacement> afterUnReplace = repairReplacements.stream().filter(repairReplacement -> repairReplacement.getStatus() >= RepairPartReplaceStatusConst.UN_REPLACE.ordinal())
                .collect(Collectors.toList());
        // 更新报修状态
        // 维修成功 isFinish 为false
        if (result == OutsideDispatchResultConst.SUCCESS.ordinal()){
            dispatchOutside.setIsFinish(false);
            // 检查是否有配件更换申请 状态 < 3, 存在则不允许完成
            if (!repairReplacements.isEmpty()){
                if (!beforeUnReplace.isEmpty()){
                    throw new BusinessVerifyFailedException("该外部派工存在未入库的采购配件, 须先取消相关的配件更换申请");
                }
            }
            repairRequest.setStatus(UN_CHECK.ordinal());
            updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.END_REPAIR_SUCCESS_AT);
            // TODO: 通知报修部门人员进行验收
        }
        // 等待配件
        else if (result == OutsideDispatchResultConst.WAITING_PART.ordinal()){
            dispatchOutside.setIsFinish(true);
            if (repairReplacements.isEmpty() || beforeUnReplace.isEmpty())
                throw new BusinessVerifyFailedException("无配件更换申请, 或配件待更换, 审批失败, 已取消");
            repairRequest.setStatus(UN_REPAIR.ordinal());
            repairRequest.setIsPartWaiting(true);
            updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.END_REPAIR_WAITING_PART_AT);
        }
        // 维修失败
        else if (result == OutsideDispatchResultConst.FAIL.ordinal()){
            dispatchOutside.setIsFinish(true);
            if (!repairReplacements.isEmpty() && !beforeUnReplace.isEmpty()){
                // TODO: 取消该部分配件更换申请

            }
            // 同行所有工程师均维修失败 需重新派工 如第三方维修则需询问合同是否违约
            repairRequest.setStatus(UN_REPAIR.ordinal());
            updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.END_REPAIR_FAILED_AT);
        }
        // 维修取消
        else if (result == OutsideDispatchResultConst.CANCEL.ordinal()){
            dispatchOutside.setIsFinish(true);
            updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.END_REPAIR_CANCEL_AT);
        }
        repairRequestMapper.updateByPK(repairRequest);
        this.update(dispatchOutside);
    }

    /**
     * 外派工程师违约
     *
     * @param dispatchOutsideId 外部派工id
     * @param remark            备注
     */
    @Override @Transactional
    public void breakContract(String dispatchOutsideId, String remark) {
        RepairDispatchOutside dispatchOutside = this.select(dispatchOutsideId);
        if (Objects.isNull(dispatchOutside))
            throw new BusinessVerifyFailedException("dispatchOutsideId 非法");
        // 查询违约合同
        ContractQueryCondition condition = new ContractQueryCondition();
        condition.setTargetId(dispatchOutside.getRequestId());
        condition.setSecondPart(dispatchOutside.getCompanyName());
        condition.setIsEnable(true);
        List<Contract> contracts = contractFeignClient.search(condition).getData();
        if (Objects.isNull(contracts) || contracts.isEmpty())
            throw new BusinessVerifyFailedException("该外部派工 未关联 相关合同");
        // 更新合同的备注信息
        Contract contract = contracts.get(0);
        remark = String.format("乙方违反合同约定: %s\n", remark);
        contract.setRemark(StringUtils.isBlank(contract.getRemark()) ? remark : contract.getRemark() + remark);
        contractFeignClient.update(contract);
        // 更新外部派工信息
        dispatchOutside.setIsBreach(true);
        dispatchOutside.setRemark(remark);
        updateWorkTime(dispatchOutside, DispatchOutsideWorkTimeConst.BREAK_CONTRACT_AT);
        this.update(dispatchOutside);

    }

    /**
     * 维修验收成功
     * @param requestId 报修id
     */
    public void check(String requestId, Boolean isSuccess){
        RepairDispatchOutsideQueryCondition condition = new RepairDispatchOutsideQueryCondition();
        condition.setRequestId(requestId);
        condition.setIsEnable(true);
        condition.setIsFinish(false);
        condition.setResult(OutsideDispatchResultConst.SUCCESS.ordinal());
        List<RepairDispatchOutside> repairDispatchOutsides = this.search(condition);
        for (RepairDispatchOutside repairDispatchOutside : repairDispatchOutsides) {
            if (isSuccess){
                repairDispatchOutside.setIsFinish(true);
                // 记录验收成功时间
                updateWorkTime(repairDispatchOutside, DispatchOutsideWorkTimeConst.CHECK_SUCCESS_AT);
            } else {
                repairDispatchOutside.setIsFinish(false);
                repairDispatchOutside.setResult(null);
                // 记录验收失败时间
                updateWorkTime(repairDispatchOutside, DispatchOutsideWorkTimeConst.CHECK_FAILED_AT);
            }
            repairDispatchOutsideMapper.updateByPK(repairDispatchOutside);
        }
    }
}
