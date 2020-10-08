package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.notify.SmsSendTemplate;
import com.welearn.entity.qo.notify.SmsSendTemplateQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.SmsSendTemplateService;
import java.lang.String;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/smsSendTemplate")
@Api(value = "smsSendTemplate 接口")
public class SmsSendTemplateController extends BaseController<SmsSendTemplate, SmsSendTemplateQueryCondition, SmsSendTemplateService>{

}