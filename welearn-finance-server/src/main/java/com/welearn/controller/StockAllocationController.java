package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.finance.StockAllocation;
import com.welearn.entity.qo.finance.StockAllocationQueryCondition;
import com.welearn.entity.vo.request.finance.UpdateBatchUse;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.StockAllocationService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/stockAllocation")
public class StockAllocationController extends BaseController<StockAllocation, StockAllocationQueryCondition, StockAllocationService>{

    @RequestMapping(value = "/releaseBatchUse")
    @ApiOperation(value = "释放批次的货位占用", httpMethod = "POST")
    public CommonResponse releaseBatchUse(@RequestBody String batchId)  {
        service.releaseBatchUse(batchId);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/selectBatchUse")
    @ApiOperation(value = "获得批次的货位占用", httpMethod = "POST")
    public CommonResponse<List<StockAllocation>> selectBatchUse(@RequestBody String batchId)  {
        return new CommonResponse<>(service.selectBatchUse(batchId));
    }

    @RequestMapping(value = "/updateBatchUse")
    @ApiOperation(value = "更新批次的货位占用", httpMethod = "POST")
    public CommonResponse updateBatchUse(@RequestBody UpdateBatchUse updateBatchUse)  {
        service.updateBatchUse(updateBatchUse.getBatchId(), updateBatchUse.getAllocationIds());
        return CommonResponse.getSuccessResponse();
    }

}