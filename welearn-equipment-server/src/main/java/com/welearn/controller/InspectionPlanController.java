package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.InspectionPlan;
import com.welearn.entity.qo.equipment.InspectionPlanQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.InspectionPlanService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/inspectionPlan")
@Api(value = "inspectionPlan 接口")
public class InspectionPlanController extends BaseController<InspectionPlan, InspectionPlanQueryCondition, InspectionPlanService>{
}