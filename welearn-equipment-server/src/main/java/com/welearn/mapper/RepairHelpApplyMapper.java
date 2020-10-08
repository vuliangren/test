package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.RepairHelpApply;
import com.welearn.entity.qo.equipment.RepairHelpApplyQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * RepairHelpApply Mapper Interface : ryme_equipment : repair_help_apply
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/11 14:37:00
 * @see com.welearn.entity.po.equipment.RepairHelpApply
 */
@Mapper 
public interface RepairHelpApplyMapper extends BaseMapper<RepairHelpApply, RepairHelpApplyQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "repairHelpApplyMapper", key = "'repairHelpApply:'+#id", unless = "#result == null")
    RepairHelpApply selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "repairHelpApplyMapper", key = "'repairHelpApply:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(RepairHelpApply entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "repairHelpApplyMapper", key = "'repairHelpApply:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(RepairHelpApply entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairHelpApplyMapper", key = "'repairHelpApply:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairHelpApplyMapper", key = "'repairHelpApply:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairHelpApplyMapper", key = "'repairHelpApply:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairHelpApplyMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
