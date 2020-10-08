package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.equipment.EquipmentScrapApplyStatusConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.equipment.EquipmentScrapApply;
import com.welearn.feign.equipment.EquipmentScrapApplyFeignClient;
import com.welearn.service.EquipmentScrapApplyHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/26.
 */
@Slf4j
@Service
public class EquipmentScrapApplyHandlerServiceImpl extends ApplicationServiceImpl<EquipmentScrapApply> implements EquipmentScrapApplyHandlerService {

    @Autowired
    private EquipmentScrapApplyFeignClient equipmentScrapApplyFeignClient;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(EquipmentScrapApply content) {
        EquipmentScrapApply scrapApply = equipmentScrapApplyFeignClient.create(content).getData();
        content.setId(scrapApply.getId());
    }

    /**
     * 创建申请内容后调用, 可覆盖用来及时记录申请信息到content中
     *
     * @param content     申请内容
     * @param application 申请信息
     */
    @Override
    public void afterCreateContent(EquipmentScrapApply content, ApprovalApplication application) {
        content.setApplicationId(application.getId());
        this.updateContent(content);
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        equipmentScrapApplyFeignClient.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(EquipmentScrapApply content) {
        equipmentScrapApplyFeignClient.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public EquipmentScrapApply selectContent(String contentId) {
        return equipmentScrapApplyFeignClient.select(contentId).getData();
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(EquipmentScrapApply content) {
        return String.format("%s %s 设备报废申请", content.getApplicantDepartmentName(), content.getEquipmentName());
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.EQUIPMENT_SCRAP_APPLY;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) throws Exception {
        equipmentScrapApplyFeignClient.afterApplicationPass(contentId);
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) throws Exception {
        EquipmentScrapApply equipmentScrapApply = this.selectContent(contentId);
        equipmentScrapApply.setStatus(EquipmentScrapApplyStatusConst.SUCCESS.ordinal());
        this.updateContent(equipmentScrapApply);
    }
}
