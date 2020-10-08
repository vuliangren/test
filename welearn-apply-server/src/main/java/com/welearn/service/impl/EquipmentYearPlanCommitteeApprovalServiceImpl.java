package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.apply.EquipmentYearPlanStatusConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.apply.EquipmentYearPlan;
import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import com.welearn.entity.qo.apply.EquipmentYearPlanApplicationQueryCondition;
import com.welearn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.welearn.dictionary.apply.EquipmentYearPlanApplicationStatusConst.*;

/**
 * Description : 医疗设备装备委员会 审批 年度计划
 * Created by Setsuna Jin on 2018/9/28.
 */
@Service
public class EquipmentYearPlanCommitteeApprovalServiceImpl extends ApplicationServiceImpl<EquipmentYearPlan>
        implements EquipmentYearPlanCommitteeApprovalService {

    @Autowired
    private EquipmentYearPlanService equipmentYearPlanService;

    @Autowired
    private ApprovalApplicationService approvalApplicationService;

    @Autowired
    private EquipmentYearPlanApplicationService equipmentYearPlanApplicationService;

    @Autowired
    private EquipmentYearPlanDirectorApprovalService directorApprovalService;

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
        return ApplicationHandleTypeConst.EQUIPMENT_YEAR_PLAN_COMMITTEE_APPROVAL;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId 申请内容id
     */
    @Override @Transactional
    public void afterApplicationPass(String applicationId, String contentId) {
        EquipmentYearPlan content = this.selectContent(contentId);
        content.setStatus(EquipmentYearPlanStatusConst.DIRECTOR_APPROVAL.ordinal());
        // 更新年度设备采购计划申请状态 4 -> 6
        EquipmentYearPlanApplicationQueryCondition condition = new EquipmentYearPlanApplicationQueryCondition();
        condition.setEquipmentYearPlanId(content.getId());
        condition.setStatus(COMMITTEE_PASS.ordinal());
        List<EquipmentYearPlanApplication> applicationList = equipmentYearPlanApplicationService.search(condition);
        for (EquipmentYearPlanApplication equipmentYearPlanApplication : applicationList) {
            equipmentYearPlanApplication.setStatus(DIRECTOR_PASS.ordinal());
            equipmentYearPlanApplicationService.update(equipmentYearPlanApplication);
        }
        // 创建 院长审批申请
        ApprovalApplication application = approvalApplicationService.select(applicationId);
        directorApprovalService.apply(content, application.getApplicantId(), ApplicationTypeConst.FORM_APPLICATION.ordinal(), null);
        // TODO: 通知院长
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
