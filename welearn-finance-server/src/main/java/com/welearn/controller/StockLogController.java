package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.finance.Stock;
import com.welearn.entity.po.finance.StockLog;
import com.welearn.entity.qo.finance.StockLogQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.StockLogService;
import java.lang.Double;
import java.lang.Integer;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/stockLog")
public class StockLogController extends BaseController<StockLog, StockLogQueryCondition, StockLogService>{

    @RequestMapping(value = "/stockAutoLog")
    @ApiOperation(value = "定时自动更新和记录库存的相关参数", httpMethod = "POST")
    public CommonResponse stockAutoLog()  {
        service.stockAutoLog();
        return CommonResponse.getSuccessResponse();
    }

}