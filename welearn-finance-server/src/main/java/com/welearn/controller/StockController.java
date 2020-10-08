package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.finance.Stock;
import com.welearn.entity.qo.finance.StockQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.finance.StockInfo;
import com.welearn.service.StockService;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/stock")
public class StockController extends BaseController<Stock, StockQueryCondition, StockService>{
    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询库存详情(带库存类型信息)", httpMethod = "POST")
    public CommonResponse<List<StockInfo>> searchInfo(@RequestBody StockQueryCondition stockQueryCondition)  {
        List<StockInfo> result = service.searchInfo(stockQueryCondition);
        return new CommonResponse<>(result);
    }

}