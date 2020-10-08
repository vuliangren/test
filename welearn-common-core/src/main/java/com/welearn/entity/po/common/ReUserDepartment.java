package com.welearn.entity.po.common;

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
 * Persistent Object : ryme_common : re_user_department
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/7 16:06:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReUserDepartment", description = "ReUserDepartment 领域实体")
public class ReUserDepartment extends BasePersistant
{
    /**
     * Description  : 创建者id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "创建者id", allowEmptyValue = false, position = 4 )
    private String creatorId;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "公司id", allowEmptyValue = false, position = 5 )
    private String companyId;
    
    /**
     * Description  : 部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "部门id", allowEmptyValue = false, position = 6 )
    private String departmentId;
    
    /**
     * Description  : 用户id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "用户id", allowEmptyValue = false, position = 7 )
    private String userId;
    
    /**
     * Description  : 入职备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "入职备注", allowEmptyValue = true, position = 8 )
    private String entryRemark;
    
    /**
     * Description  : 离职备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "离职备注", allowEmptyValue = true, position = 9 )
    private String leaveRemark;
    
}
