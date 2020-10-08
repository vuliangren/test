package com.welearn.service;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.po.common.User;

/**
 * Description : 清除 authCheck, routeAuthCache 缓存
 * Created by Setsuna Jin on 2018/4/24.
 */
public interface CacheCleanService {

    /**
     * 清除 AuthResult 缓存
     * @param user 用户
     */
    void deleteAuthCheckCache(User user);

    /**
     * 清除当前用户的认证相关 缓存
     */
    void deleteCurrentUserAuthCheckCache();

    /**
     * 清除 route 服务中的 AuthCheckResult 缓存
     * @param accessToken ACCESS_TOKEN
     */
    void deleteRouteAuthCache(String accessToken);
}
