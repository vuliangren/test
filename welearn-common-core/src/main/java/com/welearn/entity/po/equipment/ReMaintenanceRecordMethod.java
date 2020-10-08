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
 * Persistent Object : ryme_equipment : re_maintenance_record_method
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReMaintenanceRecordMethod extends BasePersistant
{
    /**
     * Description  : 维护设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String equipmentId;
    
    /**
     * Description  : 维护记录id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String recordId;
    
    /**
     * Description  : 维护方式id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String methodId;
    
    /**
     * Description  : 维护数据JSON
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:text][SIZE:65535]
     */
    private String dataJson;
    
}
