package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.po.equipment.EquipmentScrapApply;
import com.welearn.service.EquipmentBorrowApplyHandlerService;
import com.welearn.service.EquipmentScrapApplyHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 设备借用申请
 * Created by Setsuna Jin on 2018/10/31.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentBorrowApply")
public class EquipmentBorrowApplyController extends ApplicationController<EquipmentBorrow, EquipmentBorrowApplyHandlerService> {

}