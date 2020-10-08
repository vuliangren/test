package com.welearn.service;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/23.
 */
@Validated
public interface RouteCacheService {

    /**
     * 路由 AccessToken 认证缓存
     * @param accessToken ACCESS_TOKEN
     * @return AuthResult JSON
     */
    String routeAuthCache(@NotBlank String accessToken);

    /**
     * 获取 token 对应的权限信息
     * @param routeAuthCacheKey 内部认证缓存的 KEY
     * @return 权限列表
     */
    Set<String> permissions(String routeAuthCacheKey);
}
