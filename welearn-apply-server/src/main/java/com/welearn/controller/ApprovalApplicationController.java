package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.qo.apply.ApprovalApplicationQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.apply.ApplicationShow;
import com.welearn.service.ApprovalApplicationService;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/approvalApplication")
public class ApprovalApplicationController extends BaseController<ApprovalApplication, ApprovalApplicationQueryCondition, ApprovalApplicationService>{

    @RequestMapping(value = "/selectUserApplicationByCondition")
    @ApiOperation(value = "根据条件查询用户申请数据", httpMethod = "POST")
    public CommonResponse<List<ApplicationShow>> selectUserApplicationByCondition(@RequestBody ApprovalApplicationQueryCondition approvalApplicationQueryCondition) {
        List<ApplicationShow> result = service.selectUserApplicationByCondition(approvalApplicationQueryCondition);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/searchInfoByCondition")
    @ApiOperation(value = "根据条件查询申请审批相关数据", httpMethod = "POST")
    public CommonResponse<List<ApplicationShow>> searchInfoByCondition(@RequestBody ApprovalApplicationQueryCondition approvalApplicationQueryCondition) {
        List<ApplicationShow> result = service.searchInfoByCondition(approvalApplicationQueryCondition);
        return new CommonResponse<>(result);
    }
}