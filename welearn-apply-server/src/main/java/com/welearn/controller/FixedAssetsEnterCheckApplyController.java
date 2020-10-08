package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.equipment.RepairHelpApply;
import com.welearn.entity.po.finance.FixedAssets;
import com.welearn.service.FixedAssetsEnterCheckHandlerService;
import com.welearn.service.RepairHelpApplyHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description : 固定资产入账申请
 * Created by Setsuna Jin on 2018/10/31.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/fixedAssetsEnterCheck")
public class FixedAssetsEnterCheckApplyController extends ApplicationController<FixedAssets, FixedAssetsEnterCheckHandlerService> {

}