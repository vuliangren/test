package com.welearn.entity.po.common;

import java.util.Date;
import com.welearn.entity.po.BasePersistant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Persistent Object : ryme_common : wechat_app_user
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/22 15:00:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WechatAppUser extends BasePersistant
{
    /**
     * Description  : 用户id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:128]
     */
    private String userId;
    
    /**
     * Description  : openId
     * Constraint   : [NOT NULL] 
     * TableColumn   : [ryme_common:varchar][SIZE:512]
     */
    @NotBlank
    private String openId;
    
    /**
     * Description  : sessionKey
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:512]
     */
    private String sessionKey;
    
    /**
     * Description  : sessionKey创建时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:512]
     */
    private Date sessionKeyCreatedAt;
    
    /**
     * Description  : 昵称
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:200]
     */
    private String nickName;
    
    /**
     * Description  : 性别
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     */
    private Integer gender;
    
    /**
     * Description  : 语言
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:40]
     */
    private String language;
    
    /**
     * Description  : 城市
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:80]
     */
    private String city;
    
    /**
     * Description  : 省份
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:80]
     */
    private String province;
    
    /**
     * Description  : 国家
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:80]
     */
    private String country;
    
    /**
     * Description  : 头像
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:1020]
     */
    private String avatarUrl;
    
    /**
     * Description  : UNION-ID
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:512]
     */
    private String unionId;
    
    /**
     * Description  : 备注
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:160]
     */
    private String remark;
    
    /**
     * Description  : 组
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     */
    private Integer groupId;
    
}
