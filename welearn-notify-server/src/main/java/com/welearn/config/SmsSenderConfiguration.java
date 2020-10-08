package com.welearn.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description : 构建短信发送的阿里云客户端
 * Created by Setsuna Jin on 2019/3/18.
 */
@Slf4j
@Configuration
public class SmsSenderConfiguration {
    @Value("${aliyun.sms.region-id}")
    private String regionId;

    @Value("${aliyun.sms.access.account}")
    private String accessCount;

    @Value("${aliyun.sms.access.key-id}")
    private String accessKeyId;

    @Value("${aliyun.sms.access.secret}")
    private String accessSecret;


    protected DefaultProfile getProfile(){
        return DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
    }

    @Bean
    public IAcsClient getSmsSendClient(){
        log.info("初始化阿里云短信服务配置: {}", accessCount);
        return new DefaultAcsClient(getProfile());
    }
}
