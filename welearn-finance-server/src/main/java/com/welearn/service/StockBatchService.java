package com.welearn.service;

import com.welearn.entity.po.finance.StockAllocation;
import com.welearn.entity.po.finance.StockBatch;
import com.welearn.entity.qo.finance.StockBatchQueryCondition;
import com.welearn.entity.vo.response.finance.StockBatchInfo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : StockBatchService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface StockBatchService extends BaseService<StockBatch, StockBatchQueryCondition>{

    /**
     * 按搜索条件查询 库存批次详细信息(带类型信息)
     * @param condition 查询条件
     * @return 库存批次详请列表
     */
    List<StockBatchInfo> searchInfo(StockBatchQueryCondition condition);

    /**
     * 计算 某个库存的 月份内 平均订货耗时
     * @param stockId 库存id
     * @param monthCount 月数
     * @return 平均订货耗时
     */
    Double calOrderAvgCostDay(String stockId, Integer monthCount);
}