package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.apply.LargeEquipmentApplication;
import com.welearn.service.LargeEquipmentApplicationHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 大型医疗设备装备申请
 * Created by Setsuna Jin on 2018/10/31.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/largeEquipmentApplication")
public class LargeEquipmentApplicationController extends ApplicationController<LargeEquipmentApplication, LargeEquipmentApplicationHandlerService> {


}