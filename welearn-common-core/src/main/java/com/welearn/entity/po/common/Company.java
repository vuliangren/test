package com.welearn.entity.po.common;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.welearn.dictionary.common.CompanyConfigConst;
import com.welearn.dictionary.common.CompanyTypeConst;
import com.welearn.entity.po.BasePersistant;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_common : company
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/13 10:55:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends BasePersistant
{
    /**
     * Description  : 父节点主键
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "父节点主键", allowEmptyValue = true, position = 1 )
    private String parentId;

    /**
     * Description  : 名称
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 5 )
    private String name;

    /**
     * Description  : 描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "描述", allowEmptyValue = true, position = 6 )
    private String description;

    /**
     * Description  : 地址id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址id", allowEmptyValue = true, position = 7 )
    private String addressId;

    /**
     * Description  : 电话
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:16]
     */
    @ApiModelProperty( value = "电话", allowEmptyValue = true, position = 8 )
    private String phone;

    /**
     * Description  : 商标LOGO图片的URL
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "商标LOGO图片的URL", allowEmptyValue = true, position = 9 )
    private String logo;

    /**
     * Description  : 创建者id
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "创建者id", allowEmptyValue = false, position = 10 )
    private String creatorId;

    /**
     * Description  : 管理员id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "管理员id", allowEmptyValue = true, position = 11 )
    private String adminId;

    /**
     * Description  : 类型: 0-医院 1-供应 2-系统
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "类型: 0-医院 1-供应 2-系统", allowEmptyValue = false, position = 12 )
    private Integer type;

    /**
     * Description  : 标签: 可有多个标签,根据标签来确认功能
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "标签: 可有多个标签,根据标签来确认功能", allowEmptyValue = true, position = 13 )
    private String tags;

    /**
     * Description  : 状态: 0-正常
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-正常", allowEmptyValue = true, position = 14 )
    private Integer state;

    /**
     * Description  : 营业执照id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "营业执照id", allowEmptyValue = true, position = 15 )
    private String businessLicenseId;

    /**
     * Description  : 参数配置项 key-value 键值对
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:text][SIZE:16383]
     */
    @ApiModelProperty( value = "参数配置项 key-value 键值对", allowEmptyValue = true, position = 16 )
    private String configJson;

    /**
     * 商标LOGO图片的URL
     * 因内部使用七牛云存储 链接为临时生成的
     */
    private String logoUrl;

    /**
     * 获取配置文件项
     * @param companyConfig CompanyConfigConst 配置项名称
     * @return 配置项
     */
    @SuppressWarnings("unchecked")
    public <T> T getConfig(CompanyConfigConst companyConfig){
        Map<String, Object> configs = CompanyConfigConst.parseConfigJson(this.getConfigJson(), CompanyTypeConst.values()[this.getType()]);
        Object value = configs.get(companyConfig.getKey());
        if (Objects.nonNull(value))
            return (T)value;
        else {
            return (T)companyConfig.getValue();
        }
    }
}
