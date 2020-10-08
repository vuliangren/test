package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.common.Tag;
import com.welearn.entity.qo.common.TagQueryCondition;
import com.welearn.entity.vo.request.common.TypeTags;
import com.welearn.entity.vo.response.CommonResponse;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-common-service / com.welearn.controller.TagController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface TagFeignClient {

    @RequestMapping(value = "/tag/itemTags")
    CommonResponse<List<Tag>> itemTags(String itemId);

    @RequestMapping(value = "/tag/itemIds")
    CommonResponse<List<String>> itemIds(List<String> tagIds);

    @RequestMapping(value = "/tag/typeTags")
    CommonResponse<List<Tag>> typeTags(TypeTags typeTags);

    @RequestMapping(value = "/tag/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/tag/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/tag/update")
    CommonResponse update(Tag entity);

    @RequestMapping(value = "/tag/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/tag/create")
    CommonResponse<Tag> create(Tag entity);

    @RequestMapping(value = "/tag/search")
    CommonResponse<List<Tag>> search(TagQueryCondition queryCondition);

    @RequestMapping(value = "/tag/select")
    CommonResponse<Tag> select(String id);
}