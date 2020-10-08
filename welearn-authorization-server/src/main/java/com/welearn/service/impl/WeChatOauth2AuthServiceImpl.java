package com.welearn.service.impl;

import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.dictionary.common.UserTypeConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.service.WeChatOauth2AuthService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/4/28.
 */
public class WeChatOauth2AuthServiceImpl extends AbstractAuthServiceImpl  implements WeChatOauth2AuthService {
    @Override
    public String generateOAuth2Url() {
        return null;
    }

    /**
     * 登录系统
     *
     * @param code            回调得到的 scode
     * @param clientTypeConst 客户端类型
     * @return TokenPackage
     */
    @Override
    public TokenPackage login(String code, ClientTypeConst clientTypeConst) {
        return null;
    }
}
