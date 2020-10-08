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
 * Persistent Object : ryme_notify : notice
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/15 18:41:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Notice", description = "Notice 领域实体")
public class Notice extends BasePersistant
{
    /**
     * Description  : 发布时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:timestamp]
     * DefaultValue : CURRENT_TIMESTAMP
     */
    @ApiModelProperty( value = "发布时间", allowEmptyValue = true, position = 4 )
    private Date releasedAt;
    
    /**
     * Description  : 过期时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:timestamp]
     */
    @ApiModelProperty( value = "过期时间", allowEmptyValue = true, position = 5 )
    private Date expiredAt;
    
    /**
     * Description  : 状态: 0-待通知 1-通知中 2-已通知 3-部分通知(发生异常)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "状态: 0-待通知 1-通知中 2-已通知 3-部分通知(发生异常)", allowEmptyValue = true, position = 6 )
    private Integer status;
    
    /**
     * Description  : 类型: 0-系统公告 1-公司公告 2-部门公告 3-员工通知
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     */
    @NotNull
    @ApiModelProperty( value = "类型: 0-系统公告 1-公司公告 2-部门公告 3-员工通知", allowEmptyValue = false, position = 7 )
    private Integer type;
    
    /**
     * Description  : 级别: 0-一般通知 1-重要通知 2-紧急通知 3-加急通知
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "级别: 0-一般通知 1-重要通知 2-紧急通知 3-加急通知", allowEmptyValue = true, position = 8 )
    private Integer lever;
    
    /**
     * Description  : 站内信接收方客户端类型: 0-所有客户端 1-WEB端 4-小程序
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "站内信接收方客户端类型: 0-所有客户端 1-WEB端 4-小程序", allowEmptyValue = true, position = 9 )
    private Integer clientType;
    
    /**
     * Description  : 通知方式:0-站内通知 1-站内通知+邮件通知 2-站内通知+短信通知 3-站内通知+邮件通知+短信通知
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "通知方式:0-站内通知 1-站内通知+邮件通知 2-站内通知+短信通知 3-站内通知+邮件通知+短信通知", allowEmptyValue = true, position = 10 )
    private Integer method;
    
    /**
     * Description  : 标题
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @NotBlank
    @ApiModelProperty( value = "标题", allowEmptyValue = false, position = 11 )
    private String title;
    
    /**
     * Description  : 是否是简短公告(简短公告无内容id, 且不记录阅读记录)
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:tinyint][PRECISION:3]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "是否是简短公告(简短公告无内容id, 且不记录阅读记录)", allowEmptyValue = true, position = 12 )
    private Boolean isShortNotice;
    
    /**
     * Description  : 内容id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "内容id", allowEmptyValue = true, position = 13 )
    private String contentId;
    
    /**
     * Description  : 是否允许用户关闭公告
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:tinyint][PRECISION:3]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "是否允许用户关闭公告", allowEmptyValue = true, position = 14 )
    private Boolean isNoticeCloseable;
    
    /**
     * Description  : 发布方描述
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     * DefaultValue : 系统中心
     */
    @ApiModelProperty( value = "发布方描述", allowEmptyValue = true, position = 15 )
    private String senderDescription;
    
    /**
     * Description  : 发布方id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "发布方id", allowEmptyValue = true, position = 16 )
    private String senderId;
    
    /**
     * Description  : 发布方名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:64]
     */
    @ApiModelProperty( value = "发布方名称", allowEmptyValue = true, position = 17 )
    private String senderName;
    
    /**
     * Description  : 发布方公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "发布方公司id", allowEmptyValue = true, position = 18 )
    private String senderCompanyId;
    
    /**
     * Description  : 发布方公司名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "发布方公司名称", allowEmptyValue = true, position = 19 )
    private String senderCompanyName;
    
    /**
     * Description  : 发布方部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "发布方部门id", allowEmptyValue = true, position = 20 )
    private String senderDepartmentId;
    
    /**
     * Description  : 发布方部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "发布方部门名称", allowEmptyValue = true, position = 21 )
    private String senderDepartmentName;
    
    /**
     * Description  : 接收方id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "接收方id", allowEmptyValue = true, position = 22 )
    private String receiverId;
    
    /**
     * Description  : 接收方名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "接收方名称", allowEmptyValue = true, position = 23 )
    private String receiverName;
    
    /**
     * Description  : 接收方公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "接收方公司id", allowEmptyValue = true, position = 24 )
    private String receiverCompanyId;
    
    /**
     * Description  : 接收方公司名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "接收方公司名称", allowEmptyValue = true, position = 25 )
    private String receiverCompanyName;
    
    /**
     * Description  : 接收方部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "接收方部门id", allowEmptyValue = true, position = 26 )
    private String receiverDepartmentId;
    
    /**
     * Description  : 接收方部门名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @ApiModelProperty( value = "接收方部门名称", allowEmptyValue = true, position = 27 )
    private String receiverDepartmentName;
    
    /**
     * Description  : 关联类型
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "关联类型", allowEmptyValue = true, position = 28 )
    private String refType;
    
    /**
     * Description  : 关联类型id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "关联类型id", allowEmptyValue = true, position = 29 )
    private String refId;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:256]
     */
    @ApiModelProperty( value = "备注", allowEmptyValue = true, position = 30 )
    private String remark;
    
}
