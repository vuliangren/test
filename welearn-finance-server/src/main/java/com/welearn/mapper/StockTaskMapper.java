package com.welearn.mapper;

import com.welearn.entity.po.finance.StockTask;
import com.welearn.entity.qo.finance.StockTaskQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * StockTask Mapper Interface : ryme_finance : stock_task
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:59
 * @see StockTask
 */
@Mapper 
public interface StockTaskMapper extends BaseMapper<StockTask, StockTaskQueryCondition> {
    
}