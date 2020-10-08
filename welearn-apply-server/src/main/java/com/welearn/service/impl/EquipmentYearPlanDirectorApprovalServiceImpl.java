package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.apply.EquipmentYearPlanStatusConst;
import com.welearn.entity.po.apply.EquipmentYearPlan;
import com.welearn.service.EquipmentYearPlanCommitteeApprovalService;
import com.welearn.service.EquipmentYearPlanDirectorApprovalService;
import com.welearn.service.EquipmentYearPlanService;
import com.welearn.service.LargeEquipmentApplicationHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : 院长 审批 年度计划
 * Created by Setsuna Jin on 2018/9/28.
 */
@Service
public class EquipmentYearPlanDirectorApprovalServiceImpl extends ApplicationServiceImpl<EquipmentYearPlan>
        implements EquipmentYearPlanDirectorApprovalService {

    @Autowired
    private EquipmentYearPlanService equipmentYearPlanService;

    @Autowired
    private LargeEquipmentApplicationHandlerService largeEquipmentApplicationHandlerService;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(EquipmentYearPlan content) {
        equipmentYearPlanService.create(content);
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        equipmentYearPlanService.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content       申请内容
     */
    @Override
    public void updateContent(EquipmentYearPlan content) {
        equipmentYearPlanService.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public EquipmentYearPlan selectContent(String contentId) {
        return equipmentYearPlanService.select(contentId);
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(EquipmentYearPlan content) {
        return content.getYear() + "年度设备计划";
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.EQUIPMENT_YEAR_PLAN_DIRECTOR_APPROVAL;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId 申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) {
        EquipmentYearPlan content = this.selectContent(contentId);
        content.setStatus(EquipmentYearPlanStatusConst.PURCHASING.ordinal());
        this.updateContent(content);
        // 执行年度设备计划
        equipmentYearPlanService.equipmentYearPlanExecute(content, applicationId, getApplicationType().getCode(), largeEquipmentApplicationHandlerService);
        // TODO: 通知采购负责人
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) {

    }
}
