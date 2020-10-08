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
 * Persistent Object : ryme_common : re_route_permission
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/5 10:02:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReRoutePermission", description = "ReRoutePermission 领域实体")
public class ReRoutePermission extends BasePersistant
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
     * Description  : 权限code
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "权限code", allowEmptyValue = false, position = 6 )
    private String permission;
    
}
