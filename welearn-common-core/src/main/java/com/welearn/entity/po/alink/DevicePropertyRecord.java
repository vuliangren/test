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
 * Persistent Object : ryme_alink : device_property_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/27 11:51:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DevicePropertyRecord", description = "DevicePropertyRecord 领域实体")
public class DevicePropertyRecord extends BasePersistant
{
    /**
     * Description  : 设备唯一标识符
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备唯一标识符", allowEmptyValue = false, position = 4 )
    private String iotId;
    
    /**
     * Description  : 属性数据JSON格式存储
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "属性数据JSON格式存储", allowEmptyValue = false, position = 5 )
    private String dataJson;
    
    /**
     * Description  : 属性上报时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "属性上报时间", allowEmptyValue = true, position = 6 )
    private Date reportedAt;
    
    /**
     * Description  : 属性记录类型: 0-上报属性记录, 1-运行时间记录
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "属性记录类型: 0-上报属性记录, 1-运行时间记录", allowEmptyValue = true, position = 7 )
    private Integer type;
    
}
