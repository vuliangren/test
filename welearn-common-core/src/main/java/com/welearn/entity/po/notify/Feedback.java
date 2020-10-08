package com.welearn.entity.po.notify;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_notify : feedback
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/21 20:16:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Feedback extends BasePersistant
{
    /**
     * Description  : 反馈用户id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @NotBlank
    private String userId;
    
    /**
     * Description  : 反馈用户公司id
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @NotBlank
    private String userCompanyId;
    
    /**
     * Description  : 反馈用户公司名称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:128]
     */
    @NotBlank
    private String userCompanyName;
    
    /**
     * Description  : 用户名称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:256]
     */
    private String userName;
    
    /**
     * Description  : 用户邮箱
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:512]
     */
    private String userEmail;
    
    /**
     * Description  : 用户手机号
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:44]
     */
    private String userPhoneNumber;
    
    /**
     * Description  : 客户端类型
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:int][PRECISION:10]
     */
    @NotNull
    private Integer clientType;
    
    /**
     * Description  : 客户端版本
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:varchar][SIZE:64]
     */
    @NotBlank
    private String clientVersion;
    
    /**
     * Description  : 客户端环境JSON描述
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:text][SIZE:65535]
     */
    private String environmentJson;
    
    /**
     * Description  : 反馈内容
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:text][SIZE:65535]
     */
    private String content;
    
    /**
     * Description  : 意见是否接受
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @NotNull
    private Boolean isAccept;
    
    /**
     * Description  : 意见是否处理
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_notify:tinyint][PRECISION:3]
     * DefaultValue : 0
     */
    @NotNull
    private Boolean isProcessed;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_notify:varchar][SIZE:1024]
     */
    private String remark;
    
}
