package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.notify.NoticeRecord;
import com.welearn.entity.qo.notify.NoticeRecordQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.NoticeRecordService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/noticeRecord")
public class NoticeRecordController extends BaseController<NoticeRecord, NoticeRecordQueryCondition, NoticeRecordService>{
}