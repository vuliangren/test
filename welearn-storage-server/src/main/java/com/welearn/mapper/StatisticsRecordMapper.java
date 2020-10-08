package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.storage.StatisticsRecord;
import com.welearn.entity.qo.storage.StatisticsRecordQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * StatisticsRecord Mapper Interface : ryme_storage : statistics_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:39:11
 * @see com.welearn.entity.po.storage.StatisticsRecord
 */
@Mapper 
public interface StatisticsRecordMapper extends BaseMapper<StatisticsRecord, StatisticsRecordQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "statisticsRecordMapper", key = "'statisticsRecord:'+#id", unless = "#result == null")
    StatisticsRecord selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "statisticsRecordMapper", key = "'statisticsRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(StatisticsRecord entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "statisticsRecordMapper", key = "'statisticsRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(StatisticsRecord entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "statisticsRecordMapper", key = "'statisticsRecord:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "statisticsRecordMapper", key = "'statisticsRecord:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "statisticsRecordMapper", key = "'statisticsRecord:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "statisticsRecordMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
