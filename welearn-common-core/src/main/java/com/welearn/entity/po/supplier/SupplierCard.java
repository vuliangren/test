package com.welearn.entity.po.supplier;

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
 * Persistent Object : ryme_supplier : supplier_card
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/5 10:20:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SupplierCard", description = "SupplierCard 领域实体")
public class SupplierCard extends BasePersistant
{
    /**
     * Description  : 中文名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "中文名", allowEmptyValue = false, position = 4 )
    private String nameCn;
    
    /**
     * Description  : 英文名
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "英文名", allowEmptyValue = true, position = 5 )
    private String nameEn;
    
    /**
     * Description  : 图标
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:text][SIZE:16383]
     */
    @ApiModelProperty( value = "图标", allowEmptyValue = true, position = 6 )
    private String logo;
    
    /**
     * Description  : 网站url
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:text][SIZE:16383]
     */
    @ApiModelProperty( value = "网站url", allowEmptyValue = true, position = 7 )
    private String website;
    
    /**
     * Description  : 国家编号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:varchar][SIZE:4]
     * DefaultValue : CN
     */
    @ApiModelProperty( value = "国家编号", allowEmptyValue = true, position = 8 )
    private String country;
    
    /**
     * Description  : 地址索引一级(省)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引一级(省)", allowEmptyValue = true, position = 9 )
    private String cityIndex1;
    
    /**
     * Description  : 地址索引二级(市)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引二级(市)", allowEmptyValue = true, position = 10 )
    private String cityIndex2;
    
    /**
     * Description  : 地址索引三级(区)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引三级(区)", allowEmptyValue = true, position = 11 )
    private String cityIndex3;
    
    /**
     * Description  : 地址索引四级(街)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引四级(街)", allowEmptyValue = true, position = 12 )
    private String cityIndex4;
    
    /**
     * Description  : 地址索引五级(备)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引五级(备)", allowEmptyValue = true, position = 13 )
    private String cityIndex5;
    
    /**
     * Description  : 地址前缀
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "地址前缀", allowEmptyValue = true, position = 14 )
    private String addressPrefix;
    
    /**
     * Description  : 详细地址
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "详细地址", allowEmptyValue = true, position = 15 )
    private String addressDetail;
    
    /**
     * Description  : 邮政编码
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:16]
     */
    @ApiModelProperty( value = "邮政编码", allowEmptyValue = true, position = 16 )
    private String postalCode;
    
    /**
     * Description  : 服务热线
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "服务热线", allowEmptyValue = true, position = 17 )
    private String hotline;
    
    /**
     * Description  : 级别
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "级别", allowEmptyValue = true, position = 18 )
    private Integer lever;
    
    /**
     * Description  : 是否认证
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否认证", allowEmptyValue = true, position = 19 )
    private Boolean isAuth;
    
    /**
     * Description  : 是否锁定
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否锁定", allowEmptyValue = true, position = 20 )
    private Boolean isLock;
    
    /**
     * Description  : 状态: 0-正常
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-正常", allowEmptyValue = true, position = 21 )
    private Integer status;
    
    /**
     * Description  : 联系方式:{company:{}, person:[{}]}
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:text][SIZE:16383]
     */
    @NotBlank
    @ApiModelProperty( value = "联系方式:{company:{}, person:[{}]}", allowEmptyValue = false, position = 22 )
    private String contactJson;
    
    /**
     * Description  : 轮播图
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:text][SIZE:16383]
     */
    @ApiModelProperty( value = "轮播图", allowEmptyValue = true, position = 23 )
    private String slideshow;
    
    /**
     * Description  : 营业范围:[0,1,2..]
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:varchar][SIZE:64]
     * DefaultValue : []
     */
    @ApiModelProperty( value = "营业范围:[0,1,2..]", allowEmptyValue = true, position = 24 )
    private String businessScopeJson;
    
    /**
     * Description  : 公司简介
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "公司简介", allowEmptyValue = true, position = 25 )
    private String descriptionInfo;
    
    /**
     * Description  : 服务流程
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "服务流程", allowEmptyValue = true, position = 26 )
    private String serviceProcessInfo;
    
    /**
     * Description  : 资质说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "资质说明", allowEmptyValue = true, position = 27 )
    private String qualificationInfo;
    
    /**
     * Description  : 名片配置信息:{}
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:varchar][SIZE:256]
     * DefaultValue : {}
     */
    @ApiModelProperty( value = "名片配置信息:{}", allowEmptyValue = true, position = 28 )
    private String configJson;
    
    /**
     * Description  : 当前名片id 对名片进行修改时使用
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "当前名片id 对名片进行修改时使用", allowEmptyValue = true, position = 29 )
    private String currentId;
    
    /**
     * Description  : 申请id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_supplier:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "申请id", allowEmptyValue = true, position = 30 )
    private String applicationId;
    
    /**
     * Description  : 创建人公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "创建人公司id", allowEmptyValue = false, position = 31 )
    private String creatorCompanyId;
    
    /**
     * Description  : 创建人公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_supplier:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "创建人公司名称", allowEmptyValue = false, position = 32 )
    private String creatorCompanyName;
    
}
