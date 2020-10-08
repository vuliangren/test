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
 * Persistent Object : ryme_common : department
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/6 9:49:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Department", description = "Department 领域实体")
public class Department extends BasePersistant
{
    /**
     * Description  : 父节点主键
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "父节点主键", allowEmptyValue = true, position = 1 )
    private String parentId;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 5 )
    private String name;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "描述", allowEmptyValue = true, position = 6 )
    private String description;
    
    /**
     * Description  : 创建者id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "创建者id", allowEmptyValue = false, position = 7 )
    private String creatorId;
    
    /**
     * Description  : 管理员id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "管理员id", allowEmptyValue = true, position = 8 )
    private String adminId;
    
    /**
     * Description  : 所在楼层id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "所在楼层id", allowEmptyValue = true, position = 9 )
    private String floorId;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "公司id", allowEmptyValue = false, position = 10 )
    private String companyId;
    
    /**
     * Description  : 标签: 可有多个标签,根据标签来确认功能
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "标签: 可有多个标签,根据标签来确认功能", allowEmptyValue = true, position = 11 )
    private String tags;
    
    /**
     * Description  : 状态: 0-正常
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-正常", allowEmptyValue = true, position = 12 )
    private Integer state;
    
    /**
     * Description  : 所在建筑id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "所在建筑id", allowEmptyValue = true, position = 13 )
    private String buildingId;
    
    /**
     * Description  : 所在区域id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "所在区域id", allowEmptyValue = true, position = 14 )
    private String areaId;
    
}
