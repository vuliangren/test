package com.welearn.entity.po.equipment;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_equipment : engineer
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/21 10:20:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Engineer", description = "Engineer 领域实体")
public class Engineer extends BasePersistant
{
    /**
     * Description  : 人员id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "人员id", allowEmptyValue = true, position = 4 )
    private String userId;
    
    /**
     * Description  : 人员名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "人员名称", allowEmptyValue = false, position = 5 )
    private String userName;
    
    /**
     * Description  : 人员所属公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "人员所属公司id", allowEmptyValue = true, position = 6 )
    private String userCompanyId;
    
    /**
     * Description  : 人员所属公司名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "人员所属公司名称", allowEmptyValue = true, position = 7 )
    private String userCompanyName;
    
    /**
     * Description  : 人员所属部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "人员所属部门id", allowEmptyValue = true, position = 8 )
    private String userDepartmentId;
    
    /**
     * Description  : 人员所属部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "人员所属部门名称", allowEmptyValue = true, position = 9 )
    private String userDepartmentName;
    
    /**
     * Description  : 服务于公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "服务于公司id", allowEmptyValue = false, position = 10 )
    private String serveCompanyId;
    
    /**
     * Description  : 服务于公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "服务于公司名称", allowEmptyValue = false, position = 11 )
    private String serveCompanyName;
    
    /**
     * Description  : 工程师电话
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:16]
     */
    @ApiModelProperty( value = "工程师电话", allowEmptyValue = true, position = 12 )
    private String telephone;
    
    /**
     * Description  : 工程师邮箱
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "工程师邮箱", allowEmptyValue = true, position = 13 )
    private String email;
    
    /**
     * Description  : 工程师QQ
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:16]
     */
    @ApiModelProperty( value = "工程师QQ", allowEmptyValue = true, position = 14 )
    private String qq;
    
    /**
     * Description  : 工程师微信
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "工程师微信", allowEmptyValue = true, position = 15 )
    private String wechat;
    
    /**
     * Description  : 工程师类型: 0-内部工程师 1-外部工程师 2-临时工程师
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "工程师类型: 0-内部工程师 1-外部工程师 2-临时工程师", allowEmptyValue = false, position = 16 )
    private Integer type;
    
    /**
     * Description  : 经验积分
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_equipment:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "经验积分", allowEmptyValue = true, position = 17 )
    private Integer experience;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_equipment:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 18 )
    private String remark;
    
}
