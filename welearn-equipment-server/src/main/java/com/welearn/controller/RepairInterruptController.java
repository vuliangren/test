package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.RepairInterrupt;
import com.welearn.entity.qo.equipment.RepairInterruptQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RepairInterruptService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairInterrupt")
@Api(value = "repairInterrupt 接口")
public class RepairInterruptController extends BaseController<RepairInterrupt, RepairInterruptQueryCondition, RepairInterruptService>{

    @RequestMapping(value = "/afterApplicationReject")
    @ApiOperation(value = "afterApplicationReject", httpMethod = "POST")
    public CommonResponse afterApplicationReject(@RequestBody String string)  {
        service.afterApplicationReject(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/afterApplicationPass")
    @ApiOperation(value = "afterApplicationPass", httpMethod = "POST")
    public CommonResponse afterApplicationPass(@RequestBody String string)  {
        service.afterApplicationPass(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/repairInterruptAutoSubmit")
    @ApiOperation(value = "维修中止申请自动提交", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> repairInterruptAutoSubmit(@RequestBody RepairInterrupt repairInterrupt, @RequestUser User user)  {
        ApprovalApplication result = service.repairInterruptAutoSubmit(repairInterrupt, user);
        return new CommonResponse<>(result);
    }
}