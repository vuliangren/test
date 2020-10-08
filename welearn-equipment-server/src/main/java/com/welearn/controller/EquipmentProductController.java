package com.welearn.controller;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.qo.equipment.EquipmentProductQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.EquipmentProductService;

import java.util.List;
import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentProduct")
public class EquipmentProductController extends BaseController<EquipmentProduct, EquipmentProductQueryCondition, EquipmentProductService>{

    @RequestMapping(value = "/technologyAcceptanceInfo")
    @ApiOperation(value = "查询设备产品技术验收需要的数据", httpMethod = "POST")
    public CommonResponse<Map<String, EquipmentProduct>> technologyAcceptanceInfo(@RequestBody List<String> productIds) {
        Map<String, EquipmentProduct> result = service.selectTechnologyAcceptanceInfo(productIds);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/updateEquipments")
    @ApiOperation(value = "更新产品关联的设备的信息", httpMethod = "POST")
    public CommonResponse updateEquipments(@RequestBody String productId)  {
        service.updateEquipments(productId);
        return CommonResponse.getSuccessResponse();
    }
}