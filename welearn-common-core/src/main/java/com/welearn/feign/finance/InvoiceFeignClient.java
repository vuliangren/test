package com.welearn.feign.finance;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.finance.Invoice;
import com.welearn.entity.po.finance.InvoicePayment;
import com.welearn.entity.qo.finance.InvoiceQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;

import java.util.List;

/**
 * Description : welearn-finance-service / com.welearn.controller.InvoiceController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-finance-server", configuration = FeignConfiguration.class)
public interface InvoiceFeignClient {

    @RequestMapping(value = "/invoice/cancelRef")
    CommonResponse cancelRef(String string);

    @RequestMapping(value = "/invoice/payment")
    CommonResponse payment(InvoicePayment invoicePayment);

    @RequestMapping(value = "/invoice/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/invoice/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/invoice/update")
    CommonResponse update(Invoice entity);

    @RequestMapping(value = "/invoice/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/invoice/create")
    CommonResponse<Invoice> create(Invoice entity);

    @RequestMapping(value = "/invoice/search")
    CommonResponse<List<Invoice>> search(InvoiceQueryCondition queryCondition);

    @RequestMapping(value = "/invoice/select")
    CommonResponse<Invoice> select(String id);
}