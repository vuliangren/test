package com.welearn.entity.po.common;

import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Persistent Object : welearn-common : re_role_permission
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/1/7 9:25:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReRolePermission extends BasePersistant
{
    /**
     * Description  : 角色id
     * Constraint   : [NOT NULL] [Foreign Key] 
     * TableColumn   : [welearn-common:bigint][PRECISION:20]
     */
    @NotNull
    private String roleId;
    
    /**
     * Description  : 权限id
     * Constraint   : [NOT NULL] [Foreign Key] 
     * TableColumn   : [welearn-common:bigint][PRECISION:20]
     */
    @NotNull
    private String permissionId;
    
}
