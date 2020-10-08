package com.welearn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.wxa.WXBizDataCrypt;
import com.foxinmy.weixin4j.wxa.WeixinAppFacade;
import com.foxinmy.weixin4j.wxa.model.Session;
import com.welearn.dictionary.common.ClientTypeConst;
import com.welearn.dictionary.common.UserLockStatusConst;
import com.welearn.dictionary.common.UserTypeConst;
import com.welearn.dictionary.common.UserWeChatStatusConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.common.WechatAppUser;
import com.welearn.entity.qo.common.WechatAppUserQueryCondition;
import com.welearn.entity.vo.request.authorization.AccessToken;
import com.welearn.entity.vo.request.common.PasswordLogin;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.entity.vo.response.authorization.TokenPackage;
import com.welearn.entity.vo.response.authorization.WeChatAppLoginResult;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.error.exception.UserAuthCheckFailedException;
import com.welearn.feign.common.UserFeignClient;
import com.welearn.feign.common.WechatAppUserFeignClient;
import com.welearn.service.WeChatAppAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2018/12/22.
 */
@Slf4j
@Service
public class WeChatAppAuthServiceImpl extends AbstractAuthServiceImpl implements WeChatAppAuthService {


    @Value("${wechat.app.shebei120.id}")
    private String appId;

    @Autowired
    private WeixinAppFacade weixinAppFacade;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private WechatAppUserFeignClient wechatAppUserFeignClient;

    @Override
    public WeChatAppLoginResult loginWithCode(String code) throws WeixinException {
        WeChatAppLoginResult loginResult = new WeChatAppLoginResult();
        WechatAppUser wechatAppUser;
        Session session = weixinAppFacade.getLoginApi().jscode2session(code);
        WechatAppUserQueryCondition condition = new WechatAppUserQueryCondition();
        condition.setOpenId(session.getOpenId());
        condition.setIsEnable(true);
        List<WechatAppUser> wechatAppUsers = wechatAppUserFeignClient.search(condition).getData();
        if (Objects.isNull(wechatAppUsers) || wechatAppUsers.isEmpty()){
            wechatAppUser = new WechatAppUser();
            wechatAppUser.setOpenId(session.getOpenId());
            wechatAppUser.setSessionKey(session.getSessionKey());
            wechatAppUser.setUnionId(session.getUnionId());
            wechatAppUserFeignClient.create(wechatAppUser);
        } else if (wechatAppUsers.size() == 1){
            wechatAppUser = wechatAppUsers.get(0);
            wechatAppUser.setSessionKey(session.getSessionKey());
            wechatAppUser.setSessionKeyCreatedAt(new Date());
            wechatAppUserFeignClient.update(wechatAppUser);
        } else {
            throw new BusinessVerifyFailedException("用户 appId: %s 存在多个 WechatAppUser 信息", session.getOpenId());
        }
        // 构建微信小程序登录结果
        loginResult.setOpenId(wechatAppUser.getOpenId());
        if (StringUtils.isBlank(wechatAppUser.getUserId())){
            loginResult.setIsBind(false);
            return loginResult;
        } else {
            loginResult.setIsBind(true);
            // 授权 产生 TOKEN
            User user = userFeignClient.select(wechatAppUser.getUserId()).getData();
            if (user.getIsEnable() && user.getLockStatus() == UserLockStatusConst.UNLOCK.ordinal()){
                TokenPackage tokenPackage = this.sign(user, UserTypeConst.ANY, ClientTypeConst.WECHAT_APP, null, null);
                loginResult.setAccessToken(tokenPackage.getAccessToken());
                loginResult.setRefreshToken(tokenPackage.getRefreshToken());
            }
            return loginResult;
        }
    }

    /**
     * 系统内部使用不对外开放接口
     * @param user 用户
     * @param openId openId
     * @return WeChatAppLoginResult
     */
    @Override
    public WeChatAppLoginResult bind(User user, String openId) {
        WeChatAppLoginResult loginResult = new WeChatAppLoginResult();
        loginResult.setOpenId(openId);
        loginResult.setIsBind(false);

        WechatAppUser wechatAppUser = null;
        // 检查当前 openId 是否已经绑定过用户
        WechatAppUserQueryCondition openIdCondition = new WechatAppUserQueryCondition();
        openIdCondition.setOpenId(openId);
        openIdCondition.setIsEnable(true);
        List<WechatAppUser> wechatAppUsers = wechatAppUserFeignClient.search(openIdCondition).getData();
        if (Objects.nonNull(wechatAppUsers) && wechatAppUsers.size() == 1){
            wechatAppUser = wechatAppUsers.get(0);
            if (StringUtils.isNotBlank(wechatAppUser.getUserId()))
                throw new BusinessVerifyFailedException("该微信已经绑定过账号, 请先解绑后再进行操作");
        } else {
            throw new BusinessVerifyFailedException("无相关微信用户信息, 请重新绑定");
        }
        if (Objects.isNull(user))
            throw new UserAuthCheckFailedException("账户或密码错误, 账户绑定失败");
        // 检查当前 userId 是否已经绑定过 小程序
        WechatAppUserQueryCondition userIdCondition = new WechatAppUserQueryCondition();
        userIdCondition.setUserId(user.getId());
        userIdCondition.setIsEnable(true);
        wechatAppUsers = wechatAppUserFeignClient.search(userIdCondition).getData();
        if (Objects.nonNull(wechatAppUsers) && !wechatAppUsers.isEmpty())
            throw new BusinessVerifyFailedException("该用户已经绑定过微信小程序, 请先解绑后再进行操作");

        if (user.getIsEnable() && user.getLockStatus() == UserLockStatusConst.UNLOCK.ordinal()){
            // 创建关联信息
            wechatAppUser.setUserId(user.getId());
            wechatAppUserFeignClient.update(wechatAppUser);
            // 更新用户绑定状态
            user.setWechatStatus(UserWeChatStatusConst.BIND.ordinal());
            userFeignClient.update(user);
            // 授权TOKEN
            TokenPackage tokenPackage = this.sign(user, UserTypeConst.ANY, ClientTypeConst.WECHAT_APP, null, null);
            loginResult.setAccessToken(tokenPackage.getAccessToken());
            loginResult.setRefreshToken(tokenPackage.getRefreshToken());
            loginResult.setIsBind(true);
        }
        return loginResult;
    }

    @Override
    public WeChatAppLoginResult bindWithPassword(PasswordLogin passwordLogin, String openId){
        return this.bind(userFeignClient.passwordLogin(passwordLogin).getData(), openId);
    }

    @Override
    public void unbindWithAccessToken(AccessToken accessToken){
        AuthResult authResult = this.check(accessToken.getAccessToken());
        if (Objects.isNull(authResult) || Objects.isNull(authResult.getAccessToken()))
            throw new UserAuthCheckFailedException("accessToken 无效");
        String userId = authResult.getAccessToken().getUser().getId();
        WechatAppUserQueryCondition condition = new WechatAppUserQueryCondition();
        condition.setUserId(userId);
        condition.setIsEnable(true);
        List<WechatAppUser> wechatAppUsers = wechatAppUserFeignClient.search(condition).getData();
        if (Objects.isNull(wechatAppUsers) || wechatAppUsers.isEmpty())
            throw new BusinessVerifyFailedException("该用户未绑定过微信小程序");
        // 删除微信小程序用户信息里的user关联
        for (WechatAppUser wechatAppUser : wechatAppUsers) {
            wechatAppUser.setUserId("");
            wechatAppUserFeignClient.update(wechatAppUser);
        }
        // 更新用户的微信小程序绑定状态
        User user = userFeignClient.select(userId).getData();
        // TODO: 待商榷
        user.setWechatStatus(UserWeChatStatusConst.UN_SUBSCRIPT.ordinal());
        userFeignClient.update(user);
        // 将当前的 TOKEN 无效化
        this.invalid(accessToken.getAccessToken());
    }

    @Override
    public void updateWechatAppUserInfo(String openId, String encryptedData, String iv){
        if (StringUtils.isBlank(openId))
            throw new BusinessVerifyFailedException("wechatAppUser.openId 非法");
        WechatAppUserQueryCondition condition = new WechatAppUserQueryCondition();
        condition.setOpenId(openId);
        condition.setIsEnable(true);
        List<WechatAppUser> wechatAppUsers = wechatAppUserFeignClient.search(condition).getData();
        if (Objects.isNull(wechatAppUsers) || wechatAppUsers.size() != 1)
            throw new BusinessVerifyFailedException("wechatAppUser.openId 非法");
        WechatAppUser appUser = wechatAppUsers.get(0);
        // 解析加密数据
        WXBizDataCrypt wxBizDataCrypt = new WXBizDataCrypt(appId, appUser.getSessionKey());
        JSONObject data = wxBizDataCrypt.decryptData(encryptedData, iv);
        // 更新小程序信息
        appUser.setAvatarUrl(data.getString("avatarUrl"));
        appUser.setCity(data.getString("city"));
        appUser.setCountry(data.getString("country"));
        appUser.setGender(data.getInteger("gender"));
        appUser.setNickName(data.getString("nickName"));
        appUser.setProvince(data.getString("province"));
        appUser.setUnionId(data.getString("unionId"));
        // 判断是否更新用户头像
        if (StringUtils.isNotBlank(appUser.getUserId())) {
            User user = userFeignClient.select(appUser.getUserId()).getData();
            // 用户 头像为空 或是 微信头像 则更新
            if (StringUtils.isBlank(user.getHeadImageUrl()) || user.getHeadImageUrl().startsWith("https://wx.qlogo.cn")){
                user.setHeadImageUrl(appUser.getAvatarUrl());
                userFeignClient.update(user);
            }
        }
        wechatAppUserFeignClient.update(appUser);
    }
}
