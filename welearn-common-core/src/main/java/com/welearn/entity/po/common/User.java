package com.welearn.entity.po.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Persistent Object : ryme_common : user
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/03/20 14:54:07
 *
 * 修改 禁止返回 password 和 authCode 字段
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/05/16 10:21:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "User", description = "User 领域实体")
public class User extends BasePersistant
{
    /**
     * Description  : 名称
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @NotBlank
    @ApiModelProperty( value = "名称", allowEmptyValue = false, position = 4 )
    private String name;

    /**
     * Description  : 邮箱
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:255]
     */
    @ApiModelProperty( value = "邮箱", allowEmptyValue = true, position = 5 )
    private String email;

    /**
     * Description  : 密码
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:255]
     */
    @ApiModelProperty( value = "密码", allowEmptyValue = true, position = 6 )
    private String password;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Description  : 手机
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:11]
     */
    @ApiModelProperty( value = "手机", allowEmptyValue = true, position = 7 )
    private String telephone;

    /**
     * Description  : 性别：0-未知，1-男，2-女
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "性别：0-未知，1-男，2-女", allowEmptyValue = true, position = 8 )
    private Integer sex;

    /**
     * Description  : 生日
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:timestamp]
     */
    @ApiModelProperty( value = "生日", allowEmptyValue = true, position = 9 )
    private Date birthday;

    /**
     * Description  : 状态：1-正常，2-请假，3-外出...
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 1
     */
    @ApiModelProperty( value = "状态：1-正常，2-请假，3-外出...", allowEmptyValue = true, position = 10 )
    private Integer state;

    /**
     * Description  : 头像图片URL
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:512]
     */
    @ApiModelProperty( value = "头像图片URL", allowEmptyValue = true, position = 11 )
    private String headImageUrl;

    /**
     * Description  : 公司id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "公司id", allowEmptyValue = true, position = 12 )
    private String companyId;

    /**
     * Description  : 部门id
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:32]
     */
    @ApiModelProperty( value = "部门id", allowEmptyValue = true, position = 13 )
    private String departmentId;

    /**
     * Description  : 锁定状态：0-未锁定，1-已锁定
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "锁定状态：0-未锁定，1-已锁定", allowEmptyValue = true, position = 14 )
    private Integer lockStatus;

    /**
     * Description  : 锁定计数
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "锁定计数", allowEmptyValue = true, position = 15 )
    private Integer lockCount;

    /**
     * Description  : 锁定计时
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:timestamp]
     */
    @ApiModelProperty( value = "锁定计时", allowEmptyValue = true, position = 16 )
    private Date lockedAt;

    /**
     * Description  : 微信状态 0-未关注 1-已关注 2-已绑定
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "微信状态 0-未关注 1-已关注 2-已绑定", allowEmptyValue = true, position = 17 )
    private Integer wechatStatus;

    /**
     * Description  : 账户类型: 0-人 1-设备
     * Constraint   : [NOT NULL]
     * TableColumn   : [ryme_common:int][PRECISION:10]
     * DefaultValue : 0
     */
    @ApiModelProperty( value = "账户类型: 0-人 1-设备", allowEmptyValue = true, position = 18 )
    private Integer type;

    /**
     * Description  : 验证码
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:varchar][SIZE:16]
     */
    @ApiModelProperty( value = "验证码", allowEmptyValue = true, position = 19 )
    private String authCode;

    @JsonIgnore
    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * Description  : 验证码最近一次发送时间
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:timestamp]
     */
    @ApiModelProperty( value = "验证码最近一次发送时间", allowEmptyValue = true, position = 20 )
    private Date authCodeLastSendAt;

    /**
     * Description  : 参数配置项 key-value 键值对
     * Constraint   : [CAN NULL]
     * TableColumn   : [ryme_common:text][SIZE:16383]
     */
    @ApiModelProperty( value = "参数配置项 key-value 键值对", allowEmptyValue = true, position = 21 )
    private String configJson;

}
