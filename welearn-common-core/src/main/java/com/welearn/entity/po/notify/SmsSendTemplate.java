package com.welearn.entity.po.notify;

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
 * Persistent Object : ryme_notify : sms_send_template
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/18 17:42:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SmsSendTemplate", description = "SmsSendTemplate 领域实体")
public class SmsSendTemplate extends BasePersistant
{
    /**
     * Description  : 创建人id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "创建人id", allowEmptyValue = false, position = 4 )
    private String creatorId;
    
    /**
     * Description  : 系统内部编码
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "系统内部编码", allowEmptyValue = true, position = 5 )
    private String code;
    
    /**
     * Description  : 模板名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "模板名称", allowEmptyValue = true, position = 6 )
    private String name;
    
    /**
     * Description  : 模板类型: 0-验证码 1-短信通知 2-推广短信
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "模板类型: 0-验证码 1-短信通知 2-推广短信", allowEmptyValue = false, position = 7 )
    private Integer type;
    
    /**
     * Description  : 阿里云的模板编号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "阿里云的模板编号", allowEmptyValue = true, position = 8 )
    private String templateCode;
    
    /**
     * Description  : 模板内容
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:512]
     */
    @NotBlank
    @ApiModelProperty( value = "模板内容", allowEmptyValue = false, position = 9 )
    private String content;
    
    /**
     * Description  : 状态：0-审核中 1-已通过 2-未通过
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态：0-审核中 1-已通过 2-未通过", allowEmptyValue = true, position = 10 )
    private Integer status;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 11 )
    private String remark;
    
}
