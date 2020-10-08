package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.apply.LargeEquipmentApplication;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.service.HighPricePartProcurementHandlerService;
import com.welearn.service.LargeEquipmentApplicationHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 高价配件采购申请
 * Created by Setsuna Jin on 2018/10/31.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/highPricePartProcurement")
public class HighPricePartProcurementApplicationController extends ApplicationController<RepairReplacement, HighPricePartProcurementHandlerService> {


}