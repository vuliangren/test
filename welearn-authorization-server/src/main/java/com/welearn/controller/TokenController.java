package com.welearn.controller;

import com.welearn.entity.vo.request.authorization.AccessToken;
import com.welearn.entity.vo.request.authorization.RefreshToken;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.TokenAuthService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@Validated
@RequestMapping("/token")
@RestController
public class TokenController {

    @Autowired
    @Qualifier("abstractAuthServiceImpl")
    private TokenAuthService tokenAuthService;

    @RequestMapping("/refresh")
    public CommonResponse<TokenPackage> refresh(@RequestBody @Valid RefreshToken refreshToken){
        TokenPackage result = tokenAuthService.refresh(refreshToken.getRefreshToken());
        return new CommonResponse<>(result);
    }

    @RequestMapping("/check")
    public CommonResponse<AuthResult> check(@RequestBody @Valid AccessToken accessToken){
        AuthResult result = tokenAuthService.check(accessToken.getAccessToken());
        return new CommonResponse<>(result);
    }

    @RequestMapping("/invalid")
    public CommonResponse invalid(@RequestBody @Valid AccessToken accessToken){
        tokenAuthService.invalid(accessToken.getAccessToken());
        return CommonResponse.getSuccessResponse();
    }
}
