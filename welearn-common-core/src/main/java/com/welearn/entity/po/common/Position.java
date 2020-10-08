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
 * Persistent Object : ryme_common : position
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/17 14:50:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Position", description = "Position 领域实体")
public class Position extends BasePersistant
{
    /**
     * Description  : 类型: 0-系统定义的特殊职位 1-用户定义的公司职位 2-用户定义的部门职位
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "类型: 0-系统定义的特殊职位 1-用户定义的公司职位 2-用户定义的部门职位", allowEmptyValue = false, position = 4 )
    private Integer type;
    
    /**
     * Description  : 访问者公司类型(主要对系统定义的特殊职位进行区分): 0-医院 1-供应商 2-平台
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "访问者公司类型(主要对系统定义的特殊职位进行区分): 0-医院 1-供应商 2-平台", allowEmptyValue = true, position = 5 )
    private Integer visitorCompanyType;
    
    /**
     * Description  : 公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "公司id", allowEmptyValue = true, position = 6 )
    private String companyId;
    
    /**
     * Description  : 部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "部门id", allowEmptyValue = true, position = 7 )
    private String departmentId;
    
    /**
     * Description  : 职位名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "职位名称", allowEmptyValue = false, position = 8 )
    private String name;
    
    /**
     * Description  : 职位编码, 仅系统创建的职位带此信息
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "职位编码, 仅系统创建的职位带此信息", allowEmptyValue = true, position = 9 )
    private String code;
    
    /**
     * Description  : 职位描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:2048]
     */
    @ApiModelProperty( value = "职位描述", allowEmptyValue = true, position = 10 )
    private String description;
    
    /**
     * Description  : 颜色
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:16]
     */
    @ApiModelProperty( value = "颜色", allowEmptyValue = true, position = 11 )
    private String color;
    
    /**
     * Description  : 公司该职位人员数量最小限制: 0-不限制
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "公司该职位人员数量最小限制: 0-不限制", allowEmptyValue = true, position = 12 )
    private Integer minCount;
    
    /**
     * Description  : 公司该职位人员数量最大限制: 0-不限制
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "公司该职位人员数量最大限制: 0-不限制", allowEmptyValue = true, position = 13 )
    private Integer maxCount;
    
}
