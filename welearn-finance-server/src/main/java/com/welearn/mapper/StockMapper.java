package com.welearn.mapper;

import com.welearn.entity.po.finance.Stock;
import com.welearn.entity.qo.finance.StockQueryCondition;
import com.welearn.entity.vo.response.finance.StockInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Stock Mapper Interface : ryme_finance : stock
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:52
 * @see Stock
 */
@Mapper 
public interface StockMapper extends BaseMapper<Stock, StockQueryCondition> {

    /**
     * 按搜索条件查询 库存详细信息(带类型信息)
     * @param condition 查询条件
     * @return 库存详请列表
     */
    List<StockInfo> selectInfoByCondition(StockQueryCondition condition);
}