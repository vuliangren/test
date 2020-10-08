package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.equipment.SparePartOutRepairApply;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.SparePartOutBillService;
import com.welearn.service.SparePartOutRepairApplyService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.SparePartOutItem;
import com.welearn.entity.qo.equipment.SparePartOutItemQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.SparePartOutItemService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/sparePartOutItem")
@Api(value = "sparePartOutItem 接口")
public class SparePartOutItemController extends BaseController<SparePartOutItem, SparePartOutItemQueryCondition, SparePartOutItemService>{

    @RequestMapping(value = "/batchCreate")
    @ApiOperation(value = "batchCreate", httpMethod = "POST")
    public CommonResponse batchCreate(@RequestBody List<SparePartOutItem> list)  {
        service.batchCreate(list);
        return CommonResponse.getSuccessResponse();
    }
}