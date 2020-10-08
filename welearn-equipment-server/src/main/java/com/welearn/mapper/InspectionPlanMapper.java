package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.InspectionPlan;
import com.welearn.entity.qo.equipment.InspectionPlanQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * InspectionPlan Mapper Interface : ryme_equipment : inspection_plan
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/12 14:55:47
 * @see com.welearn.entity.po.equipment.InspectionPlan
 */
@Mapper 
public interface InspectionPlanMapper extends BaseMapper<InspectionPlan, InspectionPlanQueryCondition> {

    /**
     * 根据公司id 获取当前数量
     * @param companyId 公司id
     * @return 数量
     */
    Integer countByCompanyId(@Param("companyId") String companyId);
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "inspectionPlanMapper", key = "'inspectionPlan:'+#id", unless = "#result == null")
    InspectionPlan selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "inspectionPlanMapper", key = "'inspectionPlan:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(InspectionPlan entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "inspectionPlanMapper", key = "'inspectionPlan:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(InspectionPlan entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "inspectionPlanMapper", key = "'inspectionPlan:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "inspectionPlanMapper", key = "'inspectionPlan:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "inspectionPlanMapper", key = "'inspectionPlan:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "inspectionPlanMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
