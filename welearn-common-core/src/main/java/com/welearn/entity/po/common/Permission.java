package com.welearn.entity.po.common;

import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * Persistent Object : welearn-common : permission
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/1/7 9:25:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BasePersistant
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
     * Description  : 类型
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-common:int][PRECISION:10]
     */
    @Range(min = 0, max = 1)
    private Integer type;
    
    /**
     * Description  : 编码
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-common:varchar][SIZE:256]
     */
    @NotBlank
    private String code;

    @NotBlank
    private String service;
    
}
