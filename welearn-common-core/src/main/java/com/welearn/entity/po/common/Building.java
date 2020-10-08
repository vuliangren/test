package com.welearn.entity.po.common;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_common : building
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/13 10:55:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Building extends BasePersistant
{
    /**
     * Description  : 创建者id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    private String creatorId;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    private String companyId;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    private String name;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:512]
     */
    private String description;
    
}
