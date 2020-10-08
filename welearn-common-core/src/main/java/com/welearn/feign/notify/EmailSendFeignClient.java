package com.welearn.feign.notify;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.vo.request.notify.SendHtml;
import com.welearn.entity.vo.request.notify.SendHtmlTemplate;
import com.welearn.entity.vo.request.notify.SendHtmlTemplates;
import com.welearn.entity.vo.request.notify.SendHtmls;
import com.welearn.entity.vo.response.CommonResponse;

/**
 * Description : welearn-notify-service / com.welearn.controller.EmailSendController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-notify-server", configuration = FeignConfiguration.class)
public interface EmailSendFeignClient {

    @RequestMapping(value = "/emailSend/forUser")
    CommonResponse forUser(SendHtml sendHtml);

    @RequestMapping(value = "/emailSend/forUsers")
    CommonResponse forUsers(SendHtmls sendHtmls);

    @RequestMapping(value = "/emailSend/forUsersUseTemplate")
    CommonResponse forUsersUseTemplate(SendHtmlTemplates sendHtmlTemplates);

    @RequestMapping(value = "/emailSend/forUserUseTemplate")
    CommonResponse forUserUseTemplate(SendHtmlTemplate sendHtmlTemplate);
}