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
 * Persistent Object : ryme_alink : re_rfid_tag_device
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/16 16:51:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReRfidTagDevice", description = "ReRfidTagDevice 领域实体")
public class ReRfidTagDevice extends BasePersistant
{
    /**
     * Description  : 设备id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备id", allowEmptyValue = false, position = 4 )
    private String iotId;
    
    /**
     * Description  : 标签id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "标签id", allowEmptyValue = false, position = 5 )
    private String tagId;
    
}
