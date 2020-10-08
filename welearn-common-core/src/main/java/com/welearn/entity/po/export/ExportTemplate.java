package com.welearn.entity.po.export;

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
 * Persistent Object : ryme_export : export_template
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/11 11:39:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ExportTemplate", description = "ExportTemplate 领域实体")
public class ExportTemplate extends BasePersistant
{
    /**
     * Description  : 是否默认模板：0-非默认，1-默认
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:tinyint][PRECISION:3]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "是否默认模板：0-非默认，1-默认", allowEmptyValue = true, position = 4 )
    private Boolean isDefault;
    
    /**
     * Description  : 是否系统模板：0-用户模板，1-系统模板
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:tinyint][PRECISION:3]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "是否系统模板：0-用户模板，1-系统模板", allowEmptyValue = true, position = 5 )
    private Boolean isSystem;
    
    /**
     * Description  : 模板名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "模板名称", allowEmptyValue = true, position = 6 )
    private String name;
    
    /**
     * Description  : 模板类型: USER_DEFINED-用户定义 ...
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:varchar][SIZE:64]
     * DefaultValue : USER_DEFINED
     */
    @ApiModelProperty( value = "模板类型: USER_DEFINED-用户定义 ...", allowEmptyValue = true, position = 7 )
    private String type;
    
    /**
     * Description  : 模板文件内容
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "模板文件内容", allowEmptyValue = false, position = 8 )
    private String file;
    
    /**
     * Description  : 模板参数格式 JSON格式存储
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:text][SIZE:16383]
     */
    @ApiModelProperty( value = "模板参数格式 JSON格式存储", allowEmptyValue = true, position = 9 )
    private String argsFormatJson;
    
    /**
     * Description  : 文件后缀
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_export:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "文件后缀", allowEmptyValue = false, position = 10 )
    private String fileSuffix;
    
    /**
     * Description  : 所属公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "所属公司id", allowEmptyValue = true, position = 11 )
    private String companyId;
    
    /**
     * Description  : 所属公司名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "所属公司名称", allowEmptyValue = true, position = 12 )
    private String companyName;
    
    /**
     * Description  : 所属部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "所属部门id", allowEmptyValue = true, position = 13 )
    private String departmentId;
    
    /**
     * Description  : 所属部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "所属部门名称", allowEmptyValue = true, position = 14 )
    private String departmentName;
    
    /**
     * Description  : 创建人id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "创建人id", allowEmptyValue = true, position = 15 )
    private String creatorId;
    
    /**
     * Description  : 创建人名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "创建人名称", allowEmptyValue = true, position = 16 )
    private String creatorName;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_export:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 17 )
    private String remark;
    
}
