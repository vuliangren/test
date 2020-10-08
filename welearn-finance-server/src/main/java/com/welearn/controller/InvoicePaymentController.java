package com.welearn.controller;

import com.welearn.entity.po.finance.InvoicePayment;
import com.welearn.entity.qo.finance.InvoicePaymentQueryCondition;
import com.welearn.service.InvoicePaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/invoicePayment")
public class InvoicePaymentController extends BaseController<InvoicePayment, InvoicePaymentQueryCondition, InvoicePaymentService>{

}