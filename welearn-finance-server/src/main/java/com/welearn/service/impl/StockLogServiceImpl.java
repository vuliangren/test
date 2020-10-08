package com.welearn.service.impl;

import com.welearn.entity.po.finance.Stock;
import com.welearn.entity.po.finance.StockLog;
import com.welearn.entity.qo.finance.StockLogQueryCondition;
import com.welearn.entity.qo.finance.StockQueryCondition;
import com.welearn.mapper.StockLogMapper;
import com.welearn.service.StockBatchService;
import com.welearn.service.StockLogService;
import com.welearn.service.StockService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.finance.StockExecuteTypeConst.*;

/**
 * Description : StockLogService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class StockLogServiceImpl extends BaseServiceImpl<StockLog,StockLogQueryCondition,StockLogMapper>
        implements StockLogService{

    @Autowired
    private StockService stockService;

    @Autowired
    private StockBatchService stockBatchService;

    @Autowired
    private StockLogMapper stockLogMapper;
    
    @Override
    StockLogMapper getMapper() {
        return stockLogMapper;
    }

    /**
     * 库存每月底需要记录相关的余量等信息
     * 每月一号 00:00 执行
     */
    @Override
    @Scheduled(cron = "0 0 0 1 * ?")
    public void stockAutoLog() {
        StockQueryCondition condition = new StockQueryCondition();
        condition.setIsEnable(true);
        List<Stock> stocks = stockService.search(condition);
        // 更新平均订货时间 和 平均每日消耗数量
        for (Stock stock : stocks) {
            Double fastOrderCostDay = this.getFastOrderCostDay(stock);
            Double orderAvgCostDay = stockBatchService.calOrderAvgCostDay(stock.getId(), 12);
            Double dayAvgCostCount = this.calDayAvgCostCount(stock, 3, stock.getExecuteType());
            stock.setOrderAvgCostDay(orderAvgCostDay);
            stock.setDayAvgCostCount(dayAvgCostCount);
            // 计算安全库存量
            stock.setSafeCount(fastOrderCostDay * dayAvgCostCount);
            // 计算订货存量点
            stock.setOrderCount(stock.getSafeCount() + orderAvgCostDay * dayAvgCostCount);
            stockService.update(stock);
        }
        // 添加日志信息
        for (Stock stock : stocks) {
            StockLog stockLog = new StockLog();
            stockLog.setStockId(stock.getId());
            stockLog.setStockTypeId(stock.getStockTypeId());
            stockLog.setDayAvgCountCost(stock.getDayAvgCostCount());
            stockLog.setInTransitInCount(stock.getInTransitInCount());
            stockLog.setInTransitOutCount(stock.getInTransitOutCount());
            stockLog.setSumPurchaseCount(stock.getSumPurchaseCount());
            stockLog.setSumPlanConsumeCount(stock.getSumPlanConsumeCount());
            stockLog.setSumRealConsumeCount(stock.getSumRealConsumeCount());
            this.create(stockLog);
        }
    }

    /**
     * 根据当前的安全库存计算出加急订单耗时天数
     * 设置 安全库存 时, 每日平均消耗量不能为0
     * @param stock 当前库存信息
     * @return 加急订单耗时天数
     */
    private Double getFastOrderCostDay(Stock stock){
        if (stock.getDayAvgCostCount() <= 0)
            return 0d;
        return stock.getSafeCount() / stock.getDayAvgCostCount();
    }

    /**
     * 计算库存的日均消耗量
     *
     * @param stock     当前库存
     * @param monthCount  月数
     * @param executeType 执行类型
     * @return 日均消耗量
     */
    @Override
    public Double calDayAvgCostCount(Stock stock, Integer monthCount, Integer executeType) {
        DateTime currentTime = new DateTime();
        DateTime startTime = currentTime.minusMonths(monthCount);
        StockLogQueryCondition condition = new StockLogQueryCondition();
        condition.setStartTime(startTime.toDate());
        condition.setEndTime(currentTime.toDate());
        condition.setIsEnable(true);
        condition.setStockId(stock.getId());
        List<StockLog> stockLogs = this.search(condition);
        if (Objects.isNull(stockLogs) || stockLogs.isEmpty())
            return 0d;
        StockLog lastLog = stockLogs.get(0);
        int days = new Period(new DateTime(lastLog.getCreatedAt()), currentTime, PeriodType.days()).getDays();
        if (executeType == PLAN.ordinal())
            return (stock.getSumPlanConsumeCount() - lastLog.getSumPlanConsumeCount()) * 1.0d / days;
        else if (executeType == REAL.ordinal())
            return (stock.getSumRealConsumeCount() - lastLog.getSumRealConsumeCount()) * 1.0d / days;
        return 0d;
    }
}
