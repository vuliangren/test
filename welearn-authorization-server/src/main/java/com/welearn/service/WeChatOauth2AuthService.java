package com.welearn.service;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Description : 微信 OAuth2 网页授权认证
 * Created by Setsuna Jin on 2018/4/20.
 */
public interface WeChatOauth2AuthService extends TokenAuthService {
    String generateOAuth2Url();
    /**
     * 登录系统
     * @param code 回调得到的 scode
     * @param clientTypeConst 客户端类型
     * @return TokenPackage
     */
    TokenPackage login(@NotBlank String code, @NotNull ClientTypeConst clientTypeConst);
}
