package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.RepairHelpApply;
import com.welearn.entity.qo.equipment.RepairHelpApplyQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RepairHelpApplyService;
import java.lang.String;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairHelpApply")
@Api(value = "repairHelpApply 接口")
public class RepairHelpApplyController extends BaseController<RepairHelpApply, RepairHelpApplyQueryCondition, RepairHelpApplyService>{
    @RequestMapping(value = "/afterApplicationPass")
    @ApiOperation(value = "afterApplicationPass", httpMethod = "POST")
    public CommonResponse afterApplicationPass(@RequestBody String string)  {
        service.afterApplicationPass(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/afterApplicationReject")
    @ApiOperation(value = "afterApplicationReject", httpMethod = "POST")
    public CommonResponse afterApplicationReject(@RequestBody String string)  {
        service.afterApplicationReject(string);
        return CommonResponse.getSuccessResponse();
    }

}