package com.welearn.entity.po.equipment;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_equipment : equipment_type_description
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/19 14:01:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentTypeDescription extends BasePersistant
{
    /**
     * Description  : 描述编号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    private String dSort;
    
    /**
     * Description  : 用途编号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    private String uSort;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:4096]
     */
    @NotBlank
    private String description;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:2048]
     */
    private String usage;
    
}
