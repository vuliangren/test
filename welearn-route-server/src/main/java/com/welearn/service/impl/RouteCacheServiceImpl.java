package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.entity.vo.request.authorization.AccessToken;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.feign.authorization.TokenFeignClient;
import com.welearn.service.RouteCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/23.
 */
@Slf4j
@Service
public class RouteCacheServiceImpl implements RouteCacheService {

    @Autowired
    private TokenFeignClient tokenFeignClient;

    // 用户拥有的权限列表缓存数据
    private static final ConcurrentHashMap<String, HashSet<String>> PERMISSIONS_CACHE = new ConcurrentHashMap<>();

    /**
     * 路由 AccessToken 认证缓存
     *
     * @param accessToken ACCESS_TOKEN 
     * @return AuthResult JSON
     */
    @Override
    @Cacheable(cacheNames = "routeAuthCache", key = "'routeAuthCache:'+#accessToken")
    public String routeAuthCache(String accessToken) {
        AuthResult result = tokenFeignClient.check(new AccessToken(accessToken)).getData();
        // 得到用户内部认证缓存的 KEY
        String routeAuthCacheKey = String.format("authCheck:%s:%s", result.getAccessToken().getUser().getId(), result.getAccessToken().getClientTypeConst().ordinal());
        // 缓存用户的权限列表数据
        PERMISSIONS_CACHE.put(routeAuthCacheKey, new HashSet<>(result.getPermissionCodeList()));
        return routeAuthCacheKey;
    }

    /**
     * 获取 token 对应的权限信息
     * @param routeAuthCacheKey 内部认证缓存的 KEY
     * @return 权限列表
     */
    @Override
    public Set<String> permissions(String routeAuthCacheKey) {
        return PERMISSIONS_CACHE.get(routeAuthCacheKey);
    }
}
