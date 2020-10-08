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
 * Persistent Object : ryme_equipment : repair_precept
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 19:46:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RepairPrecept", description = "RepairPrecept 领域实体")
public class RepairPrecept extends BasePersistant
{
    /**
     * Description  : 适用类型: 0-类型 1-产品 2-设备 3-全部
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "适用类型: 0-类型 1-产品 2-设备 3-全部", allowEmptyValue = false, position = 4 )
    private Integer serveType;
    
    /**
     * Description  : 适用 类型/产品/设备id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "适用 类型/产品/设备id", allowEmptyValue = true, position = 5 )
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
     * Description  : 故障简述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "故障简述", allowEmptyValue = false, position = 7 )
    private String outlook;
    
    /**
     * Description  : 故障详述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @NotBlank
    @ApiModelProperty( value = "故障详述", allowEmptyValue = false, position = 8 )
    private String detail;
    
    /**
     * Description  : 紧急程度(同报修紧急程度)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "紧急程度(同报修紧急程度)", allowEmptyValue = true, position = 9 )
    private Integer lever;
    
    /**
     * Description  : 报修方建议
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "报修方建议", allowEmptyValue = true, position = 10 )
    private String reporterAdvice;
    
    /**
     * Description  : 维修方建议
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "维修方建议", allowEmptyValue = true, position = 11 )
    private String engineerAdvice;
    
    /**
     * Description  : 所属公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "所属公司id", allowEmptyValue = false, position = 12 )
    private String companyId;
    
    /**
     * Description  : 命中次数
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "命中次数", allowEmptyValue = true, position = 13 )
    private Integer hitCount;
    
    /**
     * Description  : 创建工程师id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "创建工程师id", allowEmptyValue = false, position = 14 )
    private String engineerId;
    
    /**
     * Description  : 创建工程师姓名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "创建工程师姓名", allowEmptyValue = false, position = 15 )
    private String engineerName;
    
}
