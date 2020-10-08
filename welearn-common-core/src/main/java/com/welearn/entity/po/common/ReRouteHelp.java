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
 * Persistent Object : ryme_common : re_route_help
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/4 16:18:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReRouteHelp", description = "ReRouteHelp 领域实体")
public class ReRouteHelp extends BasePersistant
{
    /**
     * Description  : 客户端类型: 1-web
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "客户端类型: 1-web", allowEmptyValue = true, position = 4 )
    private Integer clientType;
    
    /**
     * Description  : 前端路由地址
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:256]
     */
    @NotBlank
    @ApiModelProperty( value = "前端路由地址", allowEmptyValue = false, position = 5 )
    private String path;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 6 )
    private String name;
    
    /**
     * Description  : 描述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:512]
     */
    @NotBlank
    @ApiModelProperty( value = "描述", allowEmptyValue = false, position = 7 )
    private String description;
    
    /**
     * Description  : 帮助信息富文本id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "帮助信息富文本id", allowEmptyValue = false, position = 8 )
    private String helpInfoId;
    
}
