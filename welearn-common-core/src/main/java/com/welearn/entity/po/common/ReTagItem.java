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
 * Persistent Object : ryme_common : re_tag_item
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/6 9:49:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReTagItem", description = "ReTagItem 领域实体")
public class ReTagItem extends BasePersistant
{
    /**
     * Description  : 标签id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "标签id", allowEmptyValue = false, position = 4 )
    private String tagId;
    
    /**
     * Description  : 数据项id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "数据项id", allowEmptyValue = false, position = 5 )
    private String itemId;
    
}
