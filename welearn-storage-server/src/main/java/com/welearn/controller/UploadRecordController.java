package com.welearn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.storage.UploadRecord;
import com.welearn.entity.qo.storage.UploadRecordQueryCondition;
import com.welearn.service.UploadRecordService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/upload")
public class UploadRecordController extends BaseController<UploadRecord, UploadRecordQueryCondition, UploadRecordService>{
}