package com.welearn.controller;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.apply.EquipmentYearPlan;
import com.welearn.entity.qo.apply.EquipmentYearPlanQueryCondition;
import com.welearn.entity.vo.request.apply.CommitteeApproval;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.EquipmentYearPlanService;
import java.lang.String;

/**
 * Description : 年度设备计划
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentYearPlan")
public class EquipmentYearPlanController extends BaseController<EquipmentYearPlan, EquipmentYearPlanQueryCondition, EquipmentYearPlanService>{

    @RequestMapping(value = "/current")
    @ApiOperation(value = "当年设备计划", httpMethod = "POST")
    public CommonResponse<EquipmentYearPlan> current(@RequestBody String string)  {
        EquipmentYearPlan result = service.current(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/committeeApproval")
    @ApiOperation(value = "交由设备委员会审批设备计划", httpMethod = "POST")
    public CommonResponse committeeApproval(@RequestBody CommitteeApproval committeeApproval)  {
        service.committeeApproval(committeeApproval.getEquipmentYearPlanId(), committeeApproval.getUserId());
        return CommonResponse.getSuccessResponse();
    }

}