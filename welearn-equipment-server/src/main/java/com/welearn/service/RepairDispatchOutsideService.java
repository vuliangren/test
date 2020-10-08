package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.apply.Contract;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairDispatchOutside;
import com.welearn.entity.po.equipment.RepairHelpQuotationApproval;
import com.welearn.entity.qo.equipment.RepairDispatchOutsideQueryCondition;
import com.welearn.entity.vo.response.equipment.RepairDispatchOutsideInfo;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : RepairDispatchOutsideService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairDispatchOutsideService extends BaseService<RepairDispatchOutside, RepairDispatchOutsideQueryCondition>{
    /**
     * 报修外部派工
     * 允许同时给多个工程师派工, 对于外部工程师只做登记用
     * @param dispatchOutside 派工基本信息
     * @param engineerIdList 派工工程师id列表
     */
    void createDispatch(@Valid RepairDispatchOutside dispatchOutside, List<String> engineerIdList);

    /**
     * 报价审批
     * @param repairHelpQuotationApproval 外援维修报价审批
     * @param user 用户
     */
    ApprovalApplication quotationApproval(@NotNull RepairHelpQuotationApproval repairHelpQuotationApproval, @EntityCheck(checkId = true) User user);

    /**
     * 设备快递已寄件
     * @param dispatchOutsideId 外部派工id
     * @param mailSendInfoJson 寄件信息
     */
    void mailSend(String dispatchOutsideId, String mailSendInfoJson);

    /**
     * 设备快递已收件
     * @param dispatchOutsideId 外部派工id
     * @param mailReceiveInfoJson 收件信息
     */
    void mailReceive(String dispatchOutsideId, String mailReceiveInfoJson);

    /**
     * 签订合同
     * @param dispatchOutsideId 外部派工id
     * @param contract 合同
     */
    void signContract(@NotBlank String dispatchOutsideId, @NotNull Contract contract);

    /**
     * 外部派工开始维修
     * @param dispatchOutsideId 外部派工id
     */
    void startRepair(@NotBlank String dispatchOutsideId);

    /**
     * 外部派工结束维修
     * @param dispatchOutsideId 外部派工id
     * @param result 维修结果:0-维修成功 1-等待配件 2-维修失败
     */
    void endRepair(@NotBlank String dispatchOutsideId, @NotNull Integer result, String mailReceiveInfo);

    /**
     * 外部派工违约
     * @param dispatchOutsideId 外部派工id
     * @param remark 备注
     */
    void breakContract(@NotBlank String dispatchOutsideId, String remark);

    /**
     * 维修验收成功
     * @param requestId 报修id
     */
    void check(String requestId, Boolean isSuccess);
}