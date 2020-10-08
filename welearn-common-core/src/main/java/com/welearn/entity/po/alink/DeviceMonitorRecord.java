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
 * Persistent Object : ryme_alink : device_monitor_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/10 14:18:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DeviceMonitorRecord", description = "DeviceMonitorRecord 领域实体")
public class DeviceMonitorRecord extends BasePersistant
{
    /**
     * Description  : 监测记录类型 前端定义
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "监测记录类型 前端定义", allowEmptyValue = false, position = 4 )
    private String type;
    
    /**
     * Description  : 设备唯一标识符
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "设备唯一标识符", allowEmptyValue = false, position = 5 )
    private String productKey;
    
    /**
     * Description  : 设备唯一标识符
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备唯一标识符", allowEmptyValue = false, position = 6 )
    private String iotId;
    
    /**
     * Description  : 监测记录数据JSON格式存储
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "监测记录数据JSON格式存储", allowEmptyValue = false, position = 7 )
    private String dataJson;
    
    /**
     * Description  : 监测方名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "监测方名称", allowEmptyValue = false, position = 8 )
    private String monitorName;
    
    /**
     * Description  : 监测结果
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "监测结果", allowEmptyValue = false, position = 9 )
    private String monitorResult;
    
    /**
     * Description  : 监测时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "监测时间", allowEmptyValue = true, position = 10 )
    private Date monitorAt;
    
    /**
     * Description  : 创建人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "创建人id", allowEmptyValue = false, position = 11 )
    private String creatorId;
    
}
