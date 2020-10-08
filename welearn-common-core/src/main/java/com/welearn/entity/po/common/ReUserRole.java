package com.welearn.entity.po.common;

import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Persistent Object : welearn-common : re_user_role
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/1/7 9:26:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReUserRole extends BasePersistant
{
    /**
     * Description  : 用户id
     * Constraint   : [NOT NULL] [Foreign Key] 
     * TableColumn   : [welearn-common:bigint][PRECISION:20]
     */
    @NotNull
    private String userId;
    
    /**
     * Description  : 角色id
     * Constraint   : [NOT NULL] [Foreign Key] 
     * TableColumn   : [welearn-common:bigint][PRECISION:20]
     */
    @NotNull
    private String roleId;
}
