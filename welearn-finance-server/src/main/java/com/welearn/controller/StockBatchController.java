package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.finance.StockBatch;
import com.welearn.entity.qo.finance.StockBatchQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.finance.StockBatchInfo;
import com.welearn.service.StockBatchService;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/stockBatch")
public class StockBatchController extends BaseController<StockBatch, StockBatchQueryCondition, StockBatchService>{

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询批次详情(带库存类型信息)", httpMethod = "POST")
    public CommonResponse<List<StockBatchInfo>> searchInfo(@RequestBody StockBatchQueryCondition stockBatchQueryCondition)  {
        List<StockBatchInfo> result = service.searchInfo(stockBatchQueryCondition);
        return new CommonResponse<>(result);
    }


}