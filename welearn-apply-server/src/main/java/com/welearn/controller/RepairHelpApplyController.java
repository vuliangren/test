package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.equipment.RepairHelpApply;
import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.service.RepairHelpApplyHandlerService;
import com.welearn.service.SparePartStockInHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 申请第三方维修
 * Created by Setsuna Jin on 2018/10/31.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairHelpApply")
public class RepairHelpApplyController extends ApplicationController<RepairHelpApply, RepairHelpApplyHandlerService> {

}