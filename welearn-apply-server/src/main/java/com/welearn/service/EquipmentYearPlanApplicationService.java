package com.welearn.service;

import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import com.welearn.entity.qo.apply.EquipmentYearPlanApplicationQueryCondition;
import com.welearn.entity.vo.response.apply.EquipmentYearPlanApplicationInfo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : EquipmentYearPlanApplicationService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentYearPlanApplicationService extends BaseService<EquipmentYearPlanApplication, EquipmentYearPlanApplicationQueryCondition>{

    /**
     * 根据 查询条件 查询 设备计划申请信息
     * @param condition 查询条件
     * @return List<EquipmentYearPlanApplicationInfo>
     */
    List<EquipmentYearPlanApplicationInfo> searchInfo(@NotNull EquipmentYearPlanApplicationQueryCondition condition);

    /**
     * 装备委员会评审 设备计划申请
     * @param equipmentYearPlanApplicationId 设备计划申请id
     * @param isPassed 是否通过
     * @param resultJson 未通过原因 委员会通过与否决人数 等 Json信息
     */
    void committeeApproval(@NotNull String equipmentYearPlanApplicationId, @NotNull Boolean isPassed, String resultJson);

    /**
     * 院长评审 设备计划申请
     * @param equipmentYearPlanApplicationId 设备计划申请id
     * @param isPassed 是否通过
     * @param resultJson 未通过原因 Json信息
     */
    void directorApproval(@NotNull String equipmentYearPlanApplicationId, @NotNull Boolean isPassed, String resultJson);
}