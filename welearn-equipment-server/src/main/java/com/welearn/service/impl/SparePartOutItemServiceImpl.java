package com.welearn.service.impl;

import com.welearn.dictionary.equipment.SparePartOutItemStatusConst;
import com.welearn.entity.po.equipment.SparePartOutItem;
import com.welearn.entity.qo.equipment.SparePartOutItemQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.SparePartOutItemMapper;
import com.welearn.service.SparePartOutItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description : SparePartOutItemService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SparePartOutItemServiceImpl extends BaseServiceImpl<SparePartOutItem,SparePartOutItemQueryCondition,SparePartOutItemMapper>
        implements SparePartOutItemService{
    
    @Autowired
    private SparePartOutItemMapper sparePartOutItemMapper;
    
    @Override
    SparePartOutItemMapper getMapper() {
        return sparePartOutItemMapper;
    }

    /**
     * 批量创建
     *
     * @param items 出库项列表
     */
    @Override
    public void batchCreate(List<SparePartOutItem> items) {
        for (SparePartOutItem item : items) {
            this.create(item);
        }
    }
}
