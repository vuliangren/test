package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.equipment.RepairHelpApply;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.feign.equipment.RepairHelpApplyFeignClient;
import com.welearn.service.RepairHelpApplyHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/11.
 */
@Slf4j
@Service
public class RepairHelpApplyHandlerServiceImpl extends ApplicationServiceImpl<RepairHelpApply> implements RepairHelpApplyHandlerService {

    @Autowired
    private RepairHelpApplyFeignClient repairHelpApplyFeignClient;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(RepairHelpApply content) {
        RepairHelpApply helpApply = repairHelpApplyFeignClient.create(content).getData();
        content.setApplyId(helpApply.getId());
    }

    @Override
    public void afterCreateContent(RepairHelpApply content, ApprovalApplication application){
        content.setApplyId(application.getId());
        this.updateContent(content);
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        repairHelpApplyFeignClient.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(RepairHelpApply content) {
        repairHelpApplyFeignClient.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public RepairHelpApply selectContent(String contentId) {
        return repairHelpApplyFeignClient.select(contentId).getData();
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(RepairHelpApply content) {
        return String.format("工程师: %s 申请维修外援", content.getEngineerName());
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.REPAIR_HELP_APPLY;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) throws Exception {
        repairHelpApplyFeignClient.afterApplicationPass(contentId);
        // TODO: 通知工程师
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) throws Exception {
        repairHelpApplyFeignClient.afterApplicationReject(contentId);
        // TODO: 通知工程师
    }
}
