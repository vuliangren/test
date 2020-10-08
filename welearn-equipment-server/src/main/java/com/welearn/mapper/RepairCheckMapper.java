package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.RepairCheck;
import com.welearn.entity.qo.equipment.RepairCheckQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * RepairCheck Mapper Interface : ryme_equipment : repair_check
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/6 16:12:41
 * @see com.welearn.entity.po.equipment.RepairCheck
 */
@Mapper 
public interface RepairCheckMapper extends BaseMapper<RepairCheck, RepairCheckQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "repairCheckMapper", key = "'repairCheck:'+#id", unless = "#result == null")
    RepairCheck selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "repairCheckMapper", key = "'repairCheck:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(RepairCheck entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "repairCheckMapper", key = "'repairCheck:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(RepairCheck entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairCheckMapper", key = "'repairCheck:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairCheckMapper", key = "'repairCheck:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairCheckMapper", key = "'repairCheck:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairCheckMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
