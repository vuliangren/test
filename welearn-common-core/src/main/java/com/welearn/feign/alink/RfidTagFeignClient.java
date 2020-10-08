package com.welearn.feign.alink;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.alink.RfidTag;
import com.welearn.entity.qo.alink.RfidTagQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-alink-service / com.welearn.controller.RfidTagController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-alink-server", configuration = FeignConfiguration.class)
public interface RfidTagFeignClient {

    @RequestMapping(value = "/rfidTag/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/rfidTag/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/rfidTag/update")
    CommonResponse update(RfidTag entity);

    @RequestMapping(value = "/rfidTag/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/rfidTag/create")
    CommonResponse<RfidTag> create(RfidTag entity);

    @RequestMapping(value = "/rfidTag/search")
    CommonResponse<List<RfidTag>> search(RfidTagQueryCondition queryCondition);

    @RequestMapping(value = "/rfidTag/select")
    CommonResponse<RfidTag> select(String id);
}