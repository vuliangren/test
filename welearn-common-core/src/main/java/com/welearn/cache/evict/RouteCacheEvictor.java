package com.welearn.cache.evict;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/25.
 */
@Slf4j
@Component
public class RouteCacheEvictor {

    /**
     * 清除 route 服务中的 AuthCheckResult 缓存
     * @param accessToken ACCESS_TOKEN
     */
    @CacheEvict(value = "routeAuthCache", key = "'routeAuthCache:'+#accessToken")
    public void deleteRouteAuthCache(String accessToken){
        log.debug("deleteRouteAuthCache:{}", accessToken);
    }
}
