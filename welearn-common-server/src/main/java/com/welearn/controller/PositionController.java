package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.vo.request.common.Available;
import com.welearn.entity.vo.request.common.UserSearch;
import com.welearn.entity.vo.request.equipment.CompanyPositionInfo;
import com.welearn.entity.vo.response.common.PositionInfo;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.Position;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.PositionQueryCondition;
import com.welearn.entity.vo.request.common.Allot;
import com.welearn.entity.vo.request.common.TakeBack;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.service.PositionService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/position")
public class PositionController extends BaseController<Position, PositionQueryCondition, PositionService>{

    @RequestMapping(value = "/userPositions")
    @ApiOperation(value = "查询用户的职位信息", httpMethod = "POST")
    public CommonResponse<List<Position>> userPositions(@RequestBody String string)  {
        List<Position> result = service.userPositions(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/allot")
    @ApiOperation(value = "分配职位", httpMethod = "POST")
    public CommonResponse allot(@RequestBody Allot allot, @RequestUser User user) throws BusinessVerifyFailedException {
        service.allot(allot.getUserId(), allot.getDepartmentId(), allot.getPositionId(), user);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/takeBack")
    @ApiOperation(value = "撤消职位", httpMethod = "POST")
    public CommonResponse takeBack(@RequestBody TakeBack takeBack, @RequestUser User user) throws BusinessVerifyFailedException {
        service.takeBack(takeBack.getUserId(), takeBack.getDepartmentId(), takeBack.getPositionId(), user);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/available")
    @ApiOperation(value = "查询公司部门可用职位", httpMethod = "POST")
    public CommonResponse<List<Position>> available(@RequestBody Available available)  {
        List<Position> result = service.available(available.getVisitorCompanyType(), available.getCompanyId(), available.getDepartmentId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/userSearch")
    @ApiOperation(value = "查询职位关联用户", httpMethod = "POST")
    public CommonResponse<List<User>> userSearch(@RequestBody UserSearch userSearch)  {
        List<User> result = service.userSearch(userSearch.getCompanyId(), userSearch.getDepartmentId(), userSearch.getPositionId(), userSearch.getCode());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/companyPositionInfo")
    @ApiOperation(value = "查询公司用户职位分配信息", httpMethod = "POST")
    public CommonResponse<List<PositionInfo>> companyPositionInfo(@RequestBody CompanyPositionInfo companyPositionInfo) {
        List<PositionInfo> result = service.companyPositionInfo(companyPositionInfo.getCompanyType(), companyPositionInfo.getCompanyId());
        return new CommonResponse<>(result);
    }
}