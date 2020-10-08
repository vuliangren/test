package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.apply.EquipmentYearPlanApplicationStatusConst;
import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import com.welearn.entity.po.apply.LargeEquipmentApplication;
import com.welearn.entity.qo.apply.EquipmentYearPlanApplicationQueryCondition;
import com.welearn.entity.vo.response.apply.EquipmentYearPlanApplicationInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.service.EquipmentYearPlanApplicationService;
import com.welearn.service.EquipmentYearPlanService;
import com.welearn.service.LargeEquipmentApplicationHandlerService;
import com.welearn.service.LargeEquipmentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.apply.EquipmentYearPlanApplicationStatusConst.*;

/**
 * Description :
 * Created by Setsuna Jin on 2018/10/30.
 */
@Service
public class LargeEquipmentApplicationHandlerServiceImpl extends ApplicationServiceImpl<LargeEquipmentApplication>
        implements LargeEquipmentApplicationHandlerService {

    @Autowired
    private LargeEquipmentApplicationService largeEquipmentApplicationService;

    @Autowired
    private EquipmentYearPlanApplicationService equipmentYearPlanApplicationService;

    @Autowired
    private EquipmentYearPlanService equipmentYearPlanService;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(LargeEquipmentApplication content) {
        largeEquipmentApplicationService.create(content);
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        largeEquipmentApplicationService.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(LargeEquipmentApplication content) {
        largeEquipmentApplicationService.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public LargeEquipmentApplication selectContent(String contentId) {
        return largeEquipmentApplicationService.select(contentId);
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(LargeEquipmentApplication content) {
        EquipmentYearPlanApplication planApplication = equipmentYearPlanApplicationService.select(content.getEquipmentYearPlanApplicationId());
        return planApplication.getEquipmentTypeName();
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.LARGE_EQUIPMENT_APPLICATION;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) {
        LargeEquipmentApplication largeEquipmentApplication = this.selectContent(contentId);
        EquipmentYearPlanApplicationQueryCondition condition = new EquipmentYearPlanApplicationQueryCondition();
        condition.setId(largeEquipmentApplication.getEquipmentYearPlanApplicationId());
        condition.setStatus(DIRECTOR_PASS.ordinal());
        List<EquipmentYearPlanApplicationInfo> applicationInfos = equipmentYearPlanApplicationService.searchInfo(condition);
        if (Objects.isNull(applicationInfos) || applicationInfos.isEmpty())
            throw new BusinessVerifyFailedException("EquipmentYearPlanApplicationInfo 为空");
        EquipmentYearPlanApplicationInfo applicationInfo = applicationInfos.get(0);
        // 执行 年度设备计划申请 的 大型医疗设备采购
        equipmentYearPlanService.equipmentYearPlanApplicationExecute(applicationInfo,applicationId, getApplicationType().getCode());
        applicationInfo.setStatus(PURCHASING.ordinal());
        equipmentYearPlanApplicationService.update(applicationInfo);
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
