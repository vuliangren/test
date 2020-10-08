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
 * Persistent Object : ryme_equipment : maintenance_method
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 11:04:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MaintenanceMethod", description = "MaintenanceMethod 领域实体")
public class MaintenanceMethod extends BasePersistant
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
     * Description  : 最短维护间隔(分钟)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "最短维护间隔(分钟)", allowEmptyValue = true, position = 7 )
    private Integer minInterval;
    
    /**
     * Description  : 维护分值(用于计算工程师绩效)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "维护分值(用于计算工程师绩效)", allowEmptyValue = true, position = 8 )
    private Integer score;
    
    /**
     * Description  : 维护方式名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "维护方式名称", allowEmptyValue = false, position = 9 )
    private String name;
    
    /**
     * Description  : 维护方式简述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "维护方式简述", allowEmptyValue = true, position = 10 )
    private String description;
    
    /**
     * Description  : 维护方式详述id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "维护方式详述id", allowEmptyValue = true, position = 11 )
    private String stepInfoId;
    
    /**
     * Description  : 维护注意事项id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "维护注意事项id", allowEmptyValue = true, position = 12 )
    private String warningInfoId;
    
    /**
     * Description  : 维护数据记录格式JSON
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:16383]
     */
    @ApiModelProperty( value = "维护数据记录格式JSON", allowEmptyValue = true, position = 13 )
    private String dataTypeJson;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "公司id", allowEmptyValue = false, position = 14 )
    private String companyId;
    
}
