package com.welearn.entity.po.apply;

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
 * Persistent Object : ryme_apply : approval_process
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/15 10:05:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ApprovalProcess", description = "ApprovalProcess 领域实体")
public class ApprovalProcess extends BasePersistant
{
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 4 )
    private String name;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_apply:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "描述", allowEmptyValue = true, position = 5 )
    private String description;
    
    /**
     * Description  : 编码
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "编码", allowEmptyValue = false, position = 6 )
    private String code;
    
    /**
     * Description  : 创建者公司id(一般为平台公司id)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "创建者公司id(一般为平台公司id)", allowEmptyValue = false, position = 7 )
    private String creatorCompanyId;
    
    /**
     * Description  : 访问者公司类型(编码和此类型映射到唯一的一个流程上): 0-医院 1-供应商 2-平台
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "访问者公司类型(编码和此类型映射到唯一的一个流程上): 0-医院 1-供应商 2-平台", allowEmptyValue = true, position = 8 )
    private Integer visitorCompanyType;
    
    /**
     * Description  : 是否可用(用于一些情况下系统控制的审批流程, 不允许用户添加)：0-不用，1-使用
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_apply:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否可用(用于一些情况下系统控制的审批流程, 不允许用户添加)：0-不用，1-使用", allowEmptyValue = true, position = 9 )
    private Boolean isUseDefaultProcessPoint;
    
}
