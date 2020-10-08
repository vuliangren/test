package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.common.WechatAppUser;
import com.welearn.entity.qo.common.WechatAppUserQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-common-service / com.welearn.controller.WechatAppUserController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface WechatAppUserFeignClient {

    @RequestMapping(value = "/wechatAppUser/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/wechatAppUser/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/wechatAppUser/update")
    CommonResponse update(WechatAppUser entity);

    @RequestMapping(value = "/wechatAppUser/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/wechatAppUser/create")
    CommonResponse<WechatAppUser> create(WechatAppUser entity);

    @RequestMapping(value = "/wechatAppUser/search")
    CommonResponse<List<WechatAppUser>> search(WechatAppUserQueryCondition queryCondition);

    @RequestMapping(value = "/wechatAppUser/select")
    CommonResponse<WechatAppUser> select(String id);
}