package com.welearn.mapper;

import com.welearn.entity.po.finance.StockAllocation;
import com.welearn.entity.qo.finance.StockAllocationQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * StockAllocation Mapper Interface : ryme_finance : stock_allocation
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:53
 * @see StockAllocation
 */
@Mapper 
public interface StockAllocationMapper extends BaseMapper<StockAllocation, StockAllocationQueryCondition> {
    /**
     * 根据批次id 获取批次占用货位信息
     * @param batchId 批次id
     * @return 占用货位列表
     */
    List<StockAllocation> selectByBatchId(@Param("batchId") String batchId);
}