package com.welearn.service;

import com.welearn.entity.po.equipment.InspectionRecord;
import com.welearn.entity.qo.equipment.InspectionRecordQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : InspectionRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface InspectionRecordService extends BaseService<InspectionRecord, InspectionRecordQueryCondition>{

}