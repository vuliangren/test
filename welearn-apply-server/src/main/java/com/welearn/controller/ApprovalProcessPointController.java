package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.vo.request.apply.PointCount;
import com.welearn.generator.ControllerGenerator;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.qo.apply.ApprovalProcessPointQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.ApprovalProcessPointService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/approvalProcessPoint")
public class ApprovalProcessPointController extends BaseController<ApprovalProcessPoint, ApprovalProcessPointQueryCondition, ApprovalProcessPointService>{

    @RequestMapping(value = "/pointCount")
    @ApiOperation(value = "获取审批节点数量", httpMethod = "POST")
    public CommonResponse<Integer> pointCount(@RequestBody PointCount pointCount)  {
        Integer result = service.pointCount(pointCount.getCompanyId(), pointCount.getCode());
        return new CommonResponse<>(result);
    }
}