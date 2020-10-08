package com.welearn.controller;

import com.welearn.application.ApplicationController;
import com.welearn.entity.po.apply.Certificate;
import com.welearn.entity.qo.apply.CertificateQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.CertificateHandlerService;
import com.welearn.service.CertificateService;
import com.welearn.util.PaginateUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description : 证书管理
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/certificate")
public class CertificateController extends ApplicationController<Certificate, CertificateHandlerService> {

    @Autowired
    CertificateService certificateService;

    @RequestMapping(value = "/select")
    @ApiOperation(value = "数据单个查询", httpMethod = "POST")
    public CommonResponse<Certificate> select(@RequestBody String id) {
        return new CommonResponse<>(certificateService.select(id));
    }


    @RequestMapping(value = "/disable")
    @ApiOperation(value = "数据禁用", httpMethod = "POST")
    public CommonResponse disable(@RequestBody String id) {
        certificateService.disable(id);
        return CommonResponse.getSuccessResponse();
    }


    @RequestMapping(value = "/search")
    @ApiOperation(value = "数据条件查询", httpMethod = "POST")
    public CommonResponse<List<Certificate>> search(@RequestBody CertificateQueryCondition queryCondition) {
        return new CommonResponse<>(PaginateUtil.page(() -> certificateService.search(queryCondition)));
    }
}