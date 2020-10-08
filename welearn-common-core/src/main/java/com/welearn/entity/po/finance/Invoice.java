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
 * Persistent Object : ryme_finance : invoice
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/8 18:19:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Invoice extends BasePersistant
{
    /**
     * Description  : 关联类型-表名
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String refType;
    
    /**
     * Description  : 关联类型id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String refId;
    
    /**
     * Description  : 关联简述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    private String description;
    
    /**
     * Description  : 发票类型:0-增值税普通发票, 1-增值税专用发票, 2-其他发票
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer type;
    
    /**
     * Description  : 发票代码
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:64]
     */
    @NotBlank
    private String code;
    
    /**
     * Description  : 发票号码
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:64]
     */
    @NotBlank
    private String number;
    
    /**
     * Description  : 开票日期
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:timestamp]
     * DefaultValue : 0000-00-00 00:00:00
     */
    @NotNull
    private Date issuedAt;
    
    /**
     * Description  : 结算日期
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:timestamp]
     */
    private Date closedAt;
    
    /**
     * Description  : 发票状态: 0-未结算, 1-已部分结算, 2-已全部结算
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer status;
    
    /**
     * Description  : 销售方公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String sellerId;
    
    /**
     * Description  : 销售方公司名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String sellerName;
    
    /**
     * Description  : 销售方公司纳税人识别号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String sellerCode;
    
    /**
     * Description  : 销售方公司地址
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    private String sellerAddress;
    
    /**
     * Description  : 销售方公司电话
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:64]
     */
    private String sellerPhone;
    
    /**
     * Description  : 销售方公司开户行
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String sellerBank;
    
    /**
     * Description  : 销售方公司账号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String sellerAccount;
    
    /**
     * Description  : 购买方公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String buyerId;
    
    /**
     * Description  : 购买方公司名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String buyerName;
    
    /**
     * Description  : 购买方公司纳税人识别号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String buyerCode;
    
    /**
     * Description  : 购买方公司地址
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    private String buyerAddress;
    
    /**
     * Description  : 购买方公司电话
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:64]
     */
    private String buyerPhone;
    
    /**
     * Description  : 购买方公司开户行
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String buyerBank;
    
    /**
     * Description  : 购买方公司账号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String buyerAccount;
    
    /**
     * Description  : 合计金额
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    private Double totalAmount;
    
    /**
     * Description  : 合计税额
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    private Double totalTax;
    
    /**
     * Description  : 待结金额
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    private Double balance;
    
    /**
     * Description  : 价税合计小写
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    private Double amountLower;
    
    /**
     * Description  : 价税合计大写
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String amountUpper;
    
    /**
     * Description  : 发票备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:2048]
     */
    private String remark;
    
    /**
     * Description  : 上传类型
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    private Integer uploadType;
    
    /**
     * Description  : 上传文件
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    private String uploadFile;
    
    /**
     * Description  : 发票明细
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:text][SIZE:65535]
     */
    @NotBlank
    private String detailJson;
    
}
