package com.welearn.cache.evict;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.po.common.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/25.
 */
@Slf4j
@Component
public class AuthorizationCacheEvictor {
    /**
     * 从缓存中删除 TokenPackage 信息
     * @param user 用户
     * @param clientType 客户端类型
     */
    @CacheEvict(value = "tokenPackage", key = "'tokenPackage:'+#user.id+':'+#clientType.ordinal()")
    public void deleteTokenPackageCache(User user, ClientTypeConst clientType){
        log.debug("deleteTokenPackageCache:{}", user.getId());
    }

    /**
     * 删除 将 AccessToken 字符串转换为 AccessToken 实体的缓存
     * @param tokenStr AccessToken 字符串
     */
    @CacheEvict(value = "token",key = "'token:'+#tokenStr")
    public void deleteTokenTransformCache(String tokenStr){
        log.debug("deleteTokenTransformCache:{}", tokenStr);
    }

    /**
     * 清除 AuthResult 缓存
     * @param user 用户
     * @param clientType 客户端类型
     */
    @CacheEvict(value = "authCheck", key = "'authCheck:'+#user.id+':'+#clientType.ordinal()")
    public void deleteAuthCheckCache(User user, ClientTypeConst clientType){
        log.debug("deleteAuthCheckCache:{}", user.getId());
    }
}
