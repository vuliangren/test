package com.welearn.entity.po.apply;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_apply : approval_process_point
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/18 17:09:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApprovalProcessPoint extends BasePersistant
{
    /**
     * Description  : 审批流程id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String processId;

    /**
     * Description  : 公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String companyId;
    
    /**
     * Description  : 部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @NotBlank
    private String departmentId;
    
    /**
     * Description  : 部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String departmentName;
    
    /**
     * Description  : 职位id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String positionId;
    
    /**
     * Description  : 职位姓名
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String positionName;
    
    /**
     * Description  : 审批人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String approverId;
    
    /**
     * Description  : 审批人姓名
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String approverName;
    
    /**
     * Description  : 类型: 0-审批人 1-职位任意人 2-职位所有人
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    private Integer type;
    
    /**
     * Description  : 排序
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    private Integer sort;
    
    /**
     * Description  : 审批类型 0-审批 1-提意 2-抄送
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    private Integer approvalType;
    
}
