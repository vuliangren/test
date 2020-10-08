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
 * Persistent Object : ryme_equipment : spare_part_usage
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/25 17:49:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SparePartUsage extends BasePersistant
{
    /**
     * Description  : 备件类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String spareTypeId;
    
    /**
     * Description  : 用途类型: 0-通用 1-类型适用 2-产品适用 3-设备专用
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer usageType;
    
    /**
     * Description  : 用途关联的id(用途类型: 1-设备类型id, 2-设备产品id, 3-设备id)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    private String usageRefId;
    
    /**
     * Description  : 用途描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:512]
     */
    private String usageDescription;
    
}
