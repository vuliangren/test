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
 * Persistent Object : ryme_equipment : equipment_permission
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 16:15:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentPermission extends BasePersistant
{
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String equipmentId;
    
    /**
     * Description  : 类型: 0-企业 1-部门 2-员工
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    private Integer type;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @NotBlank
    private String companyId;
    
    /**
     * Description  : 公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    @NotBlank
    private String companyName;
    
    /**
     * Description  : 部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    private String departmentId;
    
    /**
     * Description  : 部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    private String departmentName;
    
    /**
     * Description  : 员工id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    private String employeeId;
    
    /**
     * Description  : 员工姓名
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:256]
     */
    private String employeeName;
    
    /**
     * Description  : 所拥有的权力编号 0-...
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    private Integer permission;
    
    /**
     * Description  : 得权时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @NotNull
    private Date obtainAt;
    
    /**
     * Description  : 得权描述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:1024]
     */
    @NotBlank
    private String obtainReason;
    
    /**
     * Description  : 失权时间 失权后变为不可用
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:timestamp]
     */
    private Date loseAt;
    
    /**
     * Description  : 失权描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:1024]
     */
    private String loseReason;
    
}
