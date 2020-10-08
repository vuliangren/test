package com.welearn.service;

import com.welearn.entity.po.common.SupplierRegister;
import com.welearn.entity.vo.response.authorization.WeChatAppLoginResult;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Description : 注册 业务
 * Created by Setsuna Jin on 2018/11/29.
 */
@Validated
public interface RegisterService {

    /**
     * 供应商注册申请
     * @param supplierRegister 注册申请
     */
    void supplierRegister(@NotNull SupplierRegister supplierRegister);

    /**
     * 用户小程序注册 获取验证码
     * @param name 用户名
     * @param telephone 手机号
     * @param sn 扫码时的二位码序列号(选填)
     */
    void userWeChatAppRequest(@NotBlank String name, @NotBlank String telephone, String sn);

    /**
     * 用户小程序注册 验证验证码
     * @param telephone 手机
     * @param authCode 验证码
     * @param openId 微信小程序的openId
     * @param type 注册类型 影响注册用户的角色/职位
     * @return WeChatAppLoginResult
     */
    WeChatAppLoginResult userWeChatAppProcess(@NotBlank String telephone, @NotBlank String authCode, @NotBlank String openId, @NotNull Integer type);
}
