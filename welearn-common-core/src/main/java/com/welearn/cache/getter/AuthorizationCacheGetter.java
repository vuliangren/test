package com.welearn.cache.getter;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.error.exception.TokenAuthCheckFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/25.
 */
@Slf4j
@Component
public class AuthorizationCacheGetter {

    /**
     * 读取 AuthResult 缓存
     * @param user 用户
     * @param clientType 客户端类型
     */
    @Cacheable(value = "authCheck", key = "'authCheck:'+#user.id+':'+#clientType.ordinal()")
    public AuthResult getAuthCheckCache(User user, ClientTypeConst clientType){
        throw new TokenAuthCheckFailedException("getAuthCheckCache, userId:%s, client:%s", user.getId(), clientType.name());
    }

    /**
     * 读取 AuthResult 缓存
     * @param key 缓存key值: authCheck:%s:%d
     */
    @Cacheable(value = "authCheck", key = "#key")
    public AuthResult getAuthCheckCache(String key){
        throw new TokenAuthCheckFailedException("getAuthCheckCache, key:%s, client:%s", key);
    }
}
