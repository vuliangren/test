package com.welearn.service;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.dictionary.common.UserTypeConst;
import com.welearn.entity.dto.authorization.Token;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import io.jsonwebtoken.Claims;
import javax.validation.constraints.NotNull;

/**
 * Description : TOKEN 处理相关业务
 * Created by Setsuna Jin on 2018/4/19.
 */
@Validated
public interface TokenCacheService {
    /**
     * 签发 ACCESS_TOKEN 和 REFRESH_TOKEN 存入缓存
     * @param user 用户
     * @param userType 用户类型
     * @param clientType 客户端类型
     * @param issuer 签发方
     * @param audience 接受方
     * @param accessTokenExpiredSeconds ACCESS_TOKEN 过期秒数
     * @param refreshTokenExpiredSeconds REFRESH_TOKEN 过期秒数
     * @param refreshToken 用于 ACCESS_TOKEN 刷新时使用
     * @return ACCESS_TOKEN 和 REFRESH_TOKEN
     */
    TokenPackage createTokenPackageCache(@NotNull User user, @NotNull UserTypeConst userType, @NotNull ClientTypeConst clientType,
                                         ServiceConst issuer, ServiceConst audience,
                                         int accessTokenExpiredSeconds, int refreshTokenExpiredSeconds, String refreshToken);
    /**
     * 从缓存中读取 TokenPackage 信息
     * @param user 用户
     * @param clientType 客户端类型
     * @return TokenPackage
     */
    TokenPackage findInTokenPackageCache(@NotNull User user, @NotNull ClientTypeConst clientType);

    /**
     * 将 Token 字符串转换为 Token 实体
     * @param tokenStr Token 字符串
     * @return Token 实体
     */
    Token transform(@NotBlank String tokenStr);

    /**
     * 创建 AuthResult 缓存
     * @param user 用户
     * @param clientType 客户端类型
     * @param accessToken ACCESS_TOKEN
     * @return AuthResult
     */
    AuthResult createAuthCheckCache(@NotNull User user, @NotNull ClientTypeConst clientType, @NotNull Token accessToken);

    /**
     * 创建 AuthResult 缓存
     * @param user 用户
     * @param clientType 客户端类型
     * @param authResult ACCESS_TOKEN
     * @return AuthResult
     */
    AuthResult updateAuthCheckCache(@NotNull User user, @NotNull ClientTypeConst clientType, @NotNull AuthResult authResult);


    /**
     * 解析 JWT Token 字符串 在规定的时间内
     * 防止因 JWT 自身的过期时间导致解析失败
     * @param tokenStr JWT Token 字符串
     * @return 解析内容
     */
    Claims parseTokenInAllowedClock(@NotBlank String tokenStr);
}
