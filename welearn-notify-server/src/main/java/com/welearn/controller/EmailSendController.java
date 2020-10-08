package com.welearn.controller;

import com.welearn.entity.vo.request.notify.SendHtmlTemplate;
import com.welearn.entity.vo.request.notify.SendHtmlTemplates;
import com.welearn.entity.vo.request.notify.SendHtmls;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.vo.request.notify.SendHtml;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.EmailSendService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/emailSend")
public class EmailSendController {

    @Autowired
    private EmailSendService service;

    @RequestMapping(value = "/forUsers")
    @ApiOperation(value = "手动设置HTML邮件内容发送给多人", httpMethod = "POST")
    public CommonResponse forUsers(@RequestBody SendHtmls sendHtmls) {
        service.sendHtml(sendHtmls.getReceivers(), sendHtmls.getSubject(), sendHtmls.getHtml());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/forUser")
    @ApiOperation(value = "sendHtml", httpMethod = "POST")
    public CommonResponse forUser(@RequestBody SendHtml sendHtml) {
        service.sendHtml(sendHtml.getReceiver(), sendHtml.getSubject(), sendHtml.getHtml());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/forUsersUseTemplate")
    @ApiOperation(value = "模板形式发送邮件给多人", httpMethod = "POST")
    public CommonResponse forUsersUseTemplate(@RequestBody SendHtmlTemplates sendHtmlTemplates) {
        service.sendHtml(sendHtmlTemplates.getReceivers(), sendHtmlTemplates.getSubject(), sendHtmlTemplates.getCode(), sendHtmlTemplates.getArgs());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/forUserUseTemplate")
    @ApiOperation(value = "模板形式发送邮件给单人", httpMethod = "POST")
    public CommonResponse forUserUseTemplate(@RequestBody SendHtmlTemplate sendHtmlTemplate) {
        service.sendHtml(sendHtmlTemplate.getReceiver(), sendHtmlTemplate.getSubject(), sendHtmlTemplate.getCode(), sendHtmlTemplate.getArgs());
        return CommonResponse.getSuccessResponse();
    }
}