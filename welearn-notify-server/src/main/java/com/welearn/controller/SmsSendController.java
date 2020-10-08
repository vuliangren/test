package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.SmsSendService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.request.notify.SendForPhone;
import com.welearn.entity.vo.request.notify.SendForUser;
import com.welearn.entity.vo.response.CommonResponse;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/smsSend")
@Api(value = "smsSend 接口")
public class SmsSendController {

    @Autowired
    private SmsSendService service;

    @RequestMapping(value = "/sendForPhone")
    @ApiOperation(value = "sendForPhone", httpMethod = "POST")
    public CommonResponse sendForPhone(@RequestBody SendForPhone sendForPhone) {
        service.sendForPhone(sendForPhone.getPhoneNumber(), sendForPhone.getSignature(), sendForPhone.getCode(), sendForPhone.getParams(), sendForPhone.getCompanyId(), sendForPhone.getDepartmentId(), sendForPhone.getUserId(), sendForPhone.getNoticeId());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/sendForUser")
    @ApiOperation(value = "sendForUser", httpMethod = "POST")
    public CommonResponse sendForUser(@RequestBody SendForUser sendForUser) {
        service.sendForUser(sendForUser.getUser(), sendForUser.getSignature(), sendForUser.getCode(), sendForUser.getParams(), sendForUser.getNoticeId());
        return CommonResponse.getSuccessResponse();
    }

}