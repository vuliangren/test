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
 * Persistent Object : ryme_alink : device_event
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/1 11:26:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DeviceEvent", description = "DeviceEvent 领域实体")
public class DeviceEvent extends BasePersistant
{
    /**
     * Description  : 事件标识
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "事件标识", allowEmptyValue = false, position = 4 )
    private String identifier;
    
    /**
     * Description  : 事件级别: 0-通知 1-警告 2-报警
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "事件级别: 0-通知 1-警告 2-报警", allowEmptyValue = false, position = 5 )
    private Integer type;
    
    /**
     * Description  : 设备唯一标识符
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "设备唯一标识符", allowEmptyValue = false, position = 6 )
    private String iotId;
    
    /**
     * Description  : 事件名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "事件名称", allowEmptyValue = false, position = 7 )
    private String namePrefix;
    
    /**
     * Description  : 异常信息
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "异常信息", allowEmptyValue = false, position = 8 )
    private String name;
    
    /**
     * Description  : 异常部位
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "异常部位", allowEmptyValue = false, position = 9 )
    private String nameSuffix;
    
    /**
     * Description  : 产品Key
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "产品Key", allowEmptyValue = false, position = 10 )
    private String productKey;
    
    /**
     * Description  : 设备编号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "设备编号", allowEmptyValue = false, position = 11 )
    private String deviceName;
    
    /**
     * Description  : 上报时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "上报时间", allowEmptyValue = true, position = 12 )
    private Date reportAt;
    
    /**
     * Description  : 时间详情JSON, {key(与物模型事件的配置对应): value}}
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "时间详情JSON, {key(与物模型事件的配置对应): value}}", allowEmptyValue = false, position = 13 )
    private String detailJson;
    
    /**
     * Description  : 是否需要处理：0-不需要，1-需要
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否需要处理：0-不需要，1-需要", allowEmptyValue = true, position = 14 )
    private Boolean shouldHandle;
    
    /**
     * Description  : 处理状态: 0-待领工 1-待处理 2-已处理
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "处理状态: 0-待领工 1-待处理 2-已处理", allowEmptyValue = true, position = 15 )
    private Integer handleStatus;
    
    /**
     * Description  : 处理人员id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "处理人员id", allowEmptyValue = true, position = 16 )
    private String handleUserId;
    
    /**
     * Description  : 处理报告JSON格式存储
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @ApiModelProperty( value = "处理报告JSON格式存储", allowEmptyValue = true, position = 17 )
    private String handleReportJson;
    
}
