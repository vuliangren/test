package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.RepairInterrupt;
import com.welearn.entity.qo.equipment.RepairInterruptQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * RepairInterrupt Mapper Interface : ryme_equipment : repair_interrupt
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/6 21:29:36
 * @see com.welearn.entity.po.equipment.RepairInterrupt
 */
@Mapper 
public interface RepairInterruptMapper extends BaseMapper<RepairInterrupt, RepairInterruptQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "repairInterruptMapper", key = "'repairInterrupt:'+#id", unless = "#result == null")
    RepairInterrupt selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "repairInterruptMapper", key = "'repairInterrupt:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(RepairInterrupt entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "repairInterruptMapper", key = "'repairInterrupt:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(RepairInterrupt entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairInterruptMapper", key = "'repairInterrupt:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairInterruptMapper", key = "'repairInterrupt:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairInterruptMapper", key = "'repairInterrupt:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairInterruptMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
