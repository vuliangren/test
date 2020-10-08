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
 * Persistent Object : ryme_common : room
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/16 18:24:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Room", description = "Room 领域实体")
public class Room extends BasePersistant
{
    /**
     * Description  : 创建者id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "创建者id", allowEmptyValue = false, position = 4 )
    private String creatorId;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "公司id", allowEmptyValue = false, position = 5 )
    private String companyId;
    
    /**
     * Description  : 建筑id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "建筑id", allowEmptyValue = false, position = 6 )
    private String buildingId;
    
    /**
     * Description  : 楼层id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "楼层id", allowEmptyValue = false, position = 7 )
    private String floorId;
    
    /**
     * Description  : 名称(门牌号)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "名称(门牌号)", allowEmptyValue = false, position = 8 )
    private String name;
    
    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "描述", allowEmptyValue = true, position = 9 )
    private String description;
    
}
