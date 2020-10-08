package com.welearn.config;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.welearn.error.exception.ProgramCheckFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description :
 * Created by Setsuna Jin on 2019/5/31.
 */
@Slf4j
@Configuration
public class AlinkConfiguration {

    @Value("${aliyun.alink.access.account}")
    private String account;

    @Value("${aliyun.alink.access.uid}")
    private String uid;

    @Value("${aliyun.alink.access.key-id}")
    private String accessKey;

    @Value("${aliyun.alink.access.secret}")
    private String accessSecret;

    @Value("${aliyun.alink.regionId}")
    private String regionId;

    @Bean
    public MessageClient getMessageClient(){
        String endPoint = String.format("https://%s.iot-as-http2.%s.aliyuncs.com", uid, regionId);
        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);
        return MessageClientFactory.messageClient(profile);
    }

    @Bean
    public DefaultAcsClient getDefaultAcsClient() {
        try {
            DefaultProfile.addEndpoint(regionId, regionId, "Iot", String.format("iot.%s.aliyuncs.com", regionId));
            IClientProfile profile = DefaultProfile.getProfile(regionId, accessKey, accessSecret);
            return new DefaultAcsClient(profile);
        } catch (ClientException e) {
            throw new ProgramCheckFailedException( e, "初始化阿里云物联网 云端 SDK 客户端 失败");
        }
    }
}
