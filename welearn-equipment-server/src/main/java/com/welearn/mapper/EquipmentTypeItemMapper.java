package com.welearn.mapper;

import com.welearn.entity.po.equipment.EquipmentTypeItem;
import com.welearn.entity.qo.equipment.EquipmentTypeItemQueryCondition;
import com.welearn.entity.vo.response.equipment.EquipmentTypeBase;
import com.welearn.entity.vo.response.equipment.EquipmentTypeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EquipmentTypeItem Mapper Interface : ryme_equipment : equipment_type_item
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/19 14:01:59
 * @see com.welearn.entity.po.equipment.EquipmentTypeItem
 */
@Mapper 
public interface EquipmentTypeItemMapper extends BaseMapper<EquipmentTypeItem, EquipmentTypeItemQueryCondition> {
    /**
     * 根据名称或编号查询设备类型详情
     * @param content 名称或编号
     * @return List<EquipmentTypeInfo>
     */
    List<EquipmentTypeInfo> selectInfoByNameOrId(@Param("content") String content);

    /**
     * 索引下一级分类名称列表
     * @param indexId 索引id
     * @return List<EquipmentTypeBase>
     */
    List<EquipmentTypeBase> selectFirst(@Param("indexId") String indexId);

    /**
     * 一级分类下二级分类名称列表
     * @param indexId 索引id
     * @param firstId 一级分类id
     * @return List<EquipmentTypeBase>
     */
    List<EquipmentTypeBase> selectSecond(@Param("indexId") String indexId, @Param("firstId") String firstId);

    /**
     * 二级分类下设备品名列表
     * @param indexId 索引id
     * @param firstId 一级分类id
     * @param secondId 二级分类
     * @return List<EquipmentTypeBase>
     */
    List<EquipmentTypeBase> selectItems(@Param("indexId") String indexId, @Param("firstId") String firstId, @Param("secondId") String secondId);
}