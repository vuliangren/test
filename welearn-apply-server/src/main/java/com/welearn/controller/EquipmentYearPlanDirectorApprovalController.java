package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.apply.EquipmentYearPlan;
import com.welearn.service.EquipmentYearPlanCommitteeApprovalService;
import com.welearn.service.EquipmentYearPlanDirectorApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 院长 审批 设备计划
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentYearPlanDirectorApproval")
public class EquipmentYearPlanDirectorApprovalController extends ApplicationController<EquipmentYearPlan, EquipmentYearPlanDirectorApprovalService> {


}