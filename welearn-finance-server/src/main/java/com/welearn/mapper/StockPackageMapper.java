package com.welearn.mapper;

import com.welearn.entity.po.finance.StockPackage;
import com.welearn.entity.qo.finance.StockPackageQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * StockPackage Mapper Interface : ryme_finance : stock_package
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:57
 * @see StockPackage
 */
@Mapper 
public interface StockPackageMapper extends BaseMapper<StockPackage, StockPackageQueryCondition> {
    
}