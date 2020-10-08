package com.welearn.mapper;

import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import com.welearn.entity.qo.apply.EquipmentYearPlanApplicationQueryCondition;
import com.welearn.entity.qo.apply.EquipmentYearPlanQueryCondition;
import com.welearn.entity.vo.response.apply.EquipmentYearPlanApplicationInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * EquipmentYearPlanApplication Mapper Interface : ryme_apply : equipment_year_plan_application
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/28 11:12:52
 * @see com.welearn.entity.po.apply.EquipmentYearPlanApplication
 */
@Mapper 
public interface EquipmentYearPlanApplicationMapper extends BaseMapper<EquipmentYearPlanApplication, EquipmentYearPlanApplicationQueryCondition> {

    /**
     * 根据 查询条件 查询 设备计划申请信息
     * @param condition 查询条件
     * @return List<EquipmentYearPlanApplicationInfo>
     */
    List<EquipmentYearPlanApplicationInfo> searchInfo(EquipmentYearPlanApplicationQueryCondition condition);
}