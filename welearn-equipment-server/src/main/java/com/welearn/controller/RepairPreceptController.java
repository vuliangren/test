package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.ControllerGenerator;
import com.welearn.service.RepairDispatchInsideService;
import com.welearn.service.RepairRequestService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.RepairPrecept;
import com.welearn.entity.qo.equipment.RepairPreceptQueryCondition;
import com.welearn.entity.vo.request.equipment.SearchByEquipmentId;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RepairPreceptService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairPrecept")
@Api(value = "repairPrecept 接口")
public class RepairPreceptController extends BaseController<RepairPrecept, RepairPreceptQueryCondition, RepairPreceptService>{

    @RequestMapping(value = "/searchByEquipmentId")
    @ApiOperation(value = "searchByEquipmentId", httpMethod = "POST")
    public CommonResponse<List<RepairPrecept>> searchByEquipmentId(@RequestBody SearchByEquipmentId searchByEquipmentId)  {
        List<RepairPrecept> result = service.searchByEquipmentId(searchByEquipmentId.getEquipmentId(), searchByEquipmentId.getCompanyId());
        return new CommonResponse<>(result);
    }
}