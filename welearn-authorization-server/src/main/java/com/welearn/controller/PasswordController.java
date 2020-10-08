package com.welearn.controller;

import com.welearn.entity.vo.request.common.ForgetPasswordProcess;
import com.welearn.entity.vo.request.common.ForgetPasswordRequest;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.feign.common.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@Validated
@RequestMapping("/password")
@RestController
public class PasswordController {

    @Autowired
    private UserFeignClient userFeignClient;

    @RequestMapping("/forget/request")
    public CommonResponse forgetRequest(@RequestBody ForgetPasswordRequest forgetPasswordRequest){
        return userFeignClient.forgetPasswordRequest(forgetPasswordRequest);
    }

    @RequestMapping("/forget/process")
    public CommonResponse forgetProcess(@RequestBody ForgetPasswordProcess forgetPasswordProcess) {
        return userFeignClient.forgetPasswordProcess(forgetPasswordProcess);
    }
}
