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

import com.welearn.entity.po.notify.Notice;
import com.welearn.entity.qo.notify.NoticeQueryCondition;
import com.welearn.entity.vo.request.notify.View;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.notify.NoticeInfo;
import com.welearn.service.NoticeService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/notice")
public class NoticeController extends BaseController<Notice, NoticeQueryCondition, NoticeService>{

    @RequestMapping(value = "/sendNotice")
    @ApiOperation(value = "手动发送通知", httpMethod = "POST")
    public CommonResponse sendNotice(@RequestBody Notice notice)  {
        service.sendNotice(notice);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/autoSendNotice")
    @ApiOperation(value = "自动发送通知", httpMethod = "POST")
    public CommonResponse autoSendNotice()  {
        service.autoSendNotice();
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询通知详情", httpMethod = "POST")
    public CommonResponse<List<NoticeInfo>> searchInfo(@RequestBody NoticeQueryCondition noticeQueryCondition)  {
        List<NoticeInfo> result = service.searchInfo(noticeQueryCondition);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/view")
    @ApiOperation(value = "浏览通知", httpMethod = "POST")
    public CommonResponse view(@RequestBody View view)  {
        service.view(view.getNoticeId(), view.getUserId());
        return CommonResponse.getSuccessResponse();
    }
}