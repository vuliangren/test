package com.welearn.service.impl;

import com.welearn.entity.po.finance.StockType;
import com.welearn.entity.qo.finance.StockTypeQueryCondition;
import com.welearn.mapper.StockTypeMapper;
import com.welearn.service.StockTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : StockTypeService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class StockTypeServiceImpl extends BaseServiceImpl<StockType,StockTypeQueryCondition,StockTypeMapper>
        implements StockTypeService{
    
    @Autowired
    private StockTypeMapper stockTypeMapper;
    
    @Override
    StockTypeMapper getMapper() {
        return stockTypeMapper;
    }

}
