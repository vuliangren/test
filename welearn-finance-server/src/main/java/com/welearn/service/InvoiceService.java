package com.welearn.service;

import com.welearn.entity.po.finance.Invoice;
import com.welearn.entity.po.finance.InvoicePayment;
import com.welearn.entity.qo.finance.InvoiceQueryCondition;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : InvoiceService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface InvoiceService extends BaseService<Invoice, InvoiceQueryCondition>{

    /**
     * 发票结算
     * @param payment 结算信息
     */
    void payment(@NotNull InvoicePayment payment);

    /**
     * 结算回调
     * @param invoice 发票信息
     * @param payment 结算信息
     */
    void callback(@NotNull Invoice invoice, @NotNull InvoicePayment payment);

    /**
     * 查询未关联项目的发票
     * @param condition 条件
     * @return 发票列表
     */
    List<Invoice> searchUnRef(@NotNull InvoiceQueryCondition condition);

    /**
     * 取消关联
     * @param invoiceId 发票id
     */
    void cancelRef(String invoiceId);
}