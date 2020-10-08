package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.notify.NoticeRecord;
import com.welearn.generator.ControllerGenerator;
import com.welearn.service.NoticeRecordService;
import com.welearn.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.notify.Feedback;
import com.welearn.entity.qo.notify.FeedbackQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.FeedbackService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/feedback")
public class FeedbackController extends BaseController<Feedback, FeedbackQueryCondition, FeedbackService>{
}