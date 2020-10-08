package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.equipment.RepairInterrupt;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.feign.equipment.RepairInterruptFeignClient;
import com.welearn.service.RepairInterruptApplyHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/7.
 */
@Slf4j
@Service
public class RepairInterruptApplyHandlerServiceImpl  extends ApplicationServiceImpl<RepairInterrupt> implements RepairInterruptApplyHandlerService {

    @Autowired
    private RepairInterruptFeignClient repairInterruptFeignClient;


    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(RepairInterrupt content) {
        RepairInterrupt interrupt = repairInterruptFeignClient.create(content).getData();
        content.setId(interrupt.getId());
    }

    /**
     * 创建申请内容后调用, 可覆盖用来及时记录申请信息到content中
     *
     * @param content     申请内容
     * @param application 申请信息
     */
    @Override
    public void afterCreateContent(RepairInterrupt content, ApprovalApplication application) {
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
        repairInterruptFeignClient.delete(contentId);
    }


    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(RepairInterrupt content) {
        repairInterruptFeignClient.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public RepairInterrupt selectContent(String contentId) {
        return repairInterruptFeignClient.select(contentId).getData();
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(RepairInterrupt content) {
        return String.format("工程师: %s 申请中止维修", content.getEngineerName());
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.REPAIR_INTERRUPT_APPLY;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) throws Exception {
        repairInterruptFeignClient.afterApplicationPass(contentId);
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) throws Exception {
        repairInterruptFeignClient.afterApplicationReject(contentId);
    }
}
