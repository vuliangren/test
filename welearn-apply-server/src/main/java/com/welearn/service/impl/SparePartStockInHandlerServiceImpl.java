package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.qo.equipment.SparePartInItemQueryCondition;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.feign.equipment.SparePartInBillFeignClient;
import com.welearn.feign.equipment.SparePartInItemFeignClient;
import com.welearn.service.SparePartStockInHandlerService;
import com.welearn.util.GlobalContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description : 配件入库申请
 * Created by Setsuna Jin on 2019/3/1.
 */
@Slf4j
@Service
public class SparePartStockInHandlerServiceImpl extends ApplicationServiceImpl<SparePartInBill> implements SparePartStockInHandlerService {

    @Autowired
    private SparePartInBillFeignClient sparePartInBillFeignClient;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(SparePartInBill content) {
        SparePartInBill sparePartInBill = sparePartInBillFeignClient.create(content).getData();
        content.setId(sparePartInBill.getId());
    }

    @Override
    public void afterCreateContent(SparePartInBill content, ApprovalApplication application){
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
        sparePartInBillFeignClient.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(SparePartInBill content) {
        sparePartInBillFeignClient.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public SparePartInBill selectContent(String contentId) {
        return sparePartInBillFeignClient.select(contentId).getData();
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(SparePartInBill content) {
        return content.getOutlook();
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.SPARE_PART_STOCK_IN_APPLY;
    }

    private void updateStockInBillApprover(SparePartInBill sparePartInBill) throws Exception {
        AuthResult authResult = GlobalContextUtil.getAuthResult();
        sparePartInBill.setApproverId(authResult.getAccessToken().getUser().getId());
        sparePartInBill.setApproverName(authResult.getAccessToken().getUser().getName());
        sparePartInBill.setApproverDepartmentId(authResult.getDepartment().getId());
        sparePartInBill.setApproverDepartmentName(authResult.getDepartment().getName());
        sparePartInBill.setApproverCompanyId(authResult.getCompany().getId());
        sparePartInBill.setApproverCompanyName(authResult.getCompany().getName());
        this.updateContent(sparePartInBill);
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) throws Exception {
        SparePartInBill sparePartInBill = this.selectContent(contentId);
        this.updateStockInBillApprover(sparePartInBill);
        sparePartInBillFeignClient.finish(sparePartInBill);
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) throws Exception {
        SparePartInBill sparePartInBill = this.selectContent(contentId);
        this.updateStockInBillApprover(sparePartInBill);
        sparePartInBillFeignClient.failed(sparePartInBill);
    }
}
