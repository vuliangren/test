package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.dictionary.authorization.TokenTypeConst;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.dictionary.common.CompanyConfigConst;
import com.welearn.dictionary.common.CompanyTypeConst;
import com.welearn.dictionary.common.UserTypeConst;
import com.welearn.entity.dto.authorization.Token;
import com.welearn.entity.po.common.*;
import com.welearn.entity.qo.common.WechatAppUserQueryCondition;
import com.welearn.entity.qo.equipment.EngineerQueryCondition;
import com.welearn.entity.vo.request.common.GenerateAuthResult;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.error.exception.TokenAuthCheckFailedException;
import com.welearn.feign.common.*;
import com.welearn.feign.equipment.EngineerFeignClient;
import com.welearn.feign.storage.QiniuFileFeignClient;
import com.welearn.service.TokenCacheService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description : TOKEN 缓存业务
 * Created by Setsuna Jin on 2018/4/19.
 */
@Slf4j
@Service
public class TokenCacheServiceImpl implements TokenCacheService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Value("${token.jwt-key}")
    private String jwtTokenKey;

    @Value("${token.default.issuer}")
    private String defaultIssuer;

    @Value("${token.default.audience}")
    private String defaultAudience;

    @Value("${token.refresh-token.defaultExpiredSeconds}")
    private Long maxAllowedClock;

    /**
     * 签发 ACCESS_TOKEN 和 REFRESH_TOKEN
     * 每个用户不同客户端只能拥有一个 TokenPackage
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
    @Override
    @CachePut(value = "tokenPackage",key = "'tokenPackage:'+#user.id+':'+#clientType.ordinal()")
    public TokenPackage createTokenPackageCache(User user, UserTypeConst userType, ClientTypeConst clientType, ServiceConst issuer, ServiceConst audience,
                                                int accessTokenExpiredSeconds, int refreshTokenExpiredSeconds, String refreshToken) {
        if (Objects.isNull(issuer))
            issuer = ServiceConst.getByServiceName(defaultIssuer);
        if (Objects.isNull(audience))
            audience = ServiceConst.getByServiceName(defaultAudience);
        DateTime dateTime = DateTime.now();
        String accessToken = Jwts.builder()
                .setIssuer(issuer.getServiceName())
                .setAudience(audience.getServiceName())
                .setExpiration(dateTime.plusSeconds(accessTokenExpiredSeconds).toDate())
                .setSubject(user.getId())
                .claim("utp", userType.ordinal())
                .claim("ctp", clientType.ordinal())
                .claim("ttp", TokenTypeConst.ACCESS_TOKEN.ordinal())
                .signWith(SignatureAlgorithm.HS256, jwtTokenKey)
                .compact();
        if (StringUtils.isBlank(refreshToken))
            refreshToken = Jwts.builder()
                .setIssuer(issuer.getServiceName())
                .setAudience(audience.getServiceName())
                .setExpiration(dateTime.plusSeconds(refreshTokenExpiredSeconds).toDate())
                .setSubject(user.getId())
                .claim("utp", userType.ordinal())
                .claim("ctp", clientType.ordinal())
                .claim("ttp", TokenTypeConst.REFRESH_TOKEN.ordinal())
                .signWith(SignatureAlgorithm.HS256, jwtTokenKey)
                .compact();
        return new TokenPackage(accessToken, refreshToken);
    }

    /**
     * 从缓存中读取 TokenPackage 信息
     * @param user 用户
     * @param clientType 客户端类型
     * @return TokenPackage
     */
    @Override
    @Cacheable(value = "tokenPackage", key = "'tokenPackage:'+#user.id+':'+#clientType.ordinal()")
    public TokenPackage findInTokenPackageCache(User user, ClientTypeConst clientType){
        return null;
    }

    /**
     * 将 Token 字符串转换为 Token 实体
     * @param tokenStr Token 字符串
     * @return Token 实体
     */
    @Override
    @Cacheable(value = "token",key = "'token:'+#tokenStr")
    public Token transform(String tokenStr) {
        try {
            Token token = new Token();
            Claims claims = Jwts.parser().setSigningKey(jwtTokenKey).parseClaimsJws(tokenStr).getBody();
            token.setUser(userFeignClient.select(claims.getSubject()).getData());
            token.setIssuer(ServiceConst.getByServiceName(claims.getIssuer()));
            token.setAudience(ServiceConst.getByServiceName(claims.getAudience()));
            token.setClientTypeConst(ClientTypeConst.values()[claims.get("ctp", Integer.class)]);
            token.setUserTypeConst(UserTypeConst.values()[claims.get("utp", Integer.class)]);
            token.setTokenType(TokenTypeConst.values()[claims.get("ttp", Integer.class)]);
            token.setExpiredAt(claims.getExpiration());
            return token;
        } catch (Exception e){
            throw new TokenAuthCheckFailedException("TokenStr transform to Token failed", e);
        }
    }

    /**
     * 创建 AuthResult 缓存
     * @param user 用户
     * @param clientType 客户端类型
     * @param accessToken ACCESS_TOKEN
     * @return AuthResult
     */
    @Override
    @Cacheable(value = "authCheck", key = "'authCheck:'+#user.id+':'+#clientType.ordinal()")
    public AuthResult createAuthCheckCache(User user, ClientTypeConst clientType, Token accessToken){
        return userFeignClient.generateAuthResult(new GenerateAuthResult(user, clientType.ordinal(), accessToken)).getData();
    }

    /**
     * 更新 AuthResult 缓存
     * @param user 用户
     * @param clientType 客户端类型
     * @param authResult ACCESS_TOKEN
     * @return AuthResult
     */
    @Override
    @CachePut(value = "authCheck", key = "'authCheck:'+#user.id+':'+#clientType.ordinal()")
    public AuthResult updateAuthCheckCache(User user, ClientTypeConst clientType, AuthResult authResult){
        return authResult;
    }

    /**
     * 解析 JWT Token 字符串 在规定的时间内
     * 防止因 JWT 自身的过期时间导致解析失败
     *
     * @param tokenStr JWT Token 字符串
     * @return 解析内容
     */
    @Override
    public Claims parseTokenInAllowedClock(String tokenStr) {
        return Jwts.parser()
                .setAllowedClockSkewSeconds(maxAllowedClock)
                .setSigningKey(jwtTokenKey)
                .parseClaimsJws(tokenStr)
                .getBody();
    }
}
