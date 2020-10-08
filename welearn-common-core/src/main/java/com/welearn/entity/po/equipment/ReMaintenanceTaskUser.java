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
 * Persistent Object : ryme_equipment : re_maintenance_task_user
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReMaintenanceTaskUser extends BasePersistant
{
    /**
     * Description  : 维护任务id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String taskId;
    
    /**
     * Description  : 维护人员id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String engineerId;
    
    /**
     * Description  : 维护人员名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @NotBlank
    private String engineerName;
    
}
