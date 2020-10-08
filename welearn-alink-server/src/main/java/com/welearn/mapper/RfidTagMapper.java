package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.RfidTag;
import com.welearn.entity.qo.alink.RfidTagQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * RfidTag Mapper Interface : ryme_alink : rfid_tag
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/16 16:51:57
 * @see com.welearn.entity.po.alink.RfidTag
 */
@Mapper 
public interface RfidTagMapper extends BaseMapper<RfidTag, RfidTagQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "rfidTagMapper", key = "'rfidTag:'+#id", unless = "#result == null")
    RfidTag selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "rfidTagMapper", key = "'rfidTag:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(RfidTag entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "rfidTagMapper", key = "'rfidTag:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(RfidTag entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "rfidTagMapper", key = "'rfidTag:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "rfidTagMapper", key = "'rfidTag:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "rfidTagMapper", key = "'rfidTag:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "rfidTagMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
