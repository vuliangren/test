package com.welearn.entity.po.alink;

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
 * Persistent Object : ryme_alink : device_group
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/1 11:26:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DeviceGroup", description = "DeviceGroup 领域实体")
public class DeviceGroup extends BasePersistant
{
    /**
     * Description  : 组名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "组名称", allowEmptyValue = false, position = 4 )
    private String name;
    
    /**
     * Description  : 组类型: 0-巡检组 1-处理组 2-监察组
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "组类型: 0-巡检组 1-处理组 2-监察组", allowEmptyValue = false, position = 5 )
    private Integer type;
    
    /**
     * Description  : 组描述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:256]
     */
    @NotBlank
    @ApiModelProperty( value = "组描述", allowEmptyValue = false, position = 6 )
    private String description;
    
}
