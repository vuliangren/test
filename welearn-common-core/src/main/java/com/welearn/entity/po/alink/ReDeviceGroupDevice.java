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
 * Persistent Object : ryme_alink : re_device_group_device
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/1 11:26:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReDeviceGroupDevice", description = "ReDeviceGroupDevice 领域实体")
public class ReDeviceGroupDevice extends BasePersistant
{
    /**
     * Description  : 组id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "组id", allowEmptyValue = false, position = 4 )
    private String groupId;
    
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 5 )
    private String iotId;
    
}
