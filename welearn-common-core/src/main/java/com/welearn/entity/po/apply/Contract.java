package com.welearn.entity.po.apply;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_apply : contract
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/25 16:14:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contract extends BasePersistant
{
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String companyId;
    
    /**
     * Description  : 公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:256]
     */
    @NotBlank
    private String companyName;
    
    /**
     * Description  : 部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String departmentId;
    
    /**
     * Description  : 部门部门
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:256]
     */
    @NotBlank
    private String departmentName;
    
    /**
     * Description  : 创建人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String creatorId;
    
    /**
     * Description  : 创建人名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String creatorName;
    
    /**
     * Description  : 合同目标id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String targetId;
    
    /**
     * Description  : 合同类型
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    private Integer type;
    
    /**
     * Description  : 合同简述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String outlook;
    
    /**
     * Description  : 合同编号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String no;
    
    /**
     * Description  : 合同甲方
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:512]
     */
    @NotBlank
    private String firstPart;
    
    /**
     * Description  : 合同乙方
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:512]
     */
    @NotBlank
    private String secondPart;
    
    /**
     * Description  : 生效日期
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @NotNull
    private Date effectiveAt;
    
    /**
     * Description  : 过期日期
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:timestamp]
     */
    private Date expiredAt;
    
    /**
     * Description  : 合同复印件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:text][SIZE:65535]
     */
    @NotBlank
    private String photocopy;
    
    /**
     * Description  : 合同详细内容JSON存储
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:text][SIZE:65535]
     */
    private String contentJson;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:2048]
     */
    private String remark;
    
}
