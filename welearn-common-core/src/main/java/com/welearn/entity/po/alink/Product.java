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
 * Persistent Object : ryme_alink : product
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:38:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Product", description = "Product 领域实体")
public class Product extends BasePersistant
{
    /**
     * Description  : 隶属的产品Key
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "隶属的产品Key", allowEmptyValue = false, position = 4 )
    private String productKey;
    
    /**
     * Description  : 数据类型: 0-串口数据格式 1-AlinkJSON
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "数据类型: 0-串口数据格式 1-AlinkJSON", allowEmptyValue = true, position = 5 )
    private Integer dataFormat;
    
    /**
     * Description  : 产品名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "产品名称", allowEmptyValue = false, position = 6 )
    private String productName;
    
    /**
     * Description  : 产品秘钥
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "产品秘钥", allowEmptyValue = false, position = 7 )
    private String prpoductSecret;
    
    /**
     * Description  : 产品描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "产品描述", allowEmptyValue = true, position = 8 )
    private String description;
    
    /**
     * Description  : 设备数量
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "设备数量", allowEmptyValue = true, position = 9 )
    private Integer deviceCount;
    
    /**
     * Description  : 高级版产品的节点类型 0-设备 1-网关
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "高级版产品的节点类型 0-设备 1-网关", allowEmptyValue = true, position = 10 )
    private Integer nodeType;
    
    /**
     * Description  : 高级版产品的设备类型
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "高级版产品的设备类型", allowEmptyValue = true, position = 11 )
    private String categoryName;
    
    /**
     * Description  : 高级版产品的英文标识
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "高级版产品的英文标识", allowEmptyValue = true, position = 12 )
    private String categoryKey;
    
    /**
     * Description  : 平台类型: 0-物联网平台基础版 1-物联网平台高级版
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "平台类型: 0-物联网平台基础版 1-物联网平台高级版", allowEmptyValue = true, position = 13 )
    private Integer platformType;
    
    /**
     * Description  : 是否使用ID²认证: 0-否 1-是
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否使用ID²认证: 0-否 1-是", allowEmptyValue = true, position = 14 )
    private Boolean id2;
    
    /**
     * Description  : 产品状态: 0-开发中 1-已发布
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "产品状态: 0-开发中 1-已发布", allowEmptyValue = true, position = 15 )
    private Integer productStatus;
    
    /**
     * Description  : 联网方式: 0-WIFI 1-Cellular (2G/3G/4G)蜂窝网 2-Ethernet 以太网 3-其他
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     * DefaultValue : 3
     */
    @ApiModelProperty( value = "联网方式: 0-WIFI 1-Cellular (2G/3G/4G)蜂窝网 2-Ethernet 以太网 3-其他", allowEmptyValue = true, position = 16 )
    private Integer netType;
    
    /**
     * Description  : 物模型 Thing Specification Language JSON格式存储
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "物模型 Thing Specification Language JSON格式存储", allowEmptyValue = false, position = 17 )
    private String tslJson;
    
    /**
     * Description  : 数据显示用 JSON格式配置文件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "数据显示用 JSON格式配置文件", allowEmptyValue = false, position = 18 )
    private String showConfigJson;
    
    /**
     * Description  : 数据分析用 JSON格式配置文件
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "数据分析用 JSON格式配置文件", allowEmptyValue = false, position = 19 )
    private String statisitcsConfigJson;
    
}
