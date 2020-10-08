package com.welearn.entity.po.common;

import com.welearn.dictionary.common.RoleTypeConst;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Persistent Object : welearn-common : role
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/1/7 9:26:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BasePersistant
{
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-common:varchar][SIZE:128]
     */
    @NotBlank
    private String name;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [welearn-common:varchar][SIZE:512]
     */
    private String description;
    
    /**
     * Description  : 创建者id
     * Constraint   : [NOT NULL] [Foreign Key] 
     * TableColumn   : [welearn-common:bigint][PRECISION:20]
     */
    @NotBlank
    private String creatorId;

    /**
     * Description  : 编码
     * Constraint   : [NOT NULL]
     * TableColumn   : [welearn-common:varchar][SIZE:256]
     */
    @NotBlank
    private String code;

    /**
     * @see RoleTypeConst
     * Description  : 类型
     * Constraint   : [NOT NULL]
     * TableColumn   : [welearn-common:int][PRECISION:10]
     */
    @Range(min = 0, max = 2)
    private Integer type;
}
