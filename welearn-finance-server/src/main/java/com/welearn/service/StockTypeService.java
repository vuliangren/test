package com.welearn.service;

import com.welearn.entity.po.finance.StockType;
import com.welearn.entity.qo.finance.StockTypeQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : StockTypeService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface StockTypeService extends BaseService<StockType, StockTypeQueryCondition>{

}