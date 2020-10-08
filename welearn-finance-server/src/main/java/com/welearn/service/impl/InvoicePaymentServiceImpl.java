package com.welearn.service.impl;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.finance.InvoicePayment;
import com.welearn.entity.qo.finance.InvoicePaymentQueryCondition;
import com.welearn.generator.ControllerGenerator;
import com.welearn.mapper.InvoicePaymentMapper;
import com.welearn.service.InvoicePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Description : InvoicePaymentService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class InvoicePaymentServiceImpl extends BaseServiceImpl<InvoicePayment,InvoicePaymentQueryCondition,InvoicePaymentMapper>
        implements InvoicePaymentService{
    
    @Autowired
    private InvoicePaymentMapper invoicePaymentMapper;
    
    @Override
    InvoicePaymentMapper getMapper() {
        return invoicePaymentMapper;
    }
}
