package com.welearn.mapper;

import com.welearn.entity.dto.equipment.RepairCostStatistic;
import com.welearn.entity.po.equipment.RepairCostItem;
import com.welearn.entity.qo.equipment.RepairCostItemQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * RepairCostItem Mapper Interface : ryme_equipment : repair_cost_item
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:13:57
 * @see com.welearn.entity.po.equipment.RepairCostItem
 */
@Mapper 
public interface RepairCostItemMapper extends BaseMapper<RepairCostItem, RepairCostItemQueryCondition> {

    /**
     * 维修花费数据统计
     * id, sumPrice, departmentId, departmentName, createdAt
     * @param companyId 公司id
     * @return 统计数据
     */
    List<RepairCostStatistic> repairCostStatistic(@Param("companyId") String companyId,
                                                  @Param("startAt") Date startAt,
                                                  @Param("equipmentId") String equipmentId);

}