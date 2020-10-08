package com.welearn.service;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.dictionary.common.UserTypeConst;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.po.common.User;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/20.
 */
public interface TokenAuthService {
    /**
     * 签发 TOKEN 方法
     * @param user 用户
     * @param userType 用户类型
     * @param clientType 客户端类型
     * @param accessTokenExpiredSeconds ACCESS_TOKEN 有效秒数
     * @param refreshTokenExpiredSeconds REFRESH_TOKEN 有效秒数
     * @return TokenPackage
     */
    TokenPackage sign(User user, UserTypeConst userType, ClientTypeConst clientType,
                                Integer accessTokenExpiredSeconds, Integer refreshTokenExpiredSeconds);

    /**
     * 通过 REFRESH_TOKEN 更新 ACCESS_TOKEN
     * @param refreshTokenStr REFRESH_TOKEN 字符串
     * @return TokenPackage
     */
    TokenPackage refresh(String refreshTokenStr);

    /**
     * 对 ACCESS_TOKEN 进行认证
     * @param accessTokenStr ACCESS_TOKEN 字符串
     * @return AuthResult
     */
    AuthResult check(String accessTokenStr);

    /**
     * 用户登出所在客户端账户
     * @param accessTokenStr ACCESS_TOKEN 字符串
     */
    void invalid(String accessTokenStr);
}
