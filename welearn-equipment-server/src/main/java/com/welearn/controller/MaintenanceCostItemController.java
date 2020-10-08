package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.MaintenanceCostItem;
import com.welearn.entity.qo.equipment.MaintenanceCostItemQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.MaintenanceCostItemService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/maintenanceCostItem")
@Api(value = "maintenanceCostItem 接口")
public class MaintenanceCostItemController extends BaseController<MaintenanceCostItem, MaintenanceCostItemQueryCondition, MaintenanceCostItemService>{
}