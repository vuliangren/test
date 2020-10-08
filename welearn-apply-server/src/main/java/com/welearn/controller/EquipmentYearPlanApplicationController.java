package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import com.welearn.entity.qo.apply.EquipmentYearPlanApplicationQueryCondition;
import com.welearn.entity.vo.request.apply.EquipmentYearPlanApplicationApproval;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.apply.EquipmentYearPlanApplicationInfo;
import com.welearn.service.EquipmentYearPlanApplicationHandlerService;
import com.welearn.service.EquipmentYearPlanApplicationService;
import com.welearn.util.PaginateUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description : 医院 年度设备计划 申请
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentYearPlanApplication")
public class EquipmentYearPlanApplicationController extends ApplicationController<EquipmentYearPlanApplication, EquipmentYearPlanApplicationHandlerService> {
    
    @Autowired
    private EquipmentYearPlanApplicationService equipmentYearPlanApplicationService;

    @RequestMapping(value = "/select")
    @ApiOperation(value = "数据单个查询", httpMethod = "POST")
    public CommonResponse<EquipmentYearPlanApplication> select(@RequestBody String id) {
        return new CommonResponse<>(equipmentYearPlanApplicationService.select(id));
    }
    
    @RequestMapping(value = "/update")
    @ApiOperation(value = "数据更新", httpMethod = "POST")
    public CommonResponse disable(@RequestBody EquipmentYearPlanApplication entity) {
        equipmentYearPlanApplicationService.update(entity);
        return CommonResponse.getSuccessResponse();
    }
    
    @RequestMapping(value = "/search")
    @ApiOperation(value = "数据条件查询", httpMethod = "POST")
    public CommonResponse<List<EquipmentYearPlanApplication>> search(@RequestBody EquipmentYearPlanApplicationQueryCondition condition) {
        return new CommonResponse<>(PaginateUtil.page(() -> equipmentYearPlanApplicationService.search(condition)));
    }

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "数据详情条件查询", httpMethod = "POST")
    public CommonResponse<List<EquipmentYearPlanApplicationInfo>> searchInfo(@RequestBody EquipmentYearPlanApplicationQueryCondition condition){
        return new CommonResponse<>(PaginateUtil.page(() -> equipmentYearPlanApplicationService.searchInfo(condition)));
    }

    @RequestMapping(value = "/committeeApproval")
    @ApiOperation(value = "装备委员会评审", httpMethod = "POST")
    public CommonResponse committeeApproval(@RequestBody EquipmentYearPlanApplicationApproval entity) {
        equipmentYearPlanApplicationService.committeeApproval(entity.getEquipmentYearPlanApplicationId(), entity.getIsPassed(), entity.getResultJson());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/directorApproval")
    @ApiOperation(value = "院长评审", httpMethod = "POST")
    public CommonResponse directorApproval(@RequestBody EquipmentYearPlanApplicationApproval entity) {
        equipmentYearPlanApplicationService.directorApproval(entity.getEquipmentYearPlanApplicationId(), entity.getIsPassed(), entity.getResultJson());
        return CommonResponse.getSuccessResponse();
    }
}