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
 * Persistent Object : ryme_equipment : repair_interrupt
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/7 9:55:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairInterrupt", description = "RepairInterrupt 领域实体")
public class RepairInterrupt extends BasePersistant
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
     * Description  : 申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "申请id", allowEmptyValue = true, position = 5 )
    private String applyId;
    
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 6 )
    private String equipmentId;
    
    /**
     * Description  : 内部派工id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "内部派工id", allowEmptyValue = false, position = 7 )
    private String dispatchId;
    
    /**
     * Description  : 工程师id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师id", allowEmptyValue = false, position = 8 )
    private String engineerId;
    
    /**
     * Description  : 工程师名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师名称", allowEmptyValue = false, position = 9 )
    private String engineerName;
    
    /**
     * Description  : 中止原因
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @NotBlank
    @ApiModelProperty( value = "中止原因", allowEmptyValue = false, position = 10 )
    private String reason;
    
    /**
     * Description  : 审批结果：0-通过 1-驳回
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     */
    @ApiModelProperty( value = "审批结果：0-通过 1-驳回", allowEmptyValue = true, position = 11 )
    private Boolean result;
    
}
