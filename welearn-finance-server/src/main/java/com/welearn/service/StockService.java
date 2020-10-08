package com.welearn.service;

import com.welearn.entity.po.finance.Stock;
import com.welearn.entity.qo.finance.StockQueryCondition;
import com.welearn.entity.vo.response.finance.StockInfo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : StockService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface StockService extends BaseService<Stock, StockQueryCondition>{

    /**
     * 按搜索条件查询 库存详细信息(带类型信息)
     * @param condition 查询条件
     * @return 库存详请列表
     */
    List<StockInfo> searchInfo(StockQueryCondition condition);

}