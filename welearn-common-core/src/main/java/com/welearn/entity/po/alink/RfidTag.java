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
 * Persistent Object : ryme_alink : rfid_tag
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/16 16:51:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RfidTag", description = "RfidTag 领域实体")
public class RfidTag extends BasePersistant
{
    /**
     * Description  : 标签编号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "标签编号", allowEmptyValue = false, position = 4 )
    private String no;
    
    /**
     * Description  : 类型: 0-125kHz
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "类型: 0-125kHz", allowEmptyValue = false, position = 5 )
    private Integer type;
    
    /**
     * Description  : 规格: 如 蓝色 直径25mm标签 等
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @NotBlank
    @ApiModelProperty( value = "规格: 如 蓝色 直径25mm标签 等", allowEmptyValue = false, position = 6 )
    private String specification;
    
    /**
     * Description  : 图标
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:text][SIZE:16383]
     */
    @ApiModelProperty( value = "图标", allowEmptyValue = true, position = 7 )
    private String image;
    
    /**
     * Description  : 关联ID
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "关联ID", allowEmptyValue = true, position = 8 )
    private String refId;
    
    /**
     * Description  : 关联类型: User, Equipment
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "关联类型: User, Equipment", allowEmptyValue = true, position = 9 )
    private String refType;
    
    /**
     * Description  : 关联类型名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "关联类型名称", allowEmptyValue = true, position = 10 )
    private String refName;
    
    /**
     * Description  : 关联类型描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_alink:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "关联类型描述", allowEmptyValue = true, position = 11 )
    private String refDescription;
    
}
