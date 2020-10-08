package com.welearn.mapper;

import com.welearn.entity.dto.equipment.RepairCostStatistic;
import com.welearn.entity.dto.equipment.RepairRequestStatistic;
import com.welearn.entity.po.equipment.RepairRequest;
import com.welearn.entity.qo.equipment.RepairRequestQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * RepairRequest Mapper Interface : ryme_equipment : repair_request
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:14:03
 * @see com.welearn.entity.po.equipment.RepairRequest
 */
@Mapper 
public interface RepairRequestMapper extends BaseMapper<RepairRequest, RepairRequestQueryCondition> {
    /**
     * 报修数量统计
     * id, status, departmentId, departmentName, createdAt, finishedAt, isHelpApply, isWarranty
     * @param companyId 公司id
     * @return 统计数据
     */
    List<RepairRequestStatistic> repairCostStatistic(@Param("companyId") String companyId, @Param("startAt") Date startAt, @Param("equipmentId") String equipmentId);
}