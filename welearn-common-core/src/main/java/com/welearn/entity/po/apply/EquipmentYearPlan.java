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
 * Persistent Object : ryme_apply : equipment_year_plan
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/30 10:33:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentYearPlan extends BasePersistant
{
    /**
     * Description  : 年度
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     */
    @NotNull
    private Integer year;
    
    /**
     * Description  : 公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    private String companyId;
    
    /**
     * Description  : 科室申请开始时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:timestamp]
     * DefaultValue : 0000-00-00 00:00:00
     */
    @NotNull
    private Date departmentApplyStart;
    
    /**
     * Description  : 科室申请结束时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:timestamp]
     * DefaultValue : 0000-00-00 00:00:00
     */
    @NotNull
    private Date departmentApplyEnd;
    
    /**
     * Description  : 计划状态 0-科室申请阶段 1-委员会评审阶段 2-院长审批阶段 3-采购执行阶段
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     * DefaultValue : 0
     */
    @NotNull
    private Integer status;
    
}
