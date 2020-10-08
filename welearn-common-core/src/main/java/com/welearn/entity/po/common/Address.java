package com.welearn.entity.po.common;

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
 * Persistent Object : ryme_common : address
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/16 18:24:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Address", description = "Address 领域实体")
public class Address extends BasePersistant
{
    /**
     * Description  : 国家编号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:4]
     * DefaultValue : CN
     */
    @ApiModelProperty( value = "国家编号", allowEmptyValue = true, position = 4 )
    private String country;
    
    /**
     * Description  : 地址索引一级(省)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引一级(省)", allowEmptyValue = true, position = 5 )
    private String cityIndex1;
    
    /**
     * Description  : 地址索引二级(市)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引二级(市)", allowEmptyValue = true, position = 6 )
    private String cityIndex2;
    
    /**
     * Description  : 地址索引三级(区)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引三级(区)", allowEmptyValue = true, position = 7 )
    private String cityIndex3;
    
    /**
     * Description  : 地址索引四级(街)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引四级(街)", allowEmptyValue = true, position = 8 )
    private String cityIndex4;
    
    /**
     * Description  : 地址索引五级(备)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "地址索引五级(备)", allowEmptyValue = true, position = 9 )
    private String cityIndex5;
    
    /**
     * Description  : 地址前缀
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "地址前缀", allowEmptyValue = true, position = 10 )
    private String addressPrefix;
    
    /**
     * Description  : 详细地址
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "详细地址", allowEmptyValue = true, position = 11 )
    private String addressDetail;
    
    /**
     * Description  : 邮政编码
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:16]
     */
    @ApiModelProperty( value = "邮政编码", allowEmptyValue = true, position = 12 )
    private String postalCode;

    /**
     * Description  : 经度
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:decimal][SCALE:8][PRECISION:12]
     */
    @ApiModelProperty( value = "经度", allowEmptyValue = true, position = 13 )
    private Double longitude;

    /**
     * Description  : 纬度
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:decimal][SCALE:8][PRECISION:12]
     */
    @ApiModelProperty( value = "纬度", allowEmptyValue = true, position = 14 )
    private Double latitude;

    /**
     * Description  : 备注内容
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "备注内容", allowEmptyValue = true, position = 15 )
    private String remark;
}
