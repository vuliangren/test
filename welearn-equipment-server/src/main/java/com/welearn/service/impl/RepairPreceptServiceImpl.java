package com.welearn.service.impl;

import com.welearn.dictionary.equipment.MaintenanceServeTypeConst;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.RepairPrecept;
import com.welearn.entity.qo.equipment.RepairPreceptQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.RepairPreceptMapper;
import com.welearn.service.EquipmentService;
import com.welearn.service.RepairPreceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.equipment.MaintenanceServeTypeConst.*;

/**
 * Description : RepairPreceptService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairPreceptServiceImpl extends BaseServiceImpl<RepairPrecept,RepairPreceptQueryCondition,RepairPreceptMapper>
        implements RepairPreceptService{
    
    @Autowired
    private RepairPreceptMapper repairPreceptMapper;

    @Autowired
    private EquipmentService equipmentService;
    
    @Override
    RepairPreceptMapper getMapper() {
        return repairPreceptMapper;
    }

    /**
     * 根据设备id 查询报修预案
     *
     * @param equipmentId 设备id
     * @return 报修预案列表
     */
    @Override
    public List<RepairPrecept> searchByEquipmentId(String equipmentId, String companyId) {
        Equipment equipment = equipmentService.select(equipmentId);
        if (Objects.isNull(equipment) || !equipment.getIsEnable())
            throw new BusinessVerifyFailedException("设备id 非法");
        List<RepairPrecept> repairPrecepts = new ArrayList<>();
        RepairPreceptQueryCondition condition = new RepairPreceptQueryCondition();
        condition.setCompanyId(companyId);
        condition.setIsEnable(true);
        // 查询适用全部的
        condition.setServeType(ALL.ordinal());
        repairPrecepts.addAll(this.search(condition));
        // 查询适用类型的
        condition.setServeType(EQUIPMENT_TYPE.ordinal());
        condition.setServeId(equipment.getEquipmentTypeId());
        repairPrecepts.addAll(this.search(condition));
        // 查询适用产品的
        condition.setServeType(EQUIPMENT_PRODUCT.ordinal());
        condition.setServeId(equipment.getEquipmentProductId());
        repairPrecepts.addAll(this.search(condition));
        // 查询适用设备的
        condition.setServeType(EQUIPMENT.ordinal());
        condition.setServeId(equipment.getId());
        repairPrecepts.addAll(this.search(condition));
        return repairPrecepts;
    }
}
