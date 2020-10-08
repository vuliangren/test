package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.ReEquipmentEquipment;
import com.welearn.entity.qo.equipment.ReEquipmentEquipmentQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * ReEquipmentEquipment Mapper Interface : ryme_equipment : re_equipment_equipment
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/9 19:34:13
 * @see com.welearn.entity.po.equipment.ReEquipmentEquipment
 */
@Mapper 
public interface ReEquipmentEquipmentMapper extends BaseMapper<ReEquipmentEquipment, ReEquipmentEquipmentQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "reEquipmentEquipmentMapper", key = "'reEquipmentEquipment:'+#id", unless = "#result == null")
    ReEquipmentEquipment selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reEquipmentEquipmentMapper", key = "'reEquipmentEquipment:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ReEquipmentEquipment entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reEquipmentEquipmentMapper", key = "'reEquipmentEquipment:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ReEquipmentEquipment entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reEquipmentEquipmentMapper", key = "'reEquipmentEquipment:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reEquipmentEquipmentMapper", key = "'reEquipmentEquipment:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reEquipmentEquipmentMapper", key = "'reEquipmentEquipment:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reEquipmentEquipmentMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
