package com.welearn.service.impl;

import com.welearn.entity.po.equipment.EquipmentTypeItem;
import com.welearn.entity.qo.equipment.EquipmentTypeItemQueryCondition;
import com.welearn.entity.vo.response.equipment.EquipmentTypeBase;
import com.welearn.entity.vo.response.equipment.EquipmentTypeInfo;
import com.welearn.mapper.EquipmentTypeItemMapper;
import com.welearn.service.EquipmentTypeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : EquipmentTypeItemService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentTypeItemServiceImpl extends BaseServiceImpl<EquipmentTypeItem,EquipmentTypeItemQueryCondition,EquipmentTypeItemMapper>
        implements EquipmentTypeItemService{
    
    @Autowired
    private EquipmentTypeItemMapper equipmentTypeItemMapper;
    
    @Override
    EquipmentTypeItemMapper getMapper() {
        return equipmentTypeItemMapper;
    }

    /**
     * 根据名称或编号查询设备类型详情
     *
     * @param content 名称或编号
     * @return List<EquipmentTypeInfo>
     */
    @Override
    public List<EquipmentTypeInfo> searchInfoByNameOrId(String content) {
        return equipmentTypeItemMapper.selectInfoByNameOrId(content);
    }

    /**
     * 索引下一级分类名称列表
     *
     * @param indexId 索引id
     * @return List<EquipmentTypeBase>
     */
    @Override
    @Cacheable(value = "equipmentType", key = "'equipmentType:'+#indexId")
    public List<EquipmentTypeBase> first(String indexId) {
        return equipmentTypeItemMapper.selectFirst(indexId);
    }

    /**
     * 一级分类下二级分类名称列表
     *
     * @param indexId 索引id
     * @param firstId 一级分类id
     * @return List<EquipmentTypeBase>
     */
    @Override
    @Cacheable(value = "equipmentType", key = "'equipmentType:'+#indexId+#firstId")
    public List<EquipmentTypeBase> second(String indexId, String firstId) {
        return equipmentTypeItemMapper.selectSecond(indexId, firstId);
    }

    /**
     * 二级分类下设备品名列表
     *
     * @param indexId  索引id
     * @param firstId  一级分类id
     * @param secondId 二级分类
     * @return List<EquipmentTypeBase>
     */
    @Override
    @Cacheable(value = "equipmentType", key = "'equipmentType:'+#indexId+#firstId+#secondId")
    public List<EquipmentTypeBase> items(String indexId, String firstId, String secondId) {
        return equipmentTypeItemMapper.selectItems(indexId, firstId, secondId);
    }
}
