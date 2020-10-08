package com.welearn.service;

import com.welearn.entity.po.finance.InvoicePayment;
import com.welearn.entity.qo.finance.InvoicePaymentQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : InvoicePaymentService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface InvoicePaymentService extends BaseService<InvoicePayment, InvoicePaymentQueryCondition>{

}