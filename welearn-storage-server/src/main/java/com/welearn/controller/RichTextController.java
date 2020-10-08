package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.FeignClientGenerator;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.storage.RichText;
import com.welearn.entity.qo.storage.RichTextQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RichTextService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/richText")
public class RichTextController extends BaseController<RichText, RichTextQueryCondition, RichTextService> {
}