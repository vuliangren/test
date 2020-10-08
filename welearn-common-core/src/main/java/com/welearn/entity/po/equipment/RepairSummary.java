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
 * Persistent Object : ryme_equipment : repair_summary
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 19:46:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairSummary", description = "RepairSummary 领域实体")
public class RepairSummary extends BasePersistant
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
     * Description  : 工程师名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师名称", allowEmptyValue = false, position = 6 )
    private String engineerName;
    
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 7 )
    private String equipmentId;
    
    /**
     * Description  : 设备名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备名称", allowEmptyValue = false, position = 8 )
    private String equipmentName;
    
    /**
     * Description  : 故障描述(富文本)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "故障描述(富文本)", allowEmptyValue = false, position = 9 )
    private String description;
    
    /**
     * Description  : 起因分析(富文本)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "起因分析(富文本)", allowEmptyValue = false, position = 10 )
    private String reason;
    
    /**
     * Description  : 维修方式: 0-厂家保修 1-自主维修 2-外援维修 3-其它
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "维修方式: 0-厂家保修 1-自主维修 2-外援维修 3-其它", allowEmptyValue = false, position = 11 )
    private Integer method;
    
    /**
     * Description  : 处理方法(富文本)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "处理方法(富文本)", allowEmptyValue = false, position = 12 )
    private String handleRecord;
    
    /**
     * Description  : 给维修方建议
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "给维修方建议", allowEmptyValue = true, position = 13 )
    private String engineerAdvice;
    
    /**
     * Description  : 给报修方建议
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "给报修方建议", allowEmptyValue = true, position = 14 )
    private String reporterAdvice;
    
}
