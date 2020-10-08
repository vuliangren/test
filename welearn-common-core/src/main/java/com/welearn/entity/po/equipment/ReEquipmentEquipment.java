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
 * Persistent Object : ryme_equipment : re_equipment_equipment
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/9 19:34:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReEquipmentEquipment", description = "ReEquipmentEquipment 领域实体")
public class ReEquipmentEquipment extends BasePersistant
{
    /**
     * Description  : 从某个设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "从某个设备id", allowEmptyValue = false, position = 4 )
    private String fromEquipmentId;
    
    /**
     * Description  : 到某个设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "到某个设备id", allowEmptyValue = false, position = 5 )
    private String toEquipmentId;
    
    /**
     * Description  : 关系说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "关系说明", allowEmptyValue = true, position = 6 )
    private String relationName;
    
    /**
     * Description  : 关系编码
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "关系编码", allowEmptyValue = true, position = 7 )
    private String relationCode;
    
}
