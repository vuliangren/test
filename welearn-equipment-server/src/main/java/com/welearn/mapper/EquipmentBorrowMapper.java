package com.welearn.mapper;

import com.welearn.entity.vo.response.equipment.EquipmentBorrowInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.qo.equipment.EquipmentBorrowQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * EquipmentBorrow Mapper Interface : ryme_equipment : equipment_borrow
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/26 16:20:18
 * @see EquipmentBorrow
 */
@Mapper 
public interface EquipmentBorrowMapper extends BaseMapper<EquipmentBorrow, EquipmentBorrowQueryCondition> {


    /**
     * 查询设备的借出记录(借出过的记录)
     * @param equipmentId 设备id
     * @return 借出记录数据
     */
    List<EquipmentBorrow> borrowStatistic(@Param("equipmentId") String equipmentId);

    /**
     * 根据条件查询设备详情
     * @param condition 查询条件
     * @return 设备借用信息和设备信息
     */
    List<EquipmentBorrowInfo> searchInfo(EquipmentBorrowQueryCondition condition);
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "equipmentBorrowMapper", key = "'equipmentBorrow:'+#id", unless = "#result == null")
    EquipmentBorrow selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "equipmentBorrowMapper", key = "'equipmentBorrow:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(EquipmentBorrow entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "equipmentBorrowMapper", key = "'equipmentBorrow:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(EquipmentBorrow entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentBorrowMapper", key = "'equipmentBorrow:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentBorrowMapper", key = "'equipmentBorrow:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentBorrowMapper", key = "'equipmentBorrow:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "equipmentBorrowMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
