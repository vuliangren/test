package com.welearn.mapper;

import com.welearn.entity.po.finance.StockType;
import com.welearn.entity.qo.finance.StockTypeQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * StockType Mapper Interface : ryme_finance : stock_type
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:35:00
 * @see StockType
 */
@Mapper 
public interface StockTypeMapper extends BaseMapper<StockType, StockTypeQueryCondition> {
    
}