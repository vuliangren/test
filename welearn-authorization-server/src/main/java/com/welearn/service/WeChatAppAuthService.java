package com.welearn.service;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.common.WechatAppUser;
import com.welearn.entity.vo.request.authorization.AccessToken;
import com.welearn.entity.vo.request.common.PasswordLogin;
import com.welearn.entity.vo.response.authorization.WeChatAppLoginResult;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/22.
 */
@Validated
public interface WeChatAppAuthService extends TokenAuthService {

    /**
     * 系统内部使用不对外开放接口
     * @param user 用户
     * @param openId openId
     * @return WeChatAppLoginResult
     */
    WeChatAppLoginResult bind(User user, String openId);

    WeChatAppLoginResult loginWithCode(@NotBlank String code) throws WeixinException;

    WeChatAppLoginResult bindWithPassword(@Valid PasswordLogin passwordLogin, @NotBlank String openId);

    void unbindWithAccessToken(@Valid AccessToken accessToken);

    void updateWechatAppUserInfo(@NotBlank String openId, @NotBlank String encryptedData, @NotBlank String iv);
}
