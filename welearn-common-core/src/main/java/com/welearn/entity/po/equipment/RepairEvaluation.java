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
 * Persistent Object : ryme_equipment : repair_evaluation
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 19:46:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairEvaluation", description = "RepairEvaluation 领域实体")
public class RepairEvaluation extends BasePersistant
{
    /**
     * Description  : 报修id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "报修id", allowEmptyValue = false, position = 4 )
    private String requestId;
    
    /**
     * Description  : 工程师id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师id", allowEmptyValue = false, position = 5 )
    private String engineerId;
    
    /**
     * Description  : 工程师姓名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师姓名", allowEmptyValue = false, position = 6 )
    private String engineerName;
    
    /**
     * Description  : 守时评价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 5.00
     */
    @ApiModelProperty( value = "守时评价", allowEmptyValue = true, position = 7 )
    private Double punctual;
    
    /**
     * Description  : 态度评价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 5.00
     */
    @ApiModelProperty( value = "态度评价", allowEmptyValue = true, position = 8 )
    private Double attitude;
    
    /**
     * Description  : 服务评价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:5]
     * DefaultValue : 5.00
     */
    @ApiModelProperty( value = "服务评价", allowEmptyValue = true, position = 9 )
    private Double service;
    
    /**
     * Description  : 评语
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "评语", allowEmptyValue = true, position = 10 )
    private String comment;
    
    /**
     * Description  : 评价人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "评价人id", allowEmptyValue = false, position = 11 )
    private String appraiserid;
    
    /**
     * Description  : 评价人姓名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "评价人姓名", allowEmptyValue = false, position = 12 )
    private String appraiserName;
    
    /**
     * Description  : 评价人部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "评价人部门id", allowEmptyValue = false, position = 13 )
    private String appraiserDepartmentId;
    
    /**
     * Description  : 评价人部门名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "评价人部门名称", allowEmptyValue = false, position = 14 )
    private String appraiserDepartmentName;
    
}
