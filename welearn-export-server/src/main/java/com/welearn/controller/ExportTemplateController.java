package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.export.ExportTemplate;
import com.welearn.entity.qo.export.ExportTemplateQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.ExportTemplateService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/exportTemplate")
@Api(value = "exportTemplate 接口")
public class ExportTemplateController extends BaseController<ExportTemplate, ExportTemplateQueryCondition, ExportTemplateService>{
}