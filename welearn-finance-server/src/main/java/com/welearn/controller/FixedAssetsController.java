package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.finance.FixedAssets;
import com.welearn.entity.qo.finance.FixedAssetsQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.FixedAssetsService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/fixedAssets")
@Api(value = "fixedAssets 接口")
public class FixedAssetsController extends BaseController<FixedAssets, FixedAssetsQueryCondition, FixedAssetsService>{

    @RequestMapping(value = "/equipmentValueStatistic")
    @ApiOperation(value = "设备固定资产统计分析数据", httpMethod = "POST")
    public CommonResponse<List<FixedAssets>> equipmentValueStatistic(@RequestBody String string)  {
        List<FixedAssets> result = service.equipmentValueStatistic(string);
        return new CommonResponse<>(result);
    }
}