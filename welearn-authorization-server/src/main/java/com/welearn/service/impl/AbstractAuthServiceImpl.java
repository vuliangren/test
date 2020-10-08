package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.cache.evict.AuthorizationCacheEvictor;
import com.welearn.cache.evict.RouteCacheEvictor;
import com.welearn.dictionary.authorization.TokenTypeConst;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.dictionary.common.UserTypeConst;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.dto.authorization.Token;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.po.common.User;
import com.welearn.error.exception.TokenAuthCheckFailedException;
import com.welearn.service.TokenAuthService;
import com.welearn.service.TokenCacheService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * Description : TOKEN 授权业务抽象实现
 * Created by Setsuna Jin on 2018/4/20.
 */
@Slf4j
@Service
public class AbstractAuthServiceImpl implements TokenAuthService {

    @Autowired
    private TokenCacheService tokenCacheService;

    @Autowired
    private AuthorizationCacheEvictor authorizationCacheEvictor;

    @Autowired
    private RouteCacheEvictor routeCacheEvictor;

    // 默认 ACCESS_TOKEN  有效期 2  个小时
    @Value("${token.access-token.defaultExpiredSeconds}")
    private Integer defaultAccessTokenExpiredSeconds;

    // 默认 REFRESH_TOKEN 有效期 24 个小时
    @Value("${token.refresh-token.defaultExpiredSeconds}")
    private Integer defaultRefreshTokenExpiredSeconds;

    /**
     * 签发 TOKEN 方法
     * @param user 用户
     * @param userType 用户类型
     * @param clientType 客户端类型
     * @param accessTokenExpiredSeconds ACCESS_TOKEN 有效秒数
     * @param refreshTokenExpiredSeconds REFRESH_TOKEN 有效秒数
     * @return TokenPackage
     */
    @Override
    public TokenPackage sign(User user, UserTypeConst userType, ClientTypeConst clientType,
                      Integer accessTokenExpiredSeconds, Integer refreshTokenExpiredSeconds){
        if (Objects.isNull(accessTokenExpiredSeconds))
            accessTokenExpiredSeconds = defaultAccessTokenExpiredSeconds;
        if (Objects.isNull(refreshTokenExpiredSeconds))
            refreshTokenExpiredSeconds = defaultRefreshTokenExpiredSeconds;
        // 清理旧的认证信息 禁止异地登录
        TokenPackage oldTokenPackageCache = tokenCacheService.findInTokenPackageCache(user, clientType);
        if (Objects.nonNull(oldTokenPackageCache))
            this.invalid(oldTokenPackageCache.getAccessToken());
        // 创建新的认证信息
        return tokenCacheService.createTokenPackageCache(user, userType, clientType, null, null,
                accessTokenExpiredSeconds, refreshTokenExpiredSeconds, null);
    }

    /**
     * 通过 REFRESH_TOKEN 更新 ACCESS_TOKEN
     * @param refreshTokenStr REFRESH_TOKEN 字符串
     * @return TokenPackage
     */
    @Override
    public TokenPackage refresh(String refreshTokenStr){
        Token refreshToken = tokenCacheService.transform(refreshTokenStr);
        // 验证 TOKEN 类型
        if (!refreshToken.getTokenType().equals(TokenTypeConst.REFRESH_TOKEN))
            throw new TokenAuthCheckFailedException("refreshToken ttp error");
        // 验证过期时间
        if (refreshToken.getExpiredAt().getTime() < new Date().getTime())
            throw new TokenAuthCheckFailedException("refreshToken is expired");
        // 验证是否在缓存中，未失效
        TokenPackage tokenPackageInCache = tokenCacheService.findInTokenPackageCache(refreshToken.getUser(), refreshToken.getClientTypeConst());
        if (Objects.isNull(tokenPackageInCache) || !tokenPackageInCache.getRefreshToken().equals(refreshTokenStr))
            throw new TokenAuthCheckFailedException("refreshToken is invalid");
        // 清除原先的 Route 缓存
        else
            routeCacheEvictor.deleteRouteAuthCache(tokenPackageInCache.getAccessToken());
        // 重写签发TOKEN
        return tokenCacheService.createTokenPackageCache(refreshToken.getUser(), refreshToken.getUserTypeConst(),
                refreshToken.getClientTypeConst(), refreshToken.getIssuer(), refreshToken.getAudience(),
                defaultAccessTokenExpiredSeconds, defaultRefreshTokenExpiredSeconds, refreshTokenStr);
    }

    /**
     * 对 ACCESS_TOKEN 进行认证
     * @param accessTokenStr ACCESS_TOKEN 字符串
     * @return AuthResult
     */
    @Override
    public AuthResult check(String accessTokenStr){
        Token accessToken = tokenCacheService.transform(accessTokenStr);
        // 验证 TOKEN 类型
        if (!accessToken.getTokenType().equals(TokenTypeConst.ACCESS_TOKEN))
            throw new TokenAuthCheckFailedException("accessToken ttp error");
        // 验证过期时间
        if (accessToken.getExpiredAt().getTime() < new Date().getTime())
            throw new TokenAuthCheckFailedException("accessToken is expired");
        // 验证是否在缓存中, 未失效
        TokenPackage tokenPackageInCache = tokenCacheService.findInTokenPackageCache(accessToken.getUser(), accessToken.getClientTypeConst());
        if (Objects.isNull(tokenPackageInCache) || !tokenPackageInCache.getAccessToken().equals(accessTokenStr))
            throw new TokenAuthCheckFailedException("accessToken is invalid");
        AuthResult authResult = tokenCacheService.createAuthCheckCache(accessToken.getUser(), accessToken.getClientTypeConst(), accessToken);
        if (!authResult.getAccessToken().equals(accessToken)){
            log.debug("AuthResult.accessToken:{} is different with current accessToken:{}, Update AuthResult",
                    JSON.toJSONString(authResult.getAccessToken()),
                    JSON.toJSONString(accessToken));
            authResult.setAccessToken(accessToken);
            tokenCacheService.updateAuthCheckCache(accessToken.getUser(), accessToken.getClientTypeConst(), authResult);
        }
        return authResult;
    }

    /**
     * 用户登出所在客户端账户
     * @param accessTokenStr ACCESS_TOKEN 字符串
     */
    @Override
    public void invalid(String accessTokenStr){
        try {
            Claims claims = tokenCacheService.parseTokenInAllowedClock(accessTokenStr);
            User user = new User();
            user.setId(claims.getSubject());
            ClientTypeConst clientType = ClientTypeConst.values()[claims.get("ctp", Integer.class)];
            authorizationCacheEvictor.deleteTokenPackageCache(user, clientType);
            authorizationCacheEvictor.deleteAuthCheckCache(user, clientType);
            authorizationCacheEvictor.deleteTokenTransformCache(accessTokenStr);
            routeCacheEvictor.deleteRouteAuthCache(accessTokenStr);
        } catch (Exception e){
            e.printStackTrace();
            log.debug("Invalid accessToken failed:{}", accessTokenStr);
        }
    }
}
