package com.welearn.feign.authorization;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.vo.request.authorization.AccessToken;
import com.welearn.entity.vo.request.authorization.RefreshToken;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description : welearn-common-service / com.welearn.controller.TokenController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-authorization-server", configuration = FeignConfiguration.class)
public interface TokenFeignClient {

    @RequestMapping(value = "/token/refresh")
    CommonResponse<TokenPackage> refresh(RefreshToken refreshToken);

    @RequestMapping(value = "/token/check")
    CommonResponse<AuthResult> check(AccessToken accessToken);

    @RequestMapping(value = "/token/invalid")
    CommonResponse invalid(AccessToken accessToken);
}