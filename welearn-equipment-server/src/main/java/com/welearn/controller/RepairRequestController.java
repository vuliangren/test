package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.po.equipment.RepairCheck;
import com.welearn.validate.annotation.common.EntityCheck;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairRequest;
import com.welearn.entity.qo.equipment.RepairRequestQueryCondition;
import com.welearn.entity.vo.request.equipment.RepairRequestCancel;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RepairRequestService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.String;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairRequest")
@Api(value = "repairRequest 接口")
public class RepairRequestController extends BaseController<RepairRequest, RepairRequestQueryCondition, RepairRequestService>{

    @RequestMapping(value = "/cancel")
    @ApiOperation(value = "报修取消", httpMethod = "POST")
    public CommonResponse cancel(@RequestBody RepairRequestCancel cancel, @RequestUser User user)  {
        service.cancel(cancel.getRequestId(), cancel.getReason(), user);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/check")
    @ApiOperation(value = "维修验收", httpMethod = "POST")
    public CommonResponse check(@RequestBody RepairCheck repairCheck, @RequestUser User user)  {
        service.check(repairCheck, user);
        return CommonResponse.getSuccessResponse();
    }

}