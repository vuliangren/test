package com.welearn.entity.po.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_common : supplier_register
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/23 17:15:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SupplierRegister extends BasePersistant
{
    /**
     * Description  : 公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    private String companyName;
    
    /**
     * Description  : 统一社会信用代码
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    private String unifiedSocialCreditCode;
    
    /**
     * Description  : 供应商类型
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:256]
     */
    @NotBlank
    private String supplierType;
    
    /**
     * Description  : 供应商级别
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:int][PRECISION:10]
     */
    @NotNull
    private Integer supplierLevel;
    
    /**
     * Description  : 销售负责人姓名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    private String headOfSalesName;
    
    /**
     * Description  : 负责人身份证号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    private String headOfSalesIdCard;
    
    /**
     * Description  : 销售负责人手机
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:44]
     */
    @NotBlank
    private String headOfSalesPhoneNumber;
    
    /**
     * Description  : 销售负责人邮件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:1024]
     */
    @NotBlank
    private String headOfSalesEmail;
    
    /**
     * Description  : 销售负责人密码
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:1024]
     */
    @NotBlank
    private String headOfSalesPassword;

    @JsonIgnore
    public String getHeadOfSalesPassword() {
        return headOfSalesPassword;
    }

    public void setHeadOfSalesPassword(String headOfSalesPassword) {
        this.headOfSalesPassword = headOfSalesPassword;
    }

    /**
     * Description  : 销售负责人的身份证加盖企业公章复印件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:text][SIZE:65535]
     */
    @NotBlank
    private String idCardPhotocopy;
    
    /**
     * Description  : 账户邮件激活码
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:512]
     */
    private String activeCode;

    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:2048]
     */
    private String remark;

    /**
     * Description  : 审批人id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    private String approverId;

    /**
     * Description  : 结果
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:2048]
     */
    private String result;

    /**
     * Description  : 状态: 0-待审批 1-审批通过 2-审批失败
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    private Integer status;
}
