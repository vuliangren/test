package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.Device;
import com.welearn.entity.qo.alink.DeviceQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Device Mapper Interface : ryme_alink : device
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:38:42
 * @see com.welearn.entity.po.alink.Device
 */
@Mapper 
public interface DeviceMapper extends BaseMapper<Device, DeviceQueryCondition> {

    /**
     * 根据iotId 获取设备信息
     * @param iotId iotId
     * @return 设备信息
     */
    Device selectByIotId(@Param("iotId") String iotId);

    /**
     * 根据id 或 iotId 更新设备信息
     * @param device 设备信息
     */
    void updateSelective(Device device);

    /**
     * 查询所有设备的 iotId 列表
     * @return iotId 列表
     */
    List<String> selectIotIds();

    /**
     * 获取所有设备的简略信息列表
     * @return 设备简略信息
     */
    List<Device> selectOutlook();
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "deviceMapper", key = "'device:'+#id", unless = "#result == null")
    Device selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "deviceMapper", key = "'device:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(Device entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "deviceMapper", key = "'device:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(Device entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceMapper", key = "'device:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceMapper", key = "'device:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceMapper", key = "'device:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "deviceMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
