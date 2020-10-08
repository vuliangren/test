package com.welearn.entity.po.equipment;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_equipment : repair_help_quotation_approval
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/11 14:37:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairHelpQuotationApproval", description = "RepairHelpQuotationApproval 领域实体")
public class RepairHelpQuotationApproval extends BasePersistant
{
    /**
     * Description  : 报修id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "报修id", allowEmptyValue = false, position = 4 )
    private String requestId;
    
    /**
     * Description  : 派工id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "派工id", allowEmptyValue = false, position = 5 )
    private String dispatchId;
    
    /**
     * Description  : 工程师id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师id", allowEmptyValue = false, position = 6 )
    private String engineerId;
    
    /**
     * Description  : 工程师名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师名称", allowEmptyValue = false, position = 7 )
    private String engineerName;
    
    /**
     * Description  : 外部派工id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "外部派工id", allowEmptyValue = true, position = 8 )
    private String outsideDispatchId;
    
    /**
     * Description  : 外部工程师id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "外部工程师id", allowEmptyValue = true, position = 9 )
    private String outsideEngineerId;
    
    /**
     * Description  : 外部工程师名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "外部工程师名称", allowEmptyValue = true, position = 10 )
    private String outsideEngineerName;
    
    /**
     * Description  : 工程师签名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师签名", allowEmptyValue = false, position = 11 )
    private String signatureId;
    
    /**
     * Description  : 价格
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "价格", allowEmptyValue = false, position = 12 )
    private Double price;
    
    /**
     * Description  : 报价说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "报价说明", allowEmptyValue = true, position = 13 )
    private String description;
    
    /**
     * Description  : 申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "申请id", allowEmptyValue = true, position = 14 )
    private String applyId;
    
    /**
     * Description  : 状态：0-待审批 1-审批通过 2-审批失败
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态：0-待审批 1-审批通过 2-审批失败", allowEmptyValue = true, position = 15 )
    private Integer status;
    
}
