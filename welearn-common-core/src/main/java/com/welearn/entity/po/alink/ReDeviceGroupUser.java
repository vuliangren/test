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
 * Persistent Object : ryme_alink : re_device_group_user
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/1 16:35:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReDeviceGroupUser", description = "ReDeviceGroupUser 领域实体")
public class ReDeviceGroupUser extends BasePersistant
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
     * Description  : 用户id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "用户id", allowEmptyValue = false, position = 5 )
    private String userId;
    
    /**
     * Description  : 用户名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "用户名称", allowEmptyValue = false, position = 6 )
    private String userName;
    
}
