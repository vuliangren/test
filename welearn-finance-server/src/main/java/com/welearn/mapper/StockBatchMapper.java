package com.welearn.mapper;

import com.welearn.entity.po.finance.StockBatch;
import com.welearn.entity.qo.finance.StockBatchQueryCondition;
import com.welearn.entity.vo.response.finance.StockBatchInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * StockBatch Mapper Interface : ryme_finance : stock_batch
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:54
 * @see StockBatch
 */
@Mapper 
public interface StockBatchMapper extends BaseMapper<StockBatch, StockBatchQueryCondition> {
    /**
     * 按搜索条件查询 库存批次详细信息(带类型信息)
     * @param condition 查询条件
     * @return 库存批次详请列表
     */
    List<StockBatchInfo> selectInfoByCondition(StockBatchQueryCondition condition);
}