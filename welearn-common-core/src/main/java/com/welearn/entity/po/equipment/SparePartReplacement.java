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
 * Persistent Object : ryme_equipment : spare_part_replacement
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/25 17:49:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SparePartReplacement extends BasePersistant
{
    /**
     * Description  : 类型id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String typeId;
    
    /**
     * Description  : 维修派工id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String repairDispatchId;
    
    /**
     * Description  : 工程师id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String maintenanceEngineerId;
    
    /**
     * Description  : 工程师名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @NotBlank
    private String maintenanceEngineerName;
    
    /**
     * Description  : 状态:0-待报废 1-待维修 2-已报废 3-已回收
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    private Integer status;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:1024]
     */
    private String remark;
    
}
