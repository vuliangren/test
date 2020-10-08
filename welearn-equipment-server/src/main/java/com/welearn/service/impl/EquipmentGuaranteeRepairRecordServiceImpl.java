package com.welearn.service.impl;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentGuaranteeRepairRecord;
import com.welearn.entity.qo.equipment.EquipmentGuaranteeRepairRecordQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.EquipmentGuaranteeRepairRecordMapper;
import com.welearn.mapper.EquipmentMapper;
import com.welearn.service.EquipmentGuaranteeRepairRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description : EquipmentGuaranteeRepairRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentGuaranteeRepairRecordServiceImpl extends BaseServiceImpl<EquipmentGuaranteeRepairRecord,EquipmentGuaranteeRepairRecordQueryCondition,EquipmentGuaranteeRepairRecordMapper>
        implements EquipmentGuaranteeRepairRecordService{

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private EquipmentGuaranteeRepairRecordMapper equipmentGuaranteeRepairRecordMapper;
    
    @Override
    EquipmentGuaranteeRepairRecordMapper getMapper() {
        return equipmentGuaranteeRepairRecordMapper;
    }


    @Override @Transactional
    public EquipmentGuaranteeRepairRecord create(EquipmentGuaranteeRepairRecord equipmentGuaranteeRepairRecord){
        Date startAt = equipmentGuaranteeRepairRecord.getStartAt();
        Date endAt = equipmentGuaranteeRepairRecord.getEndAt();
        if (Objects.isNull(startAt) || Objects.isNull(endAt) || endAt.getTime() < startAt.getTime() || endAt.getTime() < new Date().getTime())
            throw new BusinessVerifyFailedException("startAt 或 endAt 非法");
        Equipment equipment = equipmentMapper.selectByPK(equipmentGuaranteeRepairRecord.getEquipmentId());
        if (Objects.isNull(equipment) || !equipment.getIsEnable())
            throw new BusinessVerifyFailedException("equipmentId 非法");
        // 更新设备的保修过期时间
        Date current = equipment.getGuaranteeRepairExpiredAt();
        if (Objects.isNull(current) || current.getTime() < endAt.getTime()){
            equipment.setGuaranteeRepairExpiredAt(endAt);
            equipmentMapper.updateByPK(equipment);
        }
        return super.create(equipmentGuaranteeRepairRecord);
    }

    /**
     * 批量创建设备保修记录
     *
     * @param equipmentGuaranteeRepairRecords 设备保修记录列表
     */
    @Override @Transactional
    public List<EquipmentGuaranteeRepairRecord> batchCreate(List<EquipmentGuaranteeRepairRecord> equipmentGuaranteeRepairRecords) {
        for (EquipmentGuaranteeRepairRecord equipmentGuaranteeRepairRecord : equipmentGuaranteeRepairRecords) {
            this.create(equipmentGuaranteeRepairRecord);
        }
        return equipmentGuaranteeRepairRecords;
    }
}
