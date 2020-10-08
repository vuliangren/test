package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.common.Room;
import com.welearn.entity.qo.common.RoomQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * Room Mapper Interface : ryme_common : room
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/16 18:24:38
 * @see Room
 */
@Mapper 
public interface RoomMapper extends BaseMapper<Room, RoomQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "roomMapper", key = "'room:'+#id", unless = "#result == null")
    Room selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "roomMapper", key = "'room:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(Room entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "roomMapper", key = "'room:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(Room entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "roomMapper", key = "'room:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "roomMapper", key = "'room:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "roomMapper", key = "'room:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "roomMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
