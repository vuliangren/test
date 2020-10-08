package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.ReRfidTagDevice;
import com.welearn.entity.qo.alink.ReRfidTagDeviceQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * ReRfidTagDevice Mapper Interface : ryme_alink : re_rfid_tag_device
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/16 16:51:55
 * @see com.welearn.entity.po.alink.ReRfidTagDevice
 */
@Mapper 
public interface ReRfidTagDeviceMapper extends BaseMapper<ReRfidTagDevice, ReRfidTagDeviceQueryCondition> {
    
    void deleteByTagId(@Param("tagId") String tagId);
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "reRfidTagDeviceMapper", key = "'reRfidTagDevice:'+#id", unless = "#result == null")
    ReRfidTagDevice selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reRfidTagDeviceMapper", key = "'reRfidTagDevice:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ReRfidTagDevice entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reRfidTagDeviceMapper", key = "'reRfidTagDevice:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ReRfidTagDevice entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRfidTagDeviceMapper", key = "'reRfidTagDevice:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRfidTagDeviceMapper", key = "'reRfidTagDevice:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRfidTagDeviceMapper", key = "'reRfidTagDevice:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRfidTagDeviceMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
