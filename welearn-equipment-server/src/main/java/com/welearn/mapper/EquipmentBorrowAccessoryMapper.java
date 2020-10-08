package com.welearn.mapper;

import com.welearn.entity.vo.response.equipment.EquipmentBorrowAccessoryInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.EquipmentBorrowAccessory;
import com.welearn.entity.qo.equipment.EquipmentBorrowAccessoryQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * EquipmentBorrowAccessory Mapper Interface : ryme_equipment : equipment_borrow_accessory
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 16:20:19
 * @see EquipmentBorrowAccessory
 */
@Mapper 
public interface EquipmentBorrowAccessoryMapper extends BaseMapper<EquipmentBorrowAccessory, EquipmentBorrowAccessoryQueryCondition> {

    /**
     * 查询设备借用附件详情信息
     * @param condition 附件借用查询条件
     * @return 设备附件及附件借用信息
     */
    List<EquipmentBorrowAccessoryInfo> searchInfo(EquipmentBorrowAccessoryQueryCondition condition);
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "equipmentBorrowAccessoryMapper", key = "'equipmentBorrowAccessory:'+#id", unless = "#result == null")
    EquipmentBorrowAccessory selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "equipmentBorrowAccessoryMapper", key = "'equipmentBorrowAccessory:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(EquipmentBorrowAccessory entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "equipmentBorrowAccessoryMapper", key = "'equipmentBorrowAccessory:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(EquipmentBorrowAccessory entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentBorrowAccessoryMapper", key = "'equipmentBorrowAccessory:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentBorrowAccessoryMapper", key = "'equipmentBorrowAccessory:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentBorrowAccessoryMapper", key = "'equipmentBorrowAccessory:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentBorrowAccessoryMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
