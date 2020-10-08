package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.RepairHelpQuotationApproval;
import com.welearn.entity.qo.equipment.RepairHelpQuotationApprovalQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * RepairHelpQuotationApproval Mapper Interface : ryme_equipment : repair_help_quotation_approval
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/11 14:37:02
 * @see com.welearn.entity.po.equipment.RepairHelpQuotationApproval
 */
@Mapper 
public interface RepairHelpQuotationApprovalMapper extends BaseMapper<RepairHelpQuotationApproval, RepairHelpQuotationApprovalQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "repairHelpQuotationApprovalMapper", key = "'repairHelpQuotationApproval:'+#id", unless = "#result == null")
    RepairHelpQuotationApproval selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "repairHelpQuotationApprovalMapper", key = "'repairHelpQuotationApproval:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(RepairHelpQuotationApproval entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "repairHelpQuotationApprovalMapper", key = "'repairHelpQuotationApproval:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(RepairHelpQuotationApproval entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairHelpQuotationApprovalMapper", key = "'repairHelpQuotationApproval:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairHelpQuotationApprovalMapper", key = "'repairHelpQuotationApproval:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairHelpQuotationApprovalMapper", key = "'repairHelpQuotationApproval:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "repairHelpQuotationApprovalMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
