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
 * Persistent Object : ryme_common : tag
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/6 9:49:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Tag", description = "Tag 领域实体")
public class Tag extends BasePersistant
{
    /**
     * Description  : 所属公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "所属公司id", allowEmptyValue = false, position = 4 )
    private String companyId;
    
    /**
     * Description  : 数据类型 详情见 PersistantConst
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "数据类型 详情见 PersistantConst", allowEmptyValue = false, position = 5 )
    private String itemType;
    
    /**
     * Description  : 标签名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "标签名称", allowEmptyValue = false, position = 6 )
    private String name;
    
    /**
     * Description  : 标签说明
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:256]
     */
    @NotBlank
    @ApiModelProperty( value = "标签说明", allowEmptyValue = false, position = 7 )
    private String description;
    
}
