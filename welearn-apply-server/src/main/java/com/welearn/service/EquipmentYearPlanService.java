package com.welearn.service;

import com.welearn.entity.po.apply.EquipmentYearPlan;
import com.welearn.entity.qo.apply.EquipmentYearPlanQueryCondition;
import com.welearn.entity.vo.response.apply.EquipmentYearPlanApplicationInfo;
import org.springframework.validation.annotation.Validated;

/**
 * Description : EquipmentYearPlanService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentYearPlanService extends BaseService<EquipmentYearPlan, EquipmentYearPlanQueryCondition>{

    /**
     * 根据 公司id 获取其当年的年度设备计划
     * 如为医院类型公司而没有年度设备计划则创建一个并返回
     * @param companyId 公司id
     * @return 当前的年度设备计划
     */
    EquipmentYearPlan current(String companyId);

    /**
     * 将 年度设备计划 提交给 装备委员会评审
     * 默认会有 定时任务 按时执行 也可手动触发
     * @param equipmentYearPlanId 年度设备计划id
     * @param userId 设备科主任id
     */
    void committeeApproval(String equipmentYearPlanId, String userId);

    /**
     * 年度设备计划 执行
     * 将 年度设备计划 包含的申请 转换为 待采购项
     * 如为大型医疗设备 则 创建 大型医疗设备装配申请
     * @param equipmentYearPlan 年度设备计划
     * @param applicationId 申请id
     * @param applicationType 申请类型编码
     */
    void equipmentYearPlanExecute(EquipmentYearPlan equipmentYearPlan, String applicationId,
                                  String applicationType, LargeEquipmentApplicationHandlerService service);

    /**
     * 年度设备计划申请 执行
     * 将 年度设备计划申请 转换为 待采购项
     * @param application 年度设备计划申请信息
     * @param applicationId 申请id
     * @param applicationType 申请类型编码
     */
    void equipmentYearPlanApplicationExecute(EquipmentYearPlanApplicationInfo application, String applicationId,
                                             String applicationType);
}