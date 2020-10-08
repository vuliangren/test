package com.welearn.controller;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.common.SupplierRegister;
import com.welearn.entity.vo.request.authorization.UserWeChatAppProcess;
import com.welearn.entity.vo.request.authorization.UserWeChatAppRequest;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.authorization.WeChatAppLoginResult;
import com.welearn.generator.ControllerGenerator;
import com.welearn.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Description : 注册申请
 * Created by Setsuna Jin on 2018/11/29.
 */
@Slf4j
@Validated
@RequestMapping("/register")
@RestController
public class RegisterController {
    @Autowired
    private RegisterService service;

    @RequestMapping("/supplier")
    public CommonResponse supplierRegister(@RequestBody SupplierRegister supplierRegister){
        service.supplierRegister(supplierRegister);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/userWeChatAppProcess")
    @ApiOperation(value = "用户微信小程序注册-验证验证码", httpMethod = "POST")
    public CommonResponse<WeChatAppLoginResult> userWeChatAppProcess(@RequestBody UserWeChatAppProcess userWeChatAppProcess)  {
        WeChatAppLoginResult result = service.userWeChatAppProcess(userWeChatAppProcess.getTelephone(), userWeChatAppProcess.getAuthCode(), userWeChatAppProcess.getOpenId(), userWeChatAppProcess.getType());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/userWeChatAppRequest")
    @ApiOperation(value = "用户微信小程序注册-获取验证码", httpMethod = "POST")
    public CommonResponse userWeChatAppRequest(@RequestBody UserWeChatAppRequest userWeChatAppRequest)  {
        service.userWeChatAppRequest(userWeChatAppRequest.getName(), userWeChatAppRequest.getTelephone(), userWeChatAppRequest.getSn());
        return CommonResponse.getSuccessResponse();
    }
}
