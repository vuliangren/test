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
 * Persistent Object : ryme_equipment : maintenance_cost_item
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 16:20:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MaintenanceCostItem", description = "MaintenanceCostItem 领域实体")
public class MaintenanceCostItem extends BasePersistant
{
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 4 )
    private String equipmentId;
    
    /**
     * Description  : 维护任务id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "维护任务id", allowEmptyValue = false, position = 5 )
    private String taskId;
    
    /**
     * Description  : 任务分配id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "任务分配id", allowEmptyValue = false, position = 6 )
    private String taskAssignmentId;
    
    /**
     * Description  : 工程师id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师id", allowEmptyValue = false, position = 7 )
    private String engineerId;
    
    /**
     * Description  : 工程师名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "工程师名称", allowEmptyValue = false, position = 8 )
    private String engineerName;
    
    /**
     * Description  : 类型:0-配件 1-维修 2-其它
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "类型:0-配件 1-维修 2-其它", allowEmptyValue = true, position = 9 )
    private Integer type;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 10 )
    private String name;
    
    /**
     * Description  : 规格
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "规格", allowEmptyValue = true, position = 11 )
    private String specification;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "描述", allowEmptyValue = true, position = 12 )
    private String description;
    
    /**
     * Description  : 单价
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "单价", allowEmptyValue = false, position = 13 )
    private Double price;
    
    /**
     * Description  : 数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "数量", allowEmptyValue = true, position = 14 )
    private Integer count;
    
    /**
     * Description  : 合计
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:decimal][SCALE:2][PRECISION:14]
     */
    @NotNull
    @ApiModelProperty( value = "合计", allowEmptyValue = false, position = 15 )
    private Double sumPrice;
    
}
