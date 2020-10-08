package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.ControllerGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.supplier.SupplierCard;
import com.welearn.entity.qo.supplier.SupplierCardQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.SupplierCardService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/supplierCard")
@Api(value = "supplierCard 接口")
public class SupplierCardController extends BaseController<SupplierCard, SupplierCardQueryCondition, SupplierCardService>{

    @RequestMapping(value = "/createIfNotExist")
    @ApiOperation(value = "如果不存在则创建", httpMethod = "POST")
    public CommonResponse createIfNotExist(@RequestBody SupplierCard supplierCard)  {
        service.createIfNotExist(supplierCard);
        return CommonResponse.getSuccessResponse();
    }
}