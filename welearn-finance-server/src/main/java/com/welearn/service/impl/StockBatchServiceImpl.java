package com.welearn.service.impl;

import com.welearn.entity.po.finance.StockBatch;
import com.welearn.entity.qo.finance.StockBatchQueryCondition;
import com.welearn.entity.vo.response.finance.StockBatchInfo;
import com.welearn.mapper.StockBatchMapper;
import com.welearn.service.StockBatchService;
import com.welearn.util.PaginateUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Description : StockBatchService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class StockBatchServiceImpl extends BaseServiceImpl<StockBatch,StockBatchQueryCondition,StockBatchMapper>
        implements StockBatchService{
    
    @Autowired
    private StockBatchMapper stockBatchMapper;
    
    @Override
    StockBatchMapper getMapper() {
        return stockBatchMapper;
    }

    /**
     * 按搜索条件查询 库存批次详细信息(带类型信息)
     *
     * @param condition 查询条件
     * @return 库存批次详请列表
     */
    @Override
    public List<StockBatchInfo> searchInfo(StockBatchQueryCondition condition) {
        return PaginateUtil.page(()->stockBatchMapper.selectInfoByCondition(condition));
    }

    /**
     * 计算 某个库存的 月份内 平均订货耗时
     *
     * @param stockId    库存id
     * @param monthCount 月数
     * @return 平均订货耗时
     */
    @Override
    public Double calOrderAvgCostDay(String stockId, Integer monthCount) {
        DateTime currentTime = new DateTime();
        DateTime startTime = currentTime.minusMonths(monthCount);
        StockBatchQueryCondition condition = new StockBatchQueryCondition();
        condition.setSCreatedAt(startTime.toDate());
        condition.setECreatedAt(currentTime.toDate());
        condition.setStockId(stockId);
        List<StockBatch> stockBatches = this.search(condition);
        // 计算 订单耗时 大于 0 的, 进行累加
        AtomicInteger orderCostDays = new AtomicInteger(0);
        AtomicInteger orderCount = new AtomicInteger(0);
        stockBatches.stream().filter(sb -> sb.getOrderCostDay() > 0).forEach(sb->{
            orderCostDays.updateAndGet(v -> v + sb.getOrderCostDay());
            orderCount.incrementAndGet();
        });
        if (orderCostDays.get() > 0 && orderCount.get() > 0)
            return (orderCostDays.get() * 1.0d / orderCount.get());
        else
            return 0d;
    }
}
