package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.finance.StockTask;
import com.welearn.entity.po.finance.StockType;
import com.welearn.entity.qo.finance.StockTaskQueryCondition;
import com.welearn.entity.vo.request.finance.CancelStockTask;
import com.welearn.entity.vo.request.finance.CreateStockConsumeTask;
import com.welearn.entity.vo.request.finance.CreateStockInTask0;
import com.welearn.entity.vo.request.finance.CreateStockInTask;
import com.welearn.entity.vo.request.finance.CreateStockOutTask;
import com.welearn.entity.vo.request.finance.CreateStockTransferTask;
import com.welearn.entity.vo.request.finance.StockBackScanCode;
import com.welearn.entity.vo.request.finance.StockInScanCode;
import com.welearn.entity.vo.request.finance.StockLeaveScanCode;
import com.welearn.entity.vo.request.finance.StockOutScanCode;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.finance.StockTaskInfo;
import com.welearn.service.StockTaskService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/stockTask")
public class StockTaskController extends BaseController<StockTask, StockTaskQueryCondition, StockTaskService>{
    @RequestMapping(value = "/stockOutScanCode")
    @ApiOperation(value = "stockOutScanCode", httpMethod = "POST")
    public CommonResponse<StockTaskInfo> stockOutScanCode(@RequestBody StockOutScanCode stockOutScanCode)  {
        StockTaskInfo result = service.stockOutScanCode(stockOutScanCode.getCode(), stockOutScanCode.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/cancelStockTask")
    @ApiOperation(value = "cancelStockTask", httpMethod = "POST")
    public CommonResponse cancelStockTask(@RequestBody CancelStockTask cancelStockTask)  {
        service.cancelStockTask(cancelStockTask.getTaskId(), cancelStockTask.getReason(), cancelStockTask.getUserId());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/stockInScanCode")
    @ApiOperation(value = "stockInScanCode", httpMethod = "POST")
    public CommonResponse<StockTaskInfo> stockInScanCode(@RequestBody StockInScanCode stockInScanCode)  {
        StockTaskInfo result = service.stockInScanCode(stockInScanCode.getCode(), stockInScanCode.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/createStockConsumeTask")
    @ApiOperation(value = "createStockConsumeTask", httpMethod = "POST")
    public CommonResponse<StockTask> createStockConsumeTask(@RequestBody CreateStockConsumeTask createStockConsumeTask)  {
        StockTask result = service.createStockConsumeTask(createStockConsumeTask.getPackageId(), createStockConsumeTask.getCount(), createStockConsumeTask.getDescription(), createStockConsumeTask.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/createStockTransferTask")
    @ApiOperation(value = "createStockTransferTask", httpMethod = "POST")
    public CommonResponse<StockTask> createStockTransferTask(@RequestBody CreateStockTransferTask createStockTransferTask)  {
        StockTask result = service.createStockTransferTask(createStockTransferTask.getContentType(), createStockTransferTask.getItemTypeId(), createStockTransferTask.getItemSpecification(), createStockTransferTask.getTaskType(), createStockTransferTask.getTaskDescription(), createStockTransferTask.getTaskCount(), createStockTransferTask.getTaskRemark(), createStockTransferTask.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/stockLeaveScanCode")
    @ApiOperation(value = "stockLeaveScanCode", httpMethod = "POST")
    public CommonResponse<StockTaskInfo> stockLeaveScanCode(@RequestBody StockLeaveScanCode stockLeaveScanCode)  {
        StockTaskInfo result = service.stockLeaveScanCode(stockLeaveScanCode.getCode(), stockLeaveScanCode.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/stockBackScanCode")
    @ApiOperation(value = "stockBackScanCode", httpMethod = "POST")
    public CommonResponse<StockTaskInfo> stockBackScanCode(@RequestBody StockBackScanCode stockBackScanCode)  {
        StockTaskInfo result = service.stockBackScanCode(stockBackScanCode.getCode(), stockBackScanCode.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/createStockInTask")
    @ApiOperation(value = "createStockInTask", httpMethod = "POST")
    public CommonResponse<StockTask> createStockInTask(@RequestBody CreateStockInTask createStockInTask)  {
        StockTask result = service.createStockInTask(createStockInTask.getStockId(), createStockInTask.getTaskDescription(), createStockInTask.getTaskRemark(), createStockInTask.getStockPackages(), createStockInTask.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/createStockInTask0")
    @ApiOperation(value = "createStockInTask0", httpMethod = "POST")
    public CommonResponse<StockTask> createStockInTask0(@RequestBody CreateStockInTask0 createStockInTask0)  {
        StockTask result = service.createStockInTask0(createStockInTask0.getStockType(), createStockInTask0.getTaskDescription(), createStockInTask0.getTaskRemark(), createStockInTask0.getStockPackages(), createStockInTask0.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/createStockOutTask")
    @ApiOperation(value = "createStockOutTask", httpMethod = "POST")
    public CommonResponse<StockTask> createStockOutTask(@RequestBody CreateStockOutTask createStockOutTask)  {
        StockTask result = service.createStockOutTask(createStockOutTask.getStockId(), createStockOutTask.getTaskDescription(), createStockOutTask.getTaskRemark(), createStockOutTask.getTaskCount(), createStockOutTask.getUserId());
        return new CommonResponse<>(result);
    }

}