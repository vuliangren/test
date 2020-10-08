package com.welearn.controller;

import com.welearn.entity.vo.response.common.TagInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.Tag;
import com.welearn.entity.qo.common.TagQueryCondition;
import com.welearn.entity.vo.request.common.TypeTags;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.TagService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/tag")
@Api(value = "tag 接口")
public class TagController extends BaseController<Tag, TagQueryCondition, TagService>{

    @RequestMapping(value = "/itemTags")
    @ApiOperation(value = "查询数据项所关联的标签", httpMethod = "POST")
    public CommonResponse<List<TagInfo>> itemTags(@RequestBody String itemId)  {
        List<TagInfo> result = service.itemTags(itemId);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/typeTags")
    @ApiOperation(value = "查询数据项类型可用标签", httpMethod = "POST")
    public CommonResponse<List<Tag>> typeTags(@RequestBody TypeTags typeTags)  {
        List<Tag> result = service.typeTags(typeTags.getItemId(), typeTags.getCompanyId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/itemIds")
    @ApiOperation(value = "查询多个标签关联的 数据Id 的去重并集", httpMethod = "POST")
    public CommonResponse<List<String>> itemIds(@RequestBody List<String> tagIds)  {
        List<String> result = service.itemIds(tagIds);
        return new CommonResponse<>(result);
    }
}