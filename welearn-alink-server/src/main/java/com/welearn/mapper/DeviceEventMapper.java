package com.welearn.mapper;

import com.welearn.entity.vo.response.alink.DeviceEventTypeCount;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.DeviceEvent;
import com.welearn.entity.qo.alink.DeviceEventQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * DeviceEvent Mapper Interface : ryme_alink : device_event
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:38:44
 * @see com.welearn.entity.po.alink.DeviceEvent
 */
@Mapper 
public interface DeviceEventMapper extends BaseMapper<DeviceEvent, DeviceEventQueryCondition> {


    /**
     * 根据 IotId 和 类型 统计事件数量
     * @param typeGreaterThan 类型大于等于的值
     * @param shouldHandle 是否需要处理
     * @param handleStatus 处理状态
     * @return 统计数量结果
     */
    List<DeviceEventTypeCount> count(@Param("typeGreaterThan") Integer typeGreaterThan,
                                     @Param("shouldHandle") Boolean shouldHandle,
                                     @Param("handleStatus") Integer handleStatus,
                                     @Param("iotId") String iotId);

    /**
     * 根据时间和类型删除 事件数据
     * @param reportedAt 上报时间 以前的会被删除
     * @param type 事件类型
     */
    void deleteByReportedAtLessThan(@Param("reportedAt") Date reportedAt, @Param("type") Integer type);

    /**
     * 标记为全部不需要处理
     * @param iotId 设备id
     */
    void updateAllNoNeedHandle(@Param("iotId") String iotId);

    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "deviceEventMapper", key = "'deviceEvent:'+#id", unless = "#result == null")
    DeviceEvent selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "deviceEventMapper", key = "'deviceEvent:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(DeviceEvent entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "deviceEventMapper", key = "'deviceEvent:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(DeviceEvent entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceEventMapper", key = "'deviceEvent:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceEventMapper", key = "'deviceEvent:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceEventMapper", key = "'deviceEvent:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceEventMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
