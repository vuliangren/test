package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.ReDeviceGroupDevice;
import com.welearn.entity.qo.alink.ReDeviceGroupDeviceQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * ReDeviceGroupDevice Mapper Interface : ryme_alink : re_device_group_device
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/1 11:26:39
 * @see com.welearn.entity.po.alink.ReDeviceGroupDevice
 */
@Mapper 
public interface ReDeviceGroupDeviceMapper extends BaseMapper<ReDeviceGroupDevice, ReDeviceGroupDeviceQueryCondition> {

    /**
     * 根据 groupId 删除相关关联关系
     * @param groupId 组id
     */
    void deleteByGroupId(@Param("groupId") String groupId);
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "reDeviceGroupDeviceMapper", key = "'reDeviceGroupDevice:'+#id", unless = "#result == null")
    ReDeviceGroupDevice selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reDeviceGroupDeviceMapper", key = "'reDeviceGroupDevice:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ReDeviceGroupDevice entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reDeviceGroupDeviceMapper", key = "'reDeviceGroupDevice:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ReDeviceGroupDevice entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reDeviceGroupDeviceMapper", key = "'reDeviceGroupDevice:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reDeviceGroupDeviceMapper", key = "'reDeviceGroupDevice:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reDeviceGroupDeviceMapper", key = "'reDeviceGroupDevice:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reDeviceGroupDeviceMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
