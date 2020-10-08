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
 * Persistent Object : ryme_alink : device
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/27 11:51:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Device", description = "Device 领域实体")
public class Device extends BasePersistant
{
    /**
     * Description  : 所属公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "所属公司id", allowEmptyValue = true, position = 4 )
    private String companyId;
    
    /**
     * Description  : 所属公司名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "所属公司名称", allowEmptyValue = true, position = 5 )
    private String companyName;
    
    /**
     * Description  : 所在位置id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "所在位置id", allowEmptyValue = true, position = 6 )
    private String addressId;
    
    /**
     * Description  : 隶属的产品Key
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "隶属的产品Key", allowEmptyValue = false, position = 7 )
    private String productKey;
    
    /**
     * Description  : 隶属的产品名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "隶属的产品名称", allowEmptyValue = false, position = 8 )
    private String productName;
    
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 9 )
    private String deviceName;
    
    /**
     * Description  : 备注名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注名称", allowEmptyValue = true, position = 10 )
    private String nickname;
    
    /**
     * Description  : 密钥
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "密钥", allowEmptyValue = false, position = 11 )
    private String deviceSecret;
    
    /**
     * Description  : IoT平台为该设备颁发的ID，作为该设备的唯一标识符
     * Constraint   : [NOT NULL] [Unique] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "IoT平台为该设备颁发的ID，作为该设备的唯一标识符", allowEmptyValue = false, position = 12 )
    private String iotId;
    
    /**
     * Description  : ​​激活时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:timestamp]
     */
    @ApiModelProperty( value = "​​激活时间", allowEmptyValue = true, position = 13 )
    private Date activedAt;
    
    /**
     * Description  : 最近一次上线的时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:timestamp]
     */
    @ApiModelProperty( value = "最近一次上线的时间", allowEmptyValue = true, position = 14 )
    private Date lastOnlineAt;
    
    /**
     * Description  : 状态: 0-OFFLINE设备离线 1-ONLINE设备在线 2-UNACTIVE设备未激活 3-DISABLE设备已禁用
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "状态: 0-OFFLINE设备离线 1-ONLINE设备在线 2-UNACTIVE设备未激活 3-DISABLE设备已禁用", allowEmptyValue = false, position = 15 )
    private Integer status;
    
    /**
     * Description  : 最近状态改变的时间(处理状态消息错序问题)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:timestamp]
     */
    @ApiModelProperty( value = "最近状态改变的时间(处理状态消息错序问题)", allowEmptyValue = true, position = 16 )
    private Date lastStatusChangedAt;
    
    /**
     * Description  : 固件版本号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "固件版本号", allowEmptyValue = true, position = 17 )
    private String firmwareVersion;
    
    /**
     * Description  : IP地址
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "IP地址", allowEmptyValue = true, position = 18 )
    private String ipAddress;
    
    /**
     * Description  : 节点类型: 0-设备 1-网关
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "节点类型: 0-设备 1-网关", allowEmptyValue = true, position = 19 )
    private Integer nodeType;
    
    /**
     * Description  : 所在地区
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "所在地区", allowEmptyValue = false, position = 20 )
    private String region;
    
    /**
     * Description  : 属性快照JSON格式存储, {key(与物模型的属性名对应): value}
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "属性快照JSON格式存储, {key(与物模型的属性名对应): value}", allowEmptyValue = false, position = 21 )
    private String propertySnapshotJson;
    
    /**
     * Description  : 配置文件JSON格式存储, {key: value ...}
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "配置文件JSON格式存储, {key: value ...}", allowEmptyValue = false, position = 22 )
    private String configurationJson;
    
    /**
     * Description  : 文档相关文件JSON格式存储, [{name, description, files:[{...}]}]
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @ApiModelProperty( value = "文档相关文件JSON格式存储, [{name, description, files:[{...}]}]", allowEmptyValue = true, position = 23 )
    private String documentFileJson;
    
    /**
     * Description  : 数据显示用 JSON格式配置文件
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @ApiModelProperty( value = "数据显示用 JSON格式配置文件", allowEmptyValue = true, position = 24 )
    private String showConfigJson;
    
}
