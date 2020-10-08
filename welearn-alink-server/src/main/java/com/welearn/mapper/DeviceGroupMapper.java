package com.welearn.mapper;

import com.welearn.dictionary.alink.DeviceGroupTypeConst;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.DeviceGroup;
import com.welearn.entity.qo.alink.DeviceGroupQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DeviceGroup Mapper Interface : ryme_alink : device_group
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/1 11:26:33
 * @see DeviceGroup
 */
@Mapper 
public interface DeviceGroupMapper extends BaseMapper<DeviceGroup, DeviceGroupQueryCondition> {

    /**
     * 根据设备的 IotId 和 所属分组的类型获取组成员
     * TODO: 对该SQL进行调优 添加相关索引
     * @param iotId iotId
     * @param groupType 分组类型
     * @see DeviceGroupTypeConst
     * @return 对应组成员用户id列表
     */
    List<String> selectGroupUserIdByIotIdAndGroupType(@Param("iotId") String iotId, @Param("groupType") Integer groupType);

    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "deviceGroupMapper", key = "'deviceGroup:'+#id", unless = "#result == null")
    DeviceGroup selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "deviceGroupMapper", key = "'deviceGroup:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(DeviceGroup entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "deviceGroupMapper", key = "'deviceGroup:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(DeviceGroup entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceGroupMapper", key = "'deviceGroup:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceGroupMapper", key = "'deviceGroup:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceGroupMapper", key = "'deviceGroup:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceGroupMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
