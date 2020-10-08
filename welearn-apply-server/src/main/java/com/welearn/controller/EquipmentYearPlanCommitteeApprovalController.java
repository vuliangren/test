package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.apply.EquipmentYearPlan;
import com.welearn.service.EquipmentYearPlanCommitteeApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 医疗设备装备委员会 审批 设备计划
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentYearPlanCommitteeApproval")
public class EquipmentYearPlanCommitteeApprovalController extends ApplicationController<EquipmentYearPlan, EquipmentYearPlanCommitteeApprovalService> {


}