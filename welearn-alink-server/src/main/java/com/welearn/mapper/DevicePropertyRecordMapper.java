package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.DevicePropertyRecord;
import com.welearn.entity.qo.alink.DevicePropertyRecordQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * DevicePropertyRecord Mapper Interface : ryme_alink : device_property_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/25 17:30:03
 * @see com.welearn.entity.po.alink.DevicePropertyRecord
 */
@Mapper 
public interface DevicePropertyRecordMapper extends BaseMapper<DevicePropertyRecord, DevicePropertyRecordQueryCondition> {
    
    
    void deleteByReportedAtLessThan(@Param("reportedAt") Date reportedAt, @Param("type") Integer type);
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "devicePropertyRecordMapper", key = "'devicePropertyRecord:'+#id", unless = "#result == null")
    DevicePropertyRecord selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "devicePropertyRecordMapper", key = "'devicePropertyRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(DevicePropertyRecord entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "devicePropertyRecordMapper", key = "'devicePropertyRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(DevicePropertyRecord entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "devicePropertyRecordMapper", key = "'devicePropertyRecord:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "devicePropertyRecordMapper", key = "'devicePropertyRecord:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "devicePropertyRecordMapper", key = "'devicePropertyRecord:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "devicePropertyRecordMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
