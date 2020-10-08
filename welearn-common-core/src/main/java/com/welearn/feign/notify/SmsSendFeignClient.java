package com.welearn.feign.notify;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.vo.request.notify.SendForPhone;
import com.welearn.entity.vo.request.notify.SendForUser;
import com.welearn.entity.vo.response.CommonResponse;

/**
 * Description : welearn-notify-service / com.welearn.controller.SmsSendController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-notify-server", configuration = FeignConfiguration.class)
public interface SmsSendFeignClient {

    @RequestMapping(value = "/smsSend/sendForPhone")
    CommonResponse sendForPhone(SendForPhone sendForPhone);

    @RequestMapping(value = "/smsSend/sendForUser")
    CommonResponse sendForUser(SendForUser sendForUser);

}