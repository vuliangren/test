package com.welearn.service.impl;

import com.welearn.entity.po.finance.Stock;
import com.welearn.entity.qo.finance.StockQueryCondition;
import com.welearn.entity.vo.response.finance.StockInfo;
import com.welearn.mapper.StockMapper;
import com.welearn.service.StockService;
import com.welearn.util.PaginateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : StockService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class StockServiceImpl extends BaseServiceImpl<Stock,StockQueryCondition,StockMapper>
        implements StockService{
    
    @Autowired
    private StockMapper stockMapper;
    
    @Override
    StockMapper getMapper() {
        return stockMapper;
    }

    /**
     * 按搜索条件查询 库存详细信息(带类型信息)
     *
     * @param condition 查询条件
     * @return 库存详请列表
     */
    @Override
    public List<StockInfo> searchInfo(StockQueryCondition condition) {
        return PaginateUtil.page(()->stockMapper.selectInfoByCondition(condition));
    }
}
