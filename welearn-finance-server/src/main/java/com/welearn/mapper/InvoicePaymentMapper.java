package com.welearn.mapper;

import com.welearn.entity.po.finance.InvoicePayment;
import com.welearn.entity.qo.finance.InvoicePaymentQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * InvoicePayment Mapper Interface : ryme_finance : invoice_payment
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/8 18:19:48
 * @see com.welearn.entity.po.finance.InvoicePayment
 */
@Mapper 
public interface InvoicePaymentMapper extends BaseMapper<InvoicePayment, InvoicePaymentQueryCondition> {
    
}