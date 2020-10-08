package com.welearn.service.impl;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.dictionary.common.PersistantConst;
import com.welearn.dictionary.finance.InvoiceStatusConst;
import com.welearn.entity.po.finance.Invoice;
import com.welearn.entity.po.finance.InvoicePayment;
import com.welearn.entity.qo.finance.InvoiceQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.procurement.ProcurementFeignClient;
import com.welearn.generator.ControllerGenerator;
import com.welearn.mapper.InvoiceMapper;
import com.welearn.service.InvoicePaymentService;
import com.welearn.service.InvoiceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.finance.InvoiceStatusConst.*;

/**
 * Description : InvoiceService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class InvoiceServiceImpl extends BaseServiceImpl<Invoice,InvoiceQueryCondition,InvoiceMapper>
        implements InvoiceService{
    
    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoicePaymentService invoicePaymentService;

    @Autowired
    private ProcurementFeignClient procurementFeignClient;

    @Override
    InvoiceMapper getMapper() {
        return invoiceMapper;
    }

    /**
     * 发票结算
     *
     * @param payment 结算信息 
     */
    @Override @Transactional
    public void payment(InvoicePayment payment) {
        Invoice invoice = this.select(payment.getInvoiceId());
        if (Objects.isNull(invoice) || !invoice.getIsEnable())
            throw new BusinessVerifyFailedException("invoiceId 非法");
        Double balance = invoice.getBalance();
        Double amount = payment.getAmount();
        if (balance > amount){
            // 部分结算
            invoice.setBalance(balance - amount); 
            invoice.setStatus(PART_SETTLED.ordinal());
        }
        else {
            // 全部结算
            invoice.setBalance(0d);
            invoice.setStatus(ALL_SETTLED.ordinal());
            invoice.setClosedAt(new Date());
        }
        this.update(invoice);
        invoicePaymentService.create(payment);
        // 调用回调
        this.callback(invoice, payment);
    }



    /**
     * 结算回调
     *
     * @param invoice 发票信息
     * @param payment 结算信息
     */
    @Override
    public void callback(Invoice invoice, InvoicePayment payment) {
        if (StringUtils.isBlank(invoice.getRefId()) || StringUtils.isBlank(invoice.getRefType()))
            return;
        // 对应采购项目的发票处理
        if (PersistantConst.Procurement.name().equals(invoice.getRefType())){
            InvoiceQueryCondition condition = new InvoiceQueryCondition();
            condition.setIsEnable(true);
            condition.setRefId(invoice.getRefId());
            condition.setRefType(PersistantConst.Procurement.name());
            List<Invoice> invoices = this.search(condition);
            boolean isFinish = invoices.stream().allMatch(i -> i.getStatus() == InvoiceStatusConst.ALL_SETTLED.ordinal());
            if (isFinish)
                procurementFeignClient.settlementPayment(invoice.getRefId());
        }
    }

    /**
     * 查询未关联项目的发票
     *
     * @param condition 条件
     * @return 发票列表
     */
    @Override
    public List<Invoice> searchUnRef(InvoiceQueryCondition condition) {
        return invoiceMapper.searchUnRef(condition);
    }

    /**
     * 取消关联
     *
     * @param invoiceId 发票id
     */
    @Override
    public void cancelRef(String invoiceId) {
        Invoice invoice = this.select(invoiceId);
        if (Objects.isNull(invoice) || !invoice.getIsEnable())
            throw new BusinessVerifyFailedException("invoiceId 非法");
        invoice.setRefId(null);
        invoice.setRefType(null);
        invoiceMapper.updateByPK(invoice);
    }
}
