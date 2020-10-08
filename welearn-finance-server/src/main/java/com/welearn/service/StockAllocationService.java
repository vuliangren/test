package com.welearn.service;

import com.welearn.entity.po.finance.StockAllocation;
import com.welearn.entity.qo.finance.StockAllocationQueryCondition;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : StockAllocationService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface StockAllocationService extends BaseService<StockAllocation, StockAllocationQueryCondition>{
    /**
     * 更新批次的货位占用
     * @param batchId 批次id
     * @param allocationIds 货位id 列表
     */
    void updateBatchUse(String batchId, List<String> allocationIds);

    /**
     * 释放批次的货位占用
     * @param batchId 批次id
     */
    void releaseBatchUse(String batchId);

    /**
     * 获得批次的货位占用
     * @param batchId 批次id
     */
    List<StockAllocation> selectBatchUse(String batchId);
}