package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.storage.SignatureRecord;
import com.welearn.entity.qo.storage.SignatureRecordQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.SignatureRecordService;

/**
 * Description : 签字记录
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/signatureRecord")
@Api(value = "signatureRecord 接口")
public class SignatureRecordController extends BaseController<SignatureRecord, SignatureRecordQueryCondition, SignatureRecordService>{
}