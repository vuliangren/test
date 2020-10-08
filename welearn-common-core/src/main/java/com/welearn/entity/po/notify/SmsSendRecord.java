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
 * Persistent Object : ryme_notify : sms_send_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/18 17:42:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SmsSendRecord", description = "SmsSendRecord 领域实体")
public class SmsSendRecord extends BasePersistant
{
    /**
     * Description  : 用户id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "用户id", allowEmptyValue = true, position = 4 )
    private String userId;
    
    /**
     * Description  : 公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "公司id", allowEmptyValue = true, position = 5 )
    private String companyId;
    
    /**
     * Description  : 部门id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "部门id", allowEmptyValue = true, position = 6 )
    private String departmentId;
    
    /**
     * Description  : 公告id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:36]
     */
    @ApiModelProperty( value = "公告id", allowEmptyValue = true, position = 7 )
    private String noticeId;
    
    /**
     * Description  : 手机号
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:16]
     */
    @NotBlank
    @ApiModelProperty( value = "手机号", allowEmptyValue = false, position = 8 )
    private String phoneNumber;
    
    /**
     * Description  : 短信模板id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "短信模板id", allowEmptyValue = false, position = 9 )
    private String templateId;
    
    /**
     * Description  : 短信签名
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:36]
     */
    @NotBlank
    @ApiModelProperty( value = "短信签名", allowEmptyValue = false, position = 10 )
    private String signature;
    
    /**
     * Description  : 短信参数
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "短信参数", allowEmptyValue = true, position = 11 )
    private String paramsJson;
    
    /**
     * Description  : 短信长度: 70个汉字/140个英文字符 算1条
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "短信长度: 70个汉字/140个英文字符 算1条", allowEmptyValue = true, position = 12 )
    private Integer size;
    
    /**
     * Description  : 发送时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:timestamp]
     */
    @ApiModelProperty( value = "发送时间", allowEmptyValue = true, position = 13 )
    private Date sendAt;
    
    /**
     * Description  : 接收时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:timestamp]
     */
    @ApiModelProperty( value = "接收时间", allowEmptyValue = true, position = 14 )
    private Date receivedAt;
    
    /**
     * Description  : 发送序列号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "发送序列号", allowEmptyValue = true, position = 15 )
    private String bizId;
    
    /**
     * Description  : 用户序列号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "用户序列号", allowEmptyValue = true, position = 16 )
    private String outId;
    
    /**
     * Description  : 状态报告编码
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "状态报告编码", allowEmptyValue = true, position = 17 )
    private String reportCode;
    
    /**
     * Description  : 状态报告说明
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "状态报告说明", allowEmptyValue = true, position = 18 )
    private String reportMessage;
    
    /**
     * Description  : 状态：0-等待回执 1-发送成功 2-发送失败
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态：0-等待回执 1-发送成功 2-发送失败", allowEmptyValue = true, position = 19 )
    private Integer status;
    
}
