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
 * Persistent Object : ryme_equipment : maintenance_task
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 11:04:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MaintenanceTask", description = "MaintenanceTask 领域实体")
public class MaintenanceTask extends BasePersistant
{
    /**
     * Description  : 任务类型: 0-类型 1-产品 2-设备 3-全部
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @ApiModelProperty( value = "任务类型: 0-类型 1-产品 2-设备 3-全部", allowEmptyValue = true, position = 4 )
    private Integer serveType;
    
    /**
     * Description  : 类型/产品/设备id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "类型/产品/设备id", allowEmptyValue = true, position = 5 )
    private String serveId;
    
    /**
     * Description  : 适用类型名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "适用类型名称", allowEmptyValue = false, position = 6 )
    private String serveName;
    
    /**
     * Description  : 任务领取类型: 0-指派 1-抢单
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "任务领取类型: 0-指派 1-抢单", allowEmptyValue = true, position = 7 )
    private Integer orderType;
    
    /**
     * Description  : 维护计划类型: 0-预防性维护 1-定期保养
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "维护计划类型: 0-预防性维护 1-定期保养", allowEmptyValue = true, position = 8 )
    private Integer taskType;
    
    /**
     * Description  : 任务优先级(决定处理优先级的基础高度)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "任务优先级(决定处理优先级的基础高度)", allowEmptyValue = true, position = 9 )
    private Integer priority;
    
    /**
     * Description  : 任务开始时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "任务开始时间", allowEmptyValue = true, position = 10 )
    private Date startAt;
    
    /**
     * Description  : 任务时间间隔(分钟)(0 表示不会循环为单次任务)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "任务时间间隔(分钟)(0 表示不会循环为单次任务)", allowEmptyValue = true, position = 11 )
    private Integer interval;
    
    /**
     * Description  : 时间单位 0-分 60-时 1440-天 10080-周
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "时间单位 0-分 60-时 1440-天 10080-周", allowEmptyValue = true, position = 12 )
    private Integer timeUnit;
    
    /**
     * Description  : 任务名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "任务名称", allowEmptyValue = false, position = 13 )
    private String name;
    
    /**
     * Description  : 任务描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "任务描述", allowEmptyValue = true, position = 14 )
    private String description;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "公司id", allowEmptyValue = false, position = 15 )
    private String companyId;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 16 )
    private String remark;
    
}
