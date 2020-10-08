package com.welearn.entity.po.finance;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_finance : stock_task
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/19 15:18:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockTask extends BasePersistant
{
    /**
     * Description  : 库存id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String stockId;
    
    /**
     * Description  : 任务类型: 0-入库 1-出库 2-消耗
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer type;
    
    /**
     * Description  : 任务数量(内含数量)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer taskCount;
    
    /**
     * Description  : 完成数量(内含数量)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     */
    @NotNull
    private Integer finishCount;
    
    /**
     * Description  : 任务类型说明
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    @NotBlank
    private String typeDescription;
    
    /**
     * Description  : 任务状态: 0-待处理 1-处理中 2-已完成 3-已取消
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer status;
    
    /**
     * Description  : 关联任务id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String reTaskId;
    
    /**
     * Description  : 任务派发人id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String commanderId;
    
    /**
     * Description  : 任务派发人名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String commanderName;
    
    /**
     * Description  : 任务派发人部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    @NotBlank
    private String commanderDepartmentId;
    
    /**
     * Description  : 任务派发人部门名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    @NotBlank
    private String commanderDepartmentName;
    
    /**
     * Description  : 任务派发人备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:1024]
     */
    private String commanderRemark;
    
    /**
     * Description  : 任务执行人id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String operatorId;
    
    /**
     * Description  : 任务执行人名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:256]
     */
    private String operatorName;
    
    /**
     * Description  : 任务执行人部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:128]
     */
    private String operatorDepartmentId;
    
    /**
     * Description  : 任务执行人部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:512]
     */
    private String operatorDepartmentName;
    
    /**
     * Description  : 任务执行人备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:1024]
     */
    private String operatorRemark;
    
    /**
     * Description  : 任务取消原因
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_finance:varchar][SIZE:1024]
     */
    private String cancelReason;
    
}
