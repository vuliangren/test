package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.DeviceMonitorRecord;
import com.welearn.entity.qo.alink.DeviceMonitorRecordQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceMonitorRecord Mapper Interface : ryme_alink : device_monitor_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/10 14:18:31
 * @see com.welearn.entity.po.alink.DeviceMonitorRecord
 */
@Mapper 
public interface DeviceMonitorRecordMapper extends BaseMapper<DeviceMonitorRecord, DeviceMonitorRecordQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "deviceMonitorRecordMapper", key = "'deviceMonitorRecord:'+#id", unless = "#result == null")
    DeviceMonitorRecord selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "deviceMonitorRecordMapper", key = "'deviceMonitorRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(DeviceMonitorRecord entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "deviceMonitorRecordMapper", key = "'deviceMonitorRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(DeviceMonitorRecord entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceMonitorRecordMapper", key = "'deviceMonitorRecord:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceMonitorRecordMapper", key = "'deviceMonitorRecord:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceMonitorRecordMapper", key = "'deviceMonitorRecord:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceMonitorRecordMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
