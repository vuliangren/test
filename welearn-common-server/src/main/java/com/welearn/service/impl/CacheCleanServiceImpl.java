package com.welearn.service.impl;

import com.welearn.cache.evict.AuthorizationCacheEvictor;
import com.welearn.cache.evict.RouteCacheEvictor;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.service.CacheCleanService;
import com.welearn.util.GlobalContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/25.
 */
@Slf4j
@Service
public class CacheCleanServiceImpl implements CacheCleanService {

    @Autowired
    private AuthorizationCacheEvictor authorizationCacheEvictor;

    @Autowired
    private RouteCacheEvictor routeCacheEvictor;

    /**
     * 清除 AuthResult 缓存
     *
     * @param user 用户
     */
    @Override
    public void deleteAuthCheckCache(User user) {
        for (ClientTypeConst clientTypeConst : ClientTypeConst.values()){
            authorizationCacheEvictor.deleteAuthCheckCache(user, clientTypeConst);
        }
    }

    /**
     * 清除当前用户的认证相关 缓存
     */
    @Override
    public void deleteCurrentUserAuthCheckCache() {
        try {
            AuthResult authResult = GlobalContextUtil.getAuthResult();
            deleteAuthCheckCache(authResult.getAccessToken().getUser());
            deleteRouteAuthCache(GlobalContextUtil.getVedaToken());
        } catch (Exception e) {
            log.error("delete request user auth cache failed:{}", e.getMessage());
        }
    }

    /**
     * 清除 route 服务中的 AuthCheckResult 缓存
     *
     * @param accessToken ACCESS_TOKEN
     */
    @Override
    public void deleteRouteAuthCache(String accessToken) {
        routeCacheEvictor.deleteRouteAuthCache(accessToken);
    }


}

