package com.welearn.entity.po.finance;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_finance : invoice_payment
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/8 18:19:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InvoicePayment extends BasePersistant
{
    /**
     * Description  : 发票id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String invoiceId;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String companyId;
    
    /**
     * Description  : 操作人员id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String operatorId;
    
    /**
     * Description  : 操作人员名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String operatorName;
    
    /**
     * Description  : 结算金额
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    private Double amount;
    
    /**
     * Description  : 结算凭证
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String voucher;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:1024]
     */
    private String remark;
    
}
