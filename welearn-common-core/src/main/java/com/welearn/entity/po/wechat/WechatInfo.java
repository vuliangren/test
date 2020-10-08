package com.welearn.entity.po.wechat;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : welearn-wechat : wechat_info
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/4/27 2:06:54
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WechatInfo extends BasePersistant
{
    /**
     * Description  : OPEN-ID
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-wechat:varchar][SIZE:512]
     */
    @NotBlank
    private String openId;
    
    /**
     * Description  : 昵称
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-wechat:varchar][SIZE:200]
     */
    @NotBlank
    private String nickname;
    
    /**
     * Description  : 性别
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-wechat:int][PRECISION:10]
     */
    @NotNull
    private Integer sex;
    
    /**
     * Description  : 语言
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-wechat:varchar][SIZE:40]
     */
    @NotBlank
    private String language;
    
    /**
     * Description  : 城市
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-wechat:varchar][SIZE:80]
     */
    @NotBlank
    private String city;
    
    /**
     * Description  : 省份
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-wechat:varchar][SIZE:80]
     */
    @NotBlank
    private String province;
    
    /**
     * Description  : 国家
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-wechat:varchar][SIZE:80]
     */
    @NotBlank
    private String country;
    
    /**
     * Description  : 头像
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-wechat:varchar][SIZE:1020]
     */
    @NotBlank
    private String headImageUrl;
    
    /**
     * Description  : 关注时间
     * Constraint   : [NOT NULL] 
     * TableColumn   : [welearn-wechat:datetime]
     */
    @NotNull
    private Date subscribeTime;
    
    /**
     * Description  : UNION-ID
     * Constraint   : [CAN NULL]
     * TableColumn   : [welearn-wechat:varchar][SIZE:512]
     */
    private String unionId;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [welearn-wechat:varchar][SIZE:160]
     */
    private String remark;
    
    /**
     * Description  : 组
     * Constraint   : [CAN NULL]
     * TableColumn   : [welearn-wechat:int][PRECISION:10]
     */
    private Integer groupId;
    
    /**
     * Description  : 用户id
     * Constraint   : [CAN NULL]
     * TableColumn   : [welearn-wechat:bigint][PRECISION:20]
     */
    private String userId;
    
}
