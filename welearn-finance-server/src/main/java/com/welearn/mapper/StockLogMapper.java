package com.welearn.mapper;

import com.welearn.entity.po.finance.StockLog;
import com.welearn.entity.qo.finance.StockLogQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * StockLog Mapper Interface : ryme_finance : stock_log
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:56
 * @see StockLog
 */
@Mapper 
public interface StockLogMapper extends BaseMapper<StockLog, StockLogQueryCondition> {
    
}