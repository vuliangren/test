package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.MaintenanceCostItem;
import com.welearn.entity.qo.equipment.MaintenanceCostItemQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * MaintenanceCostItem Mapper Interface : ryme_equipment : maintenance_cost_item
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 16:20:34
 * @see MaintenanceCostItem
 */
@Mapper 
public interface MaintenanceCostItemMapper extends BaseMapper<MaintenanceCostItem, MaintenanceCostItemQueryCondition> {
    
    
    
    
    
    List<MaintenanceCostItem> maintenanceCostItemStatistic(@Param("companyId") String companyId,
                                                           @Param("startAt") Date startAt,
                                                           @Param("equipmentId") String equipmentId);
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "maintenanceCostItemMapper", key = "'maintenanceCostItem:'+#id", unless = "#result == null")
    MaintenanceCostItem selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "maintenanceCostItemMapper", key = "'maintenanceCostItem:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(MaintenanceCostItem entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "maintenanceCostItemMapper", key = "'maintenanceCostItem:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(MaintenanceCostItem entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "maintenanceCostItemMapper", key = "'maintenanceCostItem:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "maintenanceCostItemMapper", key = "'maintenanceCostItem:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "maintenanceCostItemMapper", key = "'maintenanceCostItem:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "maintenanceCostItemMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
