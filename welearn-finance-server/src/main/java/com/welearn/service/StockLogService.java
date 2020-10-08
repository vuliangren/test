package com.welearn.service;

import com.welearn.entity.po.finance.Stock;
import com.welearn.entity.po.finance.StockLog;
import com.welearn.entity.qo.finance.StockLogQueryCondition;
import org.joda.time.DateTime;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

/**
 * Description : StockLogService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface StockLogService extends BaseService<StockLog, StockLogQueryCondition>{

    /**
     * 库存每月底需要记录相关的余量等信息
     */
    void stockAutoLog();

    /**
     * 计算库存的日均消耗量
     *
     * @param stock     当前库存
     * @param monthCount  月数
     * @param executeType 执行类型
     * @return 日均消耗量
     */
    Double calDayAvgCostCount(Stock stock, Integer monthCount, Integer executeType);
}