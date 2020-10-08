package com.welearn.service.impl;

import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.mp.model.OauthToken;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.dictionary.common.UserLockStatusConst;
import com.welearn.dictionary.common.UserTypeConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.common.WechatAppUser;
import com.welearn.entity.qo.common.WechatAppUserQueryCondition;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.vo.response.authorization.WeChatQRCodeLoginResult;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.UserFeignClient;
import com.welearn.feign.common.WechatAppUserFeignClient;
import com.welearn.service.WeChatQRCodeAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description : 微信扫描二维码进行授权认证
 * Created by Setsuna Jin on 2019/4/24.
 */
@Slf4j
@Service
public class WeChatQRCodeAuthServiceImpl extends AbstractAuthServiceImpl implements WeChatQRCodeAuthService {

    @Autowired
    private OauthApi oauthApi;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private WechatAppUserFeignClient wechatAppUserFeignClient;

    @Override
    public WeChatQRCodeLoginResult loginWithCode(String code) {
        WeChatQRCodeLoginResult result = new WeChatQRCodeLoginResult();
        OauthToken token;
        try {
            token = oauthApi.getAuthorizationToken(code);
            if (Objects.isNull(token) || StringUtils.isBlank(token.getOpenId()))
                throw new Exception();
            result.setOpenId(token.getOpenId());
            result.setUnionId(token.getUnionId());
        } catch (Exception e) {
            log.error("微信授权临时票据无效", e);
            throw new BusinessVerifyFailedException("微信授权临时票据无效");
        }
        // 查询 unionId 是否对应用户
        WechatAppUserQueryCondition condition = new WechatAppUserQueryCondition();
        condition.setUnionId(token.getUnionId());
        condition.setIsEnable(true);
        List<WechatAppUser> wechatAppUsers = wechatAppUserFeignClient.search(condition).getData();
        if (Objects.isNull(wechatAppUsers) || wechatAppUsers.isEmpty()){
            // 用户未绑定过账户
            throw new BusinessVerifyFailedException("该微信未绑定过系统账户,无法扫码登录");
        } else {
            WechatAppUser wechatAppUser = wechatAppUsers.get(0);
            if (StringUtils.isBlank(wechatAppUser.getUserId())){
                throw new BusinessVerifyFailedException("账户绑定信息异常, 请联系客服");
            } else {
                // 授权 产生 TOKEN
                User user = userFeignClient.select(wechatAppUser.getUserId()).getData();
                if (user.getIsEnable() && user.getLockStatus() == UserLockStatusConst.UNLOCK.ordinal()){
                    TokenPackage tokenPackage = this.sign(user, UserTypeConst.ANY, ClientTypeConst.WEB, null, null);
                    result.setAccessToken(tokenPackage.getAccessToken());
                    result.setRefreshToken(tokenPackage.getRefreshToken());
                } else {
                    throw new BusinessVerifyFailedException("账户已被禁用或锁定, 无法登录");
                }
            }
        }
        return result;
    }
}
