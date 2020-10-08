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
 * Persistent Object : ryme_equipment : re_task_assignment_method
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReTaskAssignmentMethod extends BasePersistant
{
    /**
     * Description  : 维护任务分配id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String taskAssignmentId;
    
    /**
     * Description  : 维护方式id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String methodId;
    
    /**
     * Description  : 是否已经处理: 0-未处理 1-已处理
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @NotNull
    private Boolean isProcessed;
    
}
