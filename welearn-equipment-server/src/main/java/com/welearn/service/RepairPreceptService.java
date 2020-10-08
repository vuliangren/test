package com.welearn.service;

import com.welearn.entity.po.equipment.RepairPrecept;
import com.welearn.entity.qo.equipment.RepairPreceptQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : RepairPreceptService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairPreceptService extends BaseService<RepairPrecept, RepairPreceptQueryCondition>{

    /**
     * 根据设备id 查询报修预案
     * @param equipmentId 设备id
     * @return 报修预案列表
     */
    List<RepairPrecept> searchByEquipmentId(@NotBlank String equipmentId, @NotBlank String companyId);
}