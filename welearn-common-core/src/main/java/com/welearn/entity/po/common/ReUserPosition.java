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
 * Persistent Object : ryme_common : re_user_position
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/29 17:02:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReUserPosition extends BasePersistant
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
     * Description  : 部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    private String departmentId;
    
    /**
     * Description  : 职务id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    private String positionId;
    
    /**
     * Description  : 用户id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @NotBlank
    private String userId;
    
    /**
     * Description  : 分配时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:timestamp]
     */
    private Date allotAt;
    
    /**
     * Description  : 撤回时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:timestamp]
     */
    private Date takeBackAt;
    
}
