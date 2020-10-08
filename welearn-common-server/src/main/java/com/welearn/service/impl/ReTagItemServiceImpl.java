package com.welearn.service.impl;

import com.welearn.entity.po.common.ReTagItem;
import com.welearn.entity.qo.common.ReTagItemQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.ReTagItemMapper;
import com.welearn.service.ReTagItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : ReTagItemService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReTagItemServiceImpl extends BaseServiceImpl<ReTagItem,ReTagItemQueryCondition,ReTagItemMapper>
        implements ReTagItemService{
    
    @Autowired
    private ReTagItemMapper reTagItemMapper;
    
    @Override
    ReTagItemMapper getMapper() {
        return reTagItemMapper;
    }

    @Override
    public ReTagItem create(ReTagItem reTagItem) {
        ReTagItemQueryCondition condition = new ReTagItemQueryCondition();
        condition.setIsEnable(true);
        condition.setTagId(reTagItem.getTagId());
        condition.setItemId(reTagItem.getItemId());
        List<ReTagItem> search = this.search(condition);
        if (search.isEmpty())
            return super.create(reTagItem);
        else
            throw new BusinessVerifyFailedException("标签和数据已有关联, 请勿重复关联");
    }
}
