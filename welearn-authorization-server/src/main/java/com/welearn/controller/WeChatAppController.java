package com.welearn.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.po.common.WechatAppUser;
import com.welearn.entity.vo.request.authorization.AccessToken;
import com.welearn.entity.vo.request.authorization.PasswordAuth;
import com.welearn.entity.vo.request.authorization.WeChatAppBind;
import com.welearn.entity.vo.request.authorization.WeChatAppUpdate;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.vo.response.authorization.WeChatAppLoginResult;
import com.welearn.service.PasswordAuthService;
import com.welearn.service.WeChatAppAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/wechatApp")
@RestController
public class WeChatAppController {

    @Autowired
    private WeChatAppAuthService weChatAppAuthService;

    @RequestMapping("/bind")
    public CommonResponse<WeChatAppLoginResult> wechatAppBind(@RequestBody WeChatAppBind weChatAppBind) {
        return new CommonResponse<>(weChatAppAuthService.bindWithPassword(weChatAppBind.getPasswordLogin(), weChatAppBind.getOpenId()));
    }

    @RequestMapping("/unbind")
    public CommonResponse wechatAppUnbind(@RequestBody AccessToken accessToken) {
        weChatAppAuthService.unbindWithAccessToken(accessToken);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping("/update")
    public CommonResponse updateWechatAppUserInfo(@RequestBody WeChatAppUpdate weChatAppUpdate) {
        weChatAppAuthService.updateWechatAppUserInfo(weChatAppUpdate.getOpenId(), weChatAppUpdate.getEncryptedData(), weChatAppUpdate.getIv());
        return CommonResponse.getSuccessResponse();
    }
}
