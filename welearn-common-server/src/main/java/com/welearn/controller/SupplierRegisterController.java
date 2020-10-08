package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.SupplierRegister;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.SupplierRegisterQueryCondition;
import com.welearn.entity.vo.request.common.Approval;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.SupplierRegisterService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/supplierRegister")
public class SupplierRegisterController extends BaseController<SupplierRegister, SupplierRegisterQueryCondition, SupplierRegisterService>{

    @RequestMapping(value = "/approval")
    @ApiOperation(value = "approval", httpMethod = "POST")
    public CommonResponse approval(@RequestBody Approval approval, @RequestUser User user)  {
        service.approval(approval.getSupplierRegisterId(), approval.getIsPassed(), user, approval.getResult());
        return CommonResponse.getSuccessResponse();
    }

}