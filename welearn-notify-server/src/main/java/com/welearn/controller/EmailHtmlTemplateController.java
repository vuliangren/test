package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.notify.EmailHtmlTemplate;
import com.welearn.entity.qo.notify.EmailHtmlTemplateQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.EmailHtmlTemplateService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/emailHtmlTemplate")
public class EmailHtmlTemplateController extends BaseController<EmailHtmlTemplate, EmailHtmlTemplateQueryCondition, EmailHtmlTemplateService>{
}