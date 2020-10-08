package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.finance.FixedAssets;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.FixedAssetsService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.finance.Invoice;
import com.welearn.entity.po.finance.InvoicePayment;
import com.welearn.entity.qo.finance.InvoiceQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.InvoiceService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/invoice")
public class InvoiceController extends BaseController<Invoice, InvoiceQueryCondition, InvoiceService>{

    @RequestMapping(value = "/payment")
    @ApiOperation(value = "发票结算", httpMethod = "POST")
    public CommonResponse payment(@RequestBody InvoicePayment invoicePayment) {
        service.payment(invoicePayment);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/searchUnRef")
    @ApiOperation(value = "查询未关联项目的发票", httpMethod = "POST")
    public CommonResponse<List<Invoice>> searchUnRef(@RequestBody InvoiceQueryCondition invoiceQueryCondition)  {
        List<Invoice> result = service.searchUnRef(invoiceQueryCondition);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/cancelRef")
    @ApiOperation(value = "取消发票关联的项目", httpMethod = "POST")
    public CommonResponse cancelRef(@RequestBody String string)  {
        service.cancelRef(string);
        return CommonResponse.getSuccessResponse();
    }

}