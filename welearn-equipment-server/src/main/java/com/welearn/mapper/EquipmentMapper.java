package com.welearn.mapper;

import com.welearn.entity.dto.equipment.EquipmentStatusStatistic;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.qo.equipment.EquipmentQueryCondition;
import com.welearn.entity.vo.response.equipment.EquipmentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Equipment Mapper Interface : ryme_equipment : equipment
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/11/10 10:22:57
 * @see com.welearn.entity.po.equipment.Equipment
 */
@Mapper 
public interface EquipmentMapper extends BaseMapper<Equipment, EquipmentQueryCondition> {

    /**
     * 查询设备的粗略数据信息
     * @param condition 查询条件
     * @return List<Equipment> 仅部分字段
     */
    List<Equipment> searchOutlook(EquipmentQueryCondition condition);

    /**
     * 查询设备详情信息
     * @param condition 查询条件
     * @return List<EquipmentInfo>
     */
    List<EquipmentInfo> searchInfo(EquipmentQueryCondition condition);

    /**
     * 查询部门设备简略信息
     * @param condition 查询条件
     * @return List<EquipmentInfo> 仅部分字段
     */
    List<EquipmentInfo> searchDepartmentEquipment(EquipmentQueryCondition condition);


    /**
     * 设备数量统计
     * id, equipmentStatus, serviceStatus, departmentId, departmentName
     * @param companyId 公司id
     * @return 统计数据
     */
    List<EquipmentStatusStatistic> equipmentStatusStatistic(@Param("companyId") String companyId);
}