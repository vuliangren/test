package com.welearn.mapper;

import com.welearn.entity.po.finance.ReBatchAllocation;
import com.welearn.entity.qo.finance.ReBatchAllocationQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ReBatchAllocation Mapper Interface : ryme_finance : re_batch_allocation
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/13 16:34:49
 * @see ReBatchAllocation
 */
@Mapper 
public interface ReBatchAllocationMapper extends BaseMapper<ReBatchAllocation, ReBatchAllocationQueryCondition> {

    /**
     * 批量禁用关联, 根据batchId
     * @param batchId 库存批次id
     */
    void batchDisabledByBatchId(@Param("batchId") String batchId);
}