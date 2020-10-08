package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.request.equipment.BargainPricePartProcurementFinish;
import com.welearn.entity.vo.request.equipment.CancelCheck;
import com.welearn.generator.ControllerGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.qo.equipment.RepairReplacementQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RepairReplacementService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairReplacement")
@Api(value = "repairReplacement 接口")
public class RepairReplacementController extends BaseController<RepairReplacement, RepairReplacementQueryCondition, RepairReplacementService>{

    @RequestMapping(value = "/cancel")
    @ApiOperation(value = "取消维修配件更换申请", httpMethod = "POST")
    public CommonResponse cancel(@RequestBody String string)  {
        service.cancel(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/procurementFinish")
    @ApiOperation(value = "配件采购完成(维修方负责采购)", httpMethod = "POST")
    public CommonResponse procurementFinish(@RequestBody BargainPricePartProcurementFinish bargainPricePartProcurementFinish)  {
        service.procurementFinish(bargainPricePartProcurementFinish.getReplacementId(), bargainPricePartProcurementFinish.getPrice());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/replacementApplyAutoSubmit")
    @ApiOperation(value = "配件更换申请自动提交", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> replacementApplyAutoSubmit(@RequestBody RepairReplacement replacement, @RequestUser User user)  {
        ApprovalApplication result = service.replacementApplyAutoSubmit(replacement, user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/cancelCheck")
    @ApiOperation(value = "cancelCheck", httpMethod = "POST")
    public CommonResponse cancelCheck(@RequestBody CancelCheck cancelCheck)  {
        service.cancelCheck(cancelCheck.getReplacementId(), cancelCheck.getSignatureId());
        return CommonResponse.getSuccessResponse();
    }
}