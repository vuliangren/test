package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.ReDeviceGroupUser;
import com.welearn.entity.qo.alink.ReDeviceGroupUserQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ReDeviceGroupUser Mapper Interface : ryme_alink : re_device_group_user
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/1 11:26:41
 * @see com.welearn.entity.po.alink.ReDeviceGroupUser
 */
@Mapper 
public interface ReDeviceGroupUserMapper extends BaseMapper<ReDeviceGroupUser, ReDeviceGroupUserQueryCondition> {


    /**
     * 根据 groupId 删除相关关联关系
     * @param groupId 组id
     */
    void deleteByGroupId(@Param("groupId") String groupId);

    /**
     * 查询设备相关分组所关联的用户信息
     * @param iotId 设备 iotId
     * @param groupType 分组类型
     * @return 用户信息
     */
    List<ReDeviceGroupUser> selectByIotIdAndGroupType(@Param("iotId") String iotId, @Param("groupType") Integer groupType);
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "reDeviceGroupUserMapper", key = "'reDeviceGroupUser:'+#id", unless = "#result == null")
    ReDeviceGroupUser selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reDeviceGroupUserMapper", key = "'reDeviceGroupUser:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ReDeviceGroupUser entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reDeviceGroupUserMapper", key = "'reDeviceGroupUser:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ReDeviceGroupUser entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reDeviceGroupUserMapper", key = "'reDeviceGroupUser:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reDeviceGroupUserMapper", key = "'reDeviceGroupUser:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reDeviceGroupUserMapper", key = "'reDeviceGroupUser:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reDeviceGroupUserMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
