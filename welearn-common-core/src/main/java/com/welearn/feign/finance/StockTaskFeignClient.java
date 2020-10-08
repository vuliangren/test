package com.welearn.feign.finance;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.finance.StockTask;
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

import java.util.List;

/**
 * Description : welearn-finance-service / com.welearn.controller.StockTaskController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-finance-server", configuration = FeignConfiguration.class)
public interface StockTaskFeignClient {

    @RequestMapping(value = "/stockTask/stockLeaveScanCode")
    CommonResponse<StockTaskInfo> stockLeaveScanCode(StockLeaveScanCode stockLeaveScanCode);

    @RequestMapping(value = "/stockTask/createStockTransferTask")
    CommonResponse<StockTask> createStockTransferTask(CreateStockTransferTask createStockTransferTask);

    @RequestMapping(value = "/stockTask/createStockOutTask")
    CommonResponse<StockTask> createStockOutTask(CreateStockOutTask createStockOutTask);

    @RequestMapping(value = "/stockTask/createStockInTask0")
    CommonResponse<StockTask> createStockInTask0(CreateStockInTask0 createStockInTask0);

    @RequestMapping(value = "/stockTask/createStockConsumeTask")
    CommonResponse<StockTask> createStockConsumeTask(CreateStockConsumeTask createStockConsumeTask);

    @RequestMapping(value = "/stockTask/stockBackScanCode")
    CommonResponse<StockTaskInfo> stockBackScanCode(StockBackScanCode stockBackScanCode);

    @RequestMapping(value = "/stockTask/createStockInTask")
    CommonResponse<StockTask> createStockInTask(CreateStockInTask createStockInTask);

    @RequestMapping(value = "/stockTask/cancelStockTask")
    CommonResponse cancelStockTask(CancelStockTask cancelStockTask);

    @RequestMapping(value = "/stockTask/stockOutScanCode")
    CommonResponse<StockTaskInfo> stockOutScanCode(StockOutScanCode stockOutScanCode);

    @RequestMapping(value = "/stockTask/stockInScanCode")
    CommonResponse<StockTaskInfo> stockInScanCode(StockInScanCode stockInScanCode);

    @RequestMapping(value = "/stockTask/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/stockTask/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/stockTask/update")
    CommonResponse update(StockTask entity);

    @RequestMapping(value = "/stockTask/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/stockTask/create")
    CommonResponse<StockTask> create(StockTask entity);

    @RequestMapping(value = "/stockTask/search")
    CommonResponse<List<StockTask>> search(StockTaskQueryCondition queryCondition);

    @RequestMapping(value = "/stockTask/select")
    CommonResponse<StockTask> select(String id);
}