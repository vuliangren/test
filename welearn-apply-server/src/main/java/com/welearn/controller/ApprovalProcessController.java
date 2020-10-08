package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.apply.ApprovalProcess;
import com.welearn.entity.qo.apply.ApprovalProcessQueryCondition;
import com.welearn.entity.vo.request.apply.SelectInfoByCodeAndCompanyId;
import com.welearn.entity.vo.request.apply.SelectInfoByCodeListAndCompanyId;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.apply.ApprovalProcessInfo;
import com.welearn.service.ApprovalProcessService;
import java.lang.String;
import java.util.List;
import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/approvalProcess")
public class ApprovalProcessController extends BaseController<ApprovalProcess, ApprovalProcessQueryCondition, ApprovalProcessService>{
    @RequestMapping(value = "/selectInfoByCodeAndCompanyId")
    @ApiOperation(value = "selectInfoByCodeAndCompanyId", httpMethod = "POST")
    public CommonResponse<ApprovalProcessInfo> selectInfoByCodeAndCompanyId(@RequestBody SelectInfoByCodeAndCompanyId selectInfoByCodeAndCompanyId)  {
        ApprovalProcessInfo result = service.selectInfoByCodeAndCompanyId(selectInfoByCodeAndCompanyId.getCode(), selectInfoByCodeAndCompanyId.getCompanyId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectInfoByCodeListAndCompanyId")
    @ApiOperation(value = "获取分步申请的所有步骤的申请基本信息", httpMethod = "POST")
    public CommonResponse<Map<String, ApprovalProcess>> selectInfoByCodeListAndCompanyId(@RequestBody SelectInfoByCodeListAndCompanyId selectInfoByCodeListAndCompanyId)  {
        Map<String, ApprovalProcess> result = service.selectInfoByCodeListAndCompanyId(selectInfoByCodeListAndCompanyId.getCodeList(), selectInfoByCodeListAndCompanyId.getCompanyId());
        return new CommonResponse<>(result);
    }

}