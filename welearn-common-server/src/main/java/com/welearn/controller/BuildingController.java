package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.vo.response.common.Option;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.ReRouteRoleService;
import com.welearn.service.WechatAppUserService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.Building;
import com.welearn.entity.qo.common.BuildingQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.common.BuildingInfo;
import com.welearn.service.BuildingService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/building")
public class BuildingController extends BaseController<Building, BuildingQueryCondition, BuildingService>{

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "searchInfo", httpMethod = "POST")
    public CommonResponse<List<BuildingInfo>> searchInfo(@RequestBody String string)  {
        List<BuildingInfo> result = service.searchInfo(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/searchOptionInfo")
    @ApiOperation(value = "查询用于选择使用的建筑信息", httpMethod = "POST")
    public CommonResponse<List<Option<String, String>>> searchOptionInfo(@RequestBody String string)  {
        List<Option<String, String>> result = service.searchOptionInfo(string);
        return new CommonResponse<>(result);
    }
}