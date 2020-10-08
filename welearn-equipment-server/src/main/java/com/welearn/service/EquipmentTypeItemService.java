package com.welearn.service;

import com.welearn.entity.po.equipment.EquipmentTypeItem;
import com.welearn.entity.qo.equipment.EquipmentTypeItemQueryCondition;
import com.welearn.entity.vo.response.equipment.EquipmentTypeBase;
import com.welearn.entity.vo.response.equipment.EquipmentTypeInfo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : EquipmentTypeItemService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentTypeItemService extends BaseService<EquipmentTypeItem, EquipmentTypeItemQueryCondition>{

    /**
     * 根据名称或编号查询设备类型详情
     * @param content 名称或编号
     * @return List<EquipmentTypeInfo>
     */
    List<EquipmentTypeInfo> searchInfoByNameOrId(@NotBlank String content);

    /**
     * 索引下一级分类名称列表
     * @param indexId 索引id
     * @return List<EquipmentTypeBase>
     */
    List<EquipmentTypeBase> first(@NotBlank String indexId);

    /**
     * 一级分类下二级分类名称列表
     * @param indexId 索引id
     * @param firstId 一级分类id
     * @return List<EquipmentTypeBase>
     */
    List<EquipmentTypeBase> second(@NotBlank String indexId, @NotBlank String firstId);

    /**
     * 二级分类下设备品名列表
     * @param indexId 索引id
     * @param firstId 一级分类id
     * @param secondId 二级分类
     * @return List<EquipmentTypeBase>
     */
    List<EquipmentTypeBase> items(@NotBlank String indexId, @NotBlank String firstId, @NotBlank String secondId);
}