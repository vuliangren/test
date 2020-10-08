package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.service.LowPricePartProcurementHandlerService;
import com.welearn.service.OverPricePartProcurementHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 低价配件采购申请
 * Created by Setsuna Jin on 2018/10/31.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/lowPricePartProcurement")
public class LowPricePartProcurementApplicationController extends ApplicationController<RepairReplacement, LowPricePartProcurementHandlerService> {


}