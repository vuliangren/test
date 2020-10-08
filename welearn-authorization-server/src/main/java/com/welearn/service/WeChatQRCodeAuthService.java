package com.welearn.service;

import com.welearn.entity.vo.response.authorization.WeChatQRCodeLoginResult;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Description : 微信扫描二维码进行授权认证
 * Created by Setsuna Jin on 2019/4/20.
 */
public interface WeChatQRCodeAuthService extends TokenAuthService {
    WeChatQRCodeLoginResult loginWithCode(@NotBlank String code) ;
}
