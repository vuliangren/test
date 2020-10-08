package com.welearn.service.impl;

import com.welearn.entity.po.equipment.ReEquipmentEquipment;
import com.welearn.entity.qo.equipment.ReEquipmentEquipmentQueryCondition;
import com.welearn.mapper.ReEquipmentEquipmentMapper;
import com.welearn.service.ReEquipmentEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : ReEquipmentEquipmentService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReEquipmentEquipmentServiceImpl extends BaseServiceImpl<ReEquipmentEquipment,ReEquipmentEquipmentQueryCondition,ReEquipmentEquipmentMapper>
        implements ReEquipmentEquipmentService{
    
    @Autowired
    private ReEquipmentEquipmentMapper reEquipmentEquipmentMapper;
    
    @Override
    ReEquipmentEquipmentMapper getMapper() {
        return reEquipmentEquipmentMapper;
    }

    @Override
    public ReEquipmentEquipment create(ReEquipmentEquipment reEquipmentEquipment) {
        ReEquipmentEquipmentQueryCondition condition = new ReEquipmentEquipmentQueryCondition();
        condition.setFromEquipmentId(reEquipmentEquipment.getFromEquipmentId());
        condition.setToEquipmentId(reEquipmentEquipment.getToEquipmentId());
        List<ReEquipmentEquipment> search = this.search(condition);
        if (search.isEmpty())
            return super.create(reEquipmentEquipment);
        else
            return search.get(0);
    }
}
