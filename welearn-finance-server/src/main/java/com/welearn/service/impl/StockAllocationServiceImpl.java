package com.welearn.service.impl;

import com.welearn.entity.po.finance.ReBatchAllocation;
import com.welearn.entity.po.finance.StockAllocation;
import com.welearn.entity.qo.finance.ReBatchAllocationQueryCondition;
import com.welearn.entity.qo.finance.StockAllocationQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.ReBatchAllocationMapper;
import com.welearn.mapper.StockAllocationMapper;
import com.welearn.service.StockAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description : StockAllocationService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class StockAllocationServiceImpl extends BaseServiceImpl<StockAllocation,StockAllocationQueryCondition,StockAllocationMapper>
        implements StockAllocationService{
    
    @Autowired
    private StockAllocationMapper stockAllocationMapper;

    @Autowired
    private ReBatchAllocationMapper reBatchAllocationMapper;
    
    @Override
    StockAllocationMapper getMapper() {
        return stockAllocationMapper;
    }

    /**
     * 更新批次的货位占用
     *
     * @param batchId       批次id
     * @param allocationIds 货位id 列表
     */
    @Override
    @Transactional
    public void updateBatchUse(String batchId, List<String> allocationIds) {
        ReBatchAllocationQueryCondition condition = new ReBatchAllocationQueryCondition();
        condition.setBatchId(batchId);
        condition.setIsEnable(true);
        List<ReBatchAllocation> oldRe = reBatchAllocationMapper.selectByCondition(condition);
        Map<String, String> reBatchAllocationMap = oldRe.stream().collect(Collectors.toMap(ReBatchAllocation::getAllocationId, ReBatchAllocation::getId));
        // 原先货位集合
        Set<String> oldAllocationIds = new HashSet<>(reBatchAllocationMap.keySet());
        // 计算新旧的差集 进行添加
        Set<String> currentAllocationIds = new HashSet<>(allocationIds);
        currentAllocationIds.removeAll(oldAllocationIds);
        for (String allocationId : currentAllocationIds) {
            StockAllocation allocation = this.select(allocationId);
            if (Objects.isNull(allocation))
                throw new BusinessVerifyFailedException("allocationIds 存在 非法 id");
            ReBatchAllocation rba = new ReBatchAllocation();
            rba.setAllocationId(allocationId);
            rba.setBatchId(batchId);
            rba.setPlaceId(allocation.getStockPlaceId());
            reBatchAllocationMapper.insert(rba);
        }
        // 计算旧新的差集 进行禁用
        oldAllocationIds.removeAll(allocationIds);
        for (String allocationId : oldAllocationIds) {
            String reId = reBatchAllocationMap.get(allocationId);
            reBatchAllocationMapper.disable(reId);
        }
    }

    /**
     * 释放批次的货位占用
     *
     * @param batchId 批次id
     */
    @Override
    public void releaseBatchUse(String batchId) {
        reBatchAllocationMapper.batchDisabledByBatchId(batchId);
    }

    /**
     * 获得批次的货位占用
     *
     * @param batchId 批次id
     */
    @Override
    public List<StockAllocation> selectBatchUse(String batchId) {
        return stockAllocationMapper.selectByBatchId(batchId);
    }
}
