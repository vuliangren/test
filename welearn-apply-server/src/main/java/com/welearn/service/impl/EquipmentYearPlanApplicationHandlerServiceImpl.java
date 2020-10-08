package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.apply.EquipmentYearPlanApplicationStatusConst;
import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import com.welearn.service.EquipmentYearPlanApplicationHandlerService;
import com.welearn.service.EquipmentYearPlanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : 年度设备计划申请
 * Created by Setsuna Jin on 2018/9/28.
 */
@Service
public class EquipmentYearPlanApplicationHandlerServiceImpl extends ApplicationServiceImpl<EquipmentYearPlanApplication>
        implements EquipmentYearPlanApplicationHandlerService {

    @Autowired
    private EquipmentYearPlanApplicationService equipmentYearPlanApplicationService;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(EquipmentYearPlanApplication content) {
        equipmentYearPlanApplicationService.create(content);
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        equipmentYearPlanApplicationService.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content       申请内容
     */
    @Override
    public void updateContent(EquipmentYearPlanApplication content) {
        equipmentYearPlanApplicationService.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public EquipmentYearPlanApplication selectContent(String contentId) {
        return equipmentYearPlanApplicationService.select(contentId);
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(EquipmentYearPlanApplication content) {
        return content.getEquipmentTypeName();
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.EQUIPMENT_YEAR_PLAN_APPLICATION;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId 申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) {
        EquipmentYearPlanApplication content = this.selectContent(contentId);
        content.setStatus(EquipmentYearPlanApplicationStatusConst.DEPARTMENT_PASS.ordinal());
        this.updateContent(content);
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) {
        EquipmentYearPlanApplication content = this.selectContent(contentId);
        content.setStatus(EquipmentYearPlanApplicationStatusConst.DEPARTMENT_FAILED.ordinal());
        this.updateContent(content);
    }
}
