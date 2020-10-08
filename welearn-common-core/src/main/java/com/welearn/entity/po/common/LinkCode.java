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
 * Persistent Object : ryme_common : link_code
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/4/26 10:34:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LinkCode", description = "LinkCode 领域实体")
public class LinkCode extends BasePersistant
{
    /**
     * Description  : 编号: 获取当前数据库数据数量, 设置此值
     * Constraint   : [NOT NULL] [Unique] 
     * TableColumn   : [ryme_common:bigint][PRECISION:19]
     */
    @NotNull
    @ApiModelProperty( value = "编号: 获取当前数据库数据数量, 设置此值", allowEmptyValue = false, position = 4 )
    private Long number;
    
    /**
     * Description  : 类型: 0-ID 1-链接
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "类型: 0-ID 1-链接", allowEmptyValue = false, position = 5 )
    private Integer type;
    
    /**
     * Description  : 服务类型前缀:1,2 位
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:2]
     * DefaultValue : FF
     */
    @ApiModelProperty( value = "服务类型前缀:1,2 位", allowEmptyValue = true, position = 6 )
    private String servicePrefix;
    
    /**
     * Description  : 实体类型前缀:3,4 位
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:2]
     * DefaultValue : FF
     */
    @ApiModelProperty( value = "实体类型前缀:3,4 位", allowEmptyValue = true, position = 7 )
    private String persistantPrefix;
    
    /**
     * Description  : 序号:[servicePrefix:2][persistantPrefix:2][number:12(十六进制)]
     * Constraint   : [NOT NULL] [Unique] 
     * TableColumn   : [ryme_common:varchar][SIZE:16]
     */
    @NotBlank
    @ApiModelProperty( value = "序号:[servicePrefix:2][persistantPrefix:2][number:12(十六进制)]", allowEmptyValue = false, position = 8 )
    private String serialNumber;
    
    /**
     * Description  : 是否绑定：0-未绑定 1-已绑定
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "是否绑定：0-未绑定 1-已绑定", allowEmptyValue = true, position = 9 )
    private Boolean isBinding;
    
    /**
     * Description  : 绑定时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:timestamp]
     */
    @ApiModelProperty( value = "绑定时间", allowEmptyValue = true, position = 10 )
    private Date bindingAt;
    
    /**
     * Description  : 绑定id(类型为ID时可用)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "绑定id(类型为ID时可用)", allowEmptyValue = true, position = 11 )
    private String bindingId;
    
    /**
     * Description  : 绑定载荷(链接/绑定id时附带参数)
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:text][SIZE:16383]
     */
    @ApiModelProperty( value = "绑定载荷(链接/绑定id时附带参数)", allowEmptyValue = true, position = 12 )
    private String bindingPayload;
    
    /**
     * Description  : 条码所属公司Id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "条码所属公司Id", allowEmptyValue = true, position = 13 )
    private String companyId;
    
}
