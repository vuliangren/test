package com.welearn.feign.storage;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.storage.RichText;
import com.welearn.entity.qo.storage.RichTextQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-storage-service / com.welearn.controller.RichTextController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-storage-server", configuration = FeignConfiguration.class)
public interface RichTextFeignClient {

    @RequestMapping(value = "/richText/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/richText/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/richText/update")
    CommonResponse update(RichText entity);

    @RequestMapping(value = "/richText/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/richText/create")
    CommonResponse<RichText> create(RichText entity);

    @RequestMapping(value = "/richText/search")
    CommonResponse<List<RichText>> search(RichTextQueryCondition queryCondition);

    @RequestMapping(value = "/richText/select")
    CommonResponse<RichText> select(String id);
}