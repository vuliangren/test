package com.welearn.service;

import com.welearn.entity.po.equipment.EquipmentGuaranteeRepairRecord;
import com.welearn.entity.qo.equipment.EquipmentGuaranteeRepairRecordQueryCondition;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

/**
 * Description : EquipmentGuaranteeRepairRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentGuaranteeRepairRecordService extends BaseService<EquipmentGuaranteeRepairRecord, EquipmentGuaranteeRepairRecordQueryCondition>{

    /**
     * 批量创建设备保修记录
     * @param equipmentGuaranteeRepairRecords 设备保修记录列表
     */
    List<EquipmentGuaranteeRepairRecord> batchCreate(List<EquipmentGuaranteeRepairRecord> equipmentGuaranteeRepairRecords);
}