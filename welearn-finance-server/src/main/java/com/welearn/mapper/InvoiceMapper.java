package com.welearn.mapper;

import com.welearn.entity.po.finance.Invoice;
import com.welearn.entity.qo.finance.InvoiceQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Invoice Mapper Interface : ryme_finance : invoice
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/8 18:19:47
 * @see com.welearn.entity.po.finance.Invoice
 */
@Mapper 
public interface InvoiceMapper extends BaseMapper<Invoice, InvoiceQueryCondition> {

    /**
     * 查询未关联项目的发票
     * @param condition 条件
     * @return 发票列表
     */
    List<Invoice> searchUnRef(InvoiceQueryCondition condition);
}