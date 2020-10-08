package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairHelpQuotationApproval;
import com.welearn.entity.vo.request.equipment.*;
import com.welearn.generator.ControllerGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.RepairDispatchOutside;
import com.welearn.entity.qo.equipment.RepairDispatchOutsideQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RepairDispatchOutsideService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairDispatchOutside")
@Api(value = "repairDispatchOutside 接口")
public class RepairDispatchOutsideController extends BaseController<RepairDispatchOutside, RepairDispatchOutsideQueryCondition, RepairDispatchOutsideService>{

    @RequestMapping(value = "/createDispatch")
    @ApiOperation(value = "创建外部派工", httpMethod = "POST")
    public CommonResponse createDispatch(@RequestBody CreateDispatchOutside createDispatch)  {
        service.createDispatch(createDispatch.getDispatchOutside(), createDispatch.getEngineerIdList());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/breakContract")
    @ApiOperation(value = "外部派工违约", httpMethod = "POST")
    public CommonResponse breakContract(@RequestBody BreakContract breakContract)  {
        service.breakContract(breakContract.getDispatchOutsideId(), breakContract.getRemark());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/startRepair")
    @ApiOperation(value = "开始维修", httpMethod = "POST")
    public CommonResponse startRepair(@RequestBody String string)  {
        service.startRepair(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/endRepair")
    @ApiOperation(value = "结束维修", httpMethod = "POST")
    public CommonResponse endRepair(@RequestBody EndRepair endRepair)  {
        service.endRepair(endRepair.getDispatchOutsideId(), endRepair.getResult(), endRepair.getMailReceiveInfo());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/mailSend")
    @ApiOperation(value = "邮寄信息登记", httpMethod = "POST")
    public CommonResponse mailSend(@RequestBody MailSend mailSend)  {
        service.mailSend(mailSend.getDispatchOutsideId(), mailSend.getMailSendInfoJson());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/signContract")
    @ApiOperation(value = "报价审批通过后签订合同", httpMethod = "POST")
    public CommonResponse signContract(@RequestBody SignContract signContract)  {
        service.signContract(signContract.getDispatchOutsideId(), signContract.getContract());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/mailReceive")
    @ApiOperation(value = "邮寄维修时 无论结果是 成功/失败/取消 都需要收件", httpMethod = "POST")
    public CommonResponse mailReceive(@RequestBody MailReceive mailReceive)  {
        service.mailReceive(mailReceive.getDispatchOutsideId(), mailReceive.getMailReceiveInfoJson());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/quotationApproval")
    @ApiOperation(value = "第三方报价后提供报价审批", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> quotationApproval(@RequestBody RepairHelpQuotationApproval repairHelpQuotationApproval, @RequestUser User user)  {
        ApprovalApplication result = service.quotationApproval(repairHelpQuotationApproval, user);
        return new CommonResponse<>(result);
    }
}