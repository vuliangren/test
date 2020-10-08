package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.vo.response.equipment.EquipmentBorrowAccessoryInfo;
import com.welearn.generator.ControllerGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.EquipmentBorrowAccessory;
import com.welearn.entity.qo.equipment.EquipmentBorrowAccessoryQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.EquipmentBorrowAccessoryService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentBorrowAccessory")
@Api(value = "equipmentBorrowAccessory 接口")
public class EquipmentBorrowAccessoryController extends BaseController<EquipmentBorrowAccessory, EquipmentBorrowAccessoryQueryCondition, EquipmentBorrowAccessoryService>{

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询设备借用附件详情信息", httpMethod = "POST")
    public CommonResponse<List<EquipmentBorrowAccessoryInfo>> searchInfo(@RequestBody EquipmentBorrowAccessoryQueryCondition equipmentBorrowAccessoryQueryCondition)  {
        List<EquipmentBorrowAccessoryInfo> result = service.searchInfo(equipmentBorrowAccessoryQueryCondition);
        return new CommonResponse<>(result);
    }
}