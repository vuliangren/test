package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.equipment.RepairInterrupt;
import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.RepairInterruptApplyHandlerService;
import com.welearn.service.SparePartStockInHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 维修中止申请
 * Created by Setsuna Jin on 2018/10/31.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairInterruptApply")
public class RepairInterruptApplyController extends ApplicationController<RepairInterrupt, RepairInterruptApplyHandlerService> {

}