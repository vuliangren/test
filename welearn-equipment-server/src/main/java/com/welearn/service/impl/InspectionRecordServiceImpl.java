package com.welearn.service.impl;

import com.welearn.entity.po.equipment.InspectionRecord;
import com.welearn.entity.qo.equipment.InspectionRecordQueryCondition;
import com.welearn.mapper.InspectionRecordMapper;
import com.welearn.service.InspectionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : InspectionRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class InspectionRecordServiceImpl extends BaseServiceImpl<InspectionRecord,InspectionRecordQueryCondition,InspectionRecordMapper>
        implements InspectionRecordService{
    
    @Autowired
    private InspectionRecordMapper inspectionRecordMapper;
    
    @Override
    InspectionRecordMapper getMapper() {
        return inspectionRecordMapper;
    }

}
