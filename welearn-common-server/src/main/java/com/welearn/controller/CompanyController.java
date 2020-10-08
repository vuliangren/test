package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.request.common.CreateCompanyAndInit;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.common.LocationInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.Company;
import com.welearn.entity.qo.common.CompanyQueryCondition;
import com.welearn.service.CompanyService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/company")
public class CompanyController extends BaseController<Company, CompanyQueryCondition, CompanyService>{

    @RequestMapping(value = "/searchLocationInfo")
    @ApiOperation(value = "获取位置信息 根据 建筑/楼层/房间 id", httpMethod = "POST")
    public CommonResponse<LocationInfo> searchLocationInfo(@RequestBody String string) {
        LocationInfo result = service.searchLocationInfo(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/createAndInit")
    @ApiOperation(value = "创建公司并对其进行初始化操作(创建部门, 添加管理员等)", httpMethod = "POST")
    public CommonResponse createAndInit(@RequestBody CreateCompanyAndInit createCompanyAndInit, @RequestUser User user)  {
        service.createAndInit(createCompanyAndInit.getCompany(), createCompanyAndInit.getDepartment(), createCompanyAndInit.getUser(), createCompanyAndInit.getRoleIds(), createCompanyAndInit.getPositionIds(), user);
        return CommonResponse.getSuccessResponse();
    }
}