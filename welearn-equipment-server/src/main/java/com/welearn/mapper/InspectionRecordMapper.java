package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.equipment.InspectionRecord;
import com.welearn.entity.qo.equipment.InspectionRecordQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * InspectionRecord Mapper Interface : ryme_equipment : inspection_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/12 14:55:48
 * @see com.welearn.entity.po.equipment.InspectionRecord
 */
@Mapper 
public interface InspectionRecordMapper extends BaseMapper<InspectionRecord, InspectionRecordQueryCondition> {


    /**
     * 查询设备拥有的记录数
     * @param equipmentId 设备id
     * @return 数量
     */
    Integer countByEquipmentId(@Param("equipmentId") String equipmentId);
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "inspectionRecordMapper", key = "'inspectionRecord:'+#id", unless = "#result == null")
    InspectionRecord selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "inspectionRecordMapper", key = "'inspectionRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(InspectionRecord entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "inspectionRecordMapper", key = "'inspectionRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(InspectionRecord entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "inspectionRecordMapper", key = "'inspectionRecord:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "inspectionRecordMapper", key = "'inspectionRecord:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "inspectionRecordMapper", key = "'inspectionRecord:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "inspectionRecordMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
