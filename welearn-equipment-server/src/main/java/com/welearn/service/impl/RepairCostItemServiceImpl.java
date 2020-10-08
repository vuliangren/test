package com.welearn.service.impl;

import com.welearn.entity.po.equipment.RepairCostItem;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.qo.equipment.RepairCostItemQueryCondition;
import com.welearn.mapper.RepairCostItemMapper;
import com.welearn.service.RepairCostItemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : RepairCostItemService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairCostItemServiceImpl extends BaseServiceImpl<RepairCostItem,RepairCostItemQueryCondition,RepairCostItemMapper>
        implements RepairCostItemService{
    
    @Autowired
    private RepairCostItemMapper repairCostItemMapper;
    
    @Override
    RepairCostItemMapper getMapper() {
        return repairCostItemMapper;
    }

    /**
     * 创建维修配件消费项目
     *
     * @param replacement 维修配件更换
     * @param price       价格
     */
    @Override
    public void createSparePartCostItem(RepairReplacement replacement, Double price) {
        RepairCostItem costItem = new RepairCostItem();
        costItem.setRequestId(replacement.getRequestId());
        if (StringUtils.isNotBlank(replacement.getOutsideDispatchId())){
            costItem.setDispatchId(replacement.getOutsideDispatchId());
            costItem.setEngineerId(replacement.getOutsideEngineerId());
            costItem.setEngineerName(replacement.getOutsideEngineerName());
        } else {
            costItem.setDispatchId(replacement.getDispatchId());
            costItem.setEngineerId(replacement.getEngineerId());
            costItem.setEngineerName(replacement.getEngineerName());
        }
        costItem.setName(replacement.getName());
        costItem.setSpecification(replacement.getSpecification());
        costItem.setDescription(replacement.getDescription());
        costItem.setPrice(price);
        costItem.setCount(replacement.getCount());
        costItem.setSumPrice(replacement.getCount() * price);
        this.create(costItem);
    }
}
