package com.welearn.controller;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.vo.request.notify.SmsReport;
import com.welearn.entity.vo.response.notify.SendReportResponse;
import com.welearn.generator.ControllerGenerator;
import com.welearn.service.SmsSendRecordService;
import com.welearn.service.SmsSendService;
import com.welearn.service.SmsSendTemplateService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/18.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/public")
public class PublicController {

    @Autowired
    private SmsSendService smsSendService;

    @RequestMapping(value = "/smsReport")
    @ApiOperation(value = "阿里云短信发送结果回调", httpMethod = "POST")
    public SendReportResponse smsSendCallback(@RequestBody List<SmsReport> smsReports){
        return smsSendService.report(smsReports);
    }

}
