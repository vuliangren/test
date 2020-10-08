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
 * Persistent Object : ryme_equipment : inspection_plan
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/12 14:55:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "InspectionPlan", description = "InspectionPlan 领域实体")
public class InspectionPlan extends BasePersistant
{
    /**
     * Description  : 编号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "编号", allowEmptyValue = false, position = 4 )
    private String no;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "公司id", allowEmptyValue = false, position = 5 )
    private String companyId;
    
    /**
     * Description  : 公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "公司名称", allowEmptyValue = false, position = 6 )
    private String companyName;
    
    /**
     * Description  : 设备类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型id", allowEmptyValue = false, position = 7 )
    private String equipmentTypeId;
    
    /**
     * Description  : 设备类型名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "设备类型名称", allowEmptyValue = false, position = 8 )
    private String equipmentTypeName;
    
    /**
     * Description  : 设备产品id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备产品id", allowEmptyValue = false, position = 9 )
    private String equipmentProductId;
    
    /**
     * Description  : 设备产品名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "设备产品名称", allowEmptyValue = false, position = 10 )
    private String equipmentProductName;
    
    /**
     * Description  : 简述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "简述", allowEmptyValue = false, position = 11 )
    private String description;
    
    /**
     * Description  : 准确度
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "准确度", allowEmptyValue = true, position = 12 )
    private String accuracy;
    
    /**
     * Description  : 等级
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "等级", allowEmptyValue = true, position = 13 )
    private String lever;
    
    /**
     * Description  : 测量范围
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "测量范围", allowEmptyValue = true, position = 14 )
    private String range;
    
    /**
     * Description  : 计划生效时间(当前时间大于生效时间计划才有效)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "计划生效时间(当前时间大于生效时间计划才有效)", allowEmptyValue = true, position = 15 )
    private Date effectiveAt;
    
    /**
     * Description  : 检定周期(天)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 365
     */
    @ApiModelProperty( value = "检定周期(天)", allowEmptyValue = true, position = 16 )
    private Integer period;
    
    /**
     * Description  : 提前提醒(天)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 30
     */
    @ApiModelProperty( value = "提前提醒(天)", allowEmptyValue = true, position = 17 )
    private Integer remindDay;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 18 )
    private String remark;
    
}
