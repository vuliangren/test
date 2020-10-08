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
 * Persistent Object : ryme_equipment : task_assignment
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 11:05:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TaskAssignment", description = "TaskAssignment 领域实体")
public class TaskAssignment extends BasePersistant
{
    /**
     * Description  : 维护设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "维护设备id", allowEmptyValue = false, position = 4 )
    private String equipmentId;
    
    /**
     * Description  : 维护设备描述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @NotBlank
    @ApiModelProperty( value = "维护设备描述", allowEmptyValue = false, position = 5 )
    private String equipmentDescription;
    
    /**
     * Description  : 维护设备位置
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @NotBlank
    @ApiModelProperty( value = "维护设备位置", allowEmptyValue = false, position = 6 )
    private String equipmentPosition;
    
    /**
     * Description  : 维护计划类型: 0-预防性维护 1-定期保养
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "维护计划类型: 0-预防性维护 1-定期保养", allowEmptyValue = true, position = 7 )
    private Integer taskType;
    
    /**
     * Description  : 维护任务id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "维护任务id", allowEmptyValue = false, position = 8 )
    private String taskId;
    
    /**
     * Description  : 维护人员id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "维护人员id", allowEmptyValue = false, position = 9 )
    private String engineerId;
    
    /**
     * Description  : 维护人员名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "维护人员名称", allowEmptyValue = false, position = 10 )
    private String engineerName;
    
    /**
     * Description  : 处理优先级(任务优先级决定基础高度, 催促或延期执行后, 处理优先级会在基础值上升高)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "处理优先级(任务优先级决定基础高度, 催促或延期执行后, 处理优先级会在基础值上升高)", allowEmptyValue = true, position = 11 )
    private Integer priority;
    
    /**
     * Description  : 任务状态: 0-待领取 1-待处理 2-处理中 3-已处理 4-已取消
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "任务状态: 0-待领取 1-待处理 2-处理中 3-已处理 4-已取消", allowEmptyValue = true, position = 12 )
    private Integer status;
    
    /**
     * Description  : 维护记录id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "维护记录id", allowEmptyValue = true, position = 13 )
    private String recordId;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "公司id", allowEmptyValue = false, position = 14 )
    private String companyId;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 15 )
    private String remark;
    
}
