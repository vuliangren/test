package com.welearn.controller;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.entity.vo.request.authorization.AccessToken;
import com.welearn.entity.vo.request.authorization.PasswordAuth;
import com.welearn.entity.vo.request.authorization.WeChatAppBind;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.authorization.WeChatAppLoginResult;
import com.welearn.entity.vo.response.authorization.WeChatQRCodeLoginResult;
import com.welearn.service.PasswordAuthService;
import com.welearn.service.WeChatAppAuthService;
import com.welearn.service.WeChatQRCodeAuthService;
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
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private PasswordAuthService passwordAuthService;

    @Autowired
    private WeChatAppAuthService weChatAppAuthService;

    @Autowired
    private WeChatQRCodeAuthService weChatQRCodeAuthService;

    @RequestMapping("/passwordAuth")
    public CommonResponse<TokenPackage> passwordAuth(@RequestBody @Valid PasswordAuth passwordAuth){
        // 先清理旧的认证信息
        TokenPackage result = passwordAuthService.login(
                passwordAuth.getPasswordLogin(),
                ClientTypeConst.values()[passwordAuth.getClientType()]);
        return new CommonResponse<>(result);
    }

    @RequestMapping("/wechatAppAuth")
    public CommonResponse<WeChatAppLoginResult> wechatAppAuth(@RequestBody String code) throws WeixinException {
        return new CommonResponse<>(weChatAppAuthService.loginWithCode(code));
    }

    @RequestMapping("/wechatQRCodeAuth")
    public CommonResponse<WeChatQRCodeLoginResult> wechatQRCodeAuth(@RequestBody String code) {
        return new CommonResponse<>(weChatQRCodeAuthService.loginWithCode(code));
    }
}
