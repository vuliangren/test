package com.welearn.entity.qo.finance;

import com.welearn.entity.po.finance.Invoice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : Invoice Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/8 18:19:48
 * @see com.welearn.entity.po.finance.Invoice
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InvoiceQueryCondition extends Invoice {
    private Date issuedAtStart;
    private Date issuedAtEnd;
}
