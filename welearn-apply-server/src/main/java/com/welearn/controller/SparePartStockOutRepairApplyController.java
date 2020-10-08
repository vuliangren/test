package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.po.equipment.SparePartOutRepairApply;
import com.welearn.service.OverPricePartProcurementHandlerService;
import com.welearn.service.SparePartStockOutRepairApplyHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 配件出库维修用申请
 * Created by Setsuna Jin on 2018/10/31.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/sparePartStockOutRepairApply")
public class SparePartStockOutRepairApplyController extends ApplicationController<SparePartOutRepairApply, SparePartStockOutRepairApplyHandlerService> {

}