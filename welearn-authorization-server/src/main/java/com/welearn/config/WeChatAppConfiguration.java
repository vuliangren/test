package com.welearn.config;

import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.wxa.WeixinAppFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description : 
 * Created by Setsuna Jin on 2018/12/22.
 */
@Configuration
public class WeChatAppConfiguration {
    @Value("${wechat.app.shebei120.id}")
    private String appId;
    @Value("${wechat.app.shebei120.secret}")
    private String appSecret;

    @Value("${wechat.web.shebei120.id}")
    private String webId;
    @Value("${wechat.web.shebei120.secret}")
    private String webSecret;


    @Bean
    public WeixinAppFacade weixinAppFacade(){
        WeixinAccount account = new WeixinAccount(appId, appSecret);
        return new WeixinAppFacade(account);
    }

    @Bean
    public OauthApi weixinWebOauthApi(){
        return new OauthApi(new WeixinAccount(webId, webSecret));
    }
}
