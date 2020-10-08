package com.welearn.mapper;

import com.welearn.entity.po.finance.StockPlace;
import com.welearn.entity.qo.finance.StockPlaceQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * StockPlace Mapper Interface : ryme_finance : stock_place
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:58
 * @see StockPlace
 */
@Mapper 
public interface StockPlaceMapper extends BaseMapper<StockPlace, StockPlaceQueryCondition> {
    
}