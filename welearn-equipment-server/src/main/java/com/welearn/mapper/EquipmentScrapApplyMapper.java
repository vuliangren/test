package com.welearn.mapper;

import com.welearn.entity.vo.response.equipment.EquipmentScrapInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.EquipmentScrapApply;
import com.welearn.entity.qo.equipment.EquipmentScrapApplyQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * EquipmentScrapApply Mapper Interface : ryme_equipment : equipment_scrap_apply
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 16:20:25
 * @see EquipmentScrapApply
 */
@Mapper 
public interface EquipmentScrapApplyMapper extends BaseMapper<EquipmentScrapApply, EquipmentScrapApplyQueryCondition> {


    /**
     * 根据条件查询设备报废信息
     * @param condition 查询条件
     * @return 设备报废申请和设备信息
     */
    List<EquipmentScrapInfo> searchInfo(EquipmentScrapApplyQueryCondition condition);
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "equipmentScrapApplyMapper", key = "'equipmentScrapApply:'+#id", unless = "#result == null")
    EquipmentScrapApply selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "equipmentScrapApplyMapper", key = "'equipmentScrapApply:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(EquipmentScrapApply entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "equipmentScrapApplyMapper", key = "'equipmentScrapApply:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(EquipmentScrapApply entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentScrapApplyMapper", key = "'equipmentScrapApply:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentScrapApplyMapper", key = "'equipmentScrapApply:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentScrapApplyMapper", key = "'equipmentScrapApply:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentScrapApplyMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
