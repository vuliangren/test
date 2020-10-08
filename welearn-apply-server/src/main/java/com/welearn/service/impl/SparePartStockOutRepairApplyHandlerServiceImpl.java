package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.equipment.SparePartOutBillStatusConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.equipment.SparePartOutBill;
import com.welearn.entity.po.equipment.SparePartOutItem;
import com.welearn.entity.po.equipment.SparePartOutRepairApply;
import com.welearn.entity.qo.equipment.SparePartOutItemQueryCondition;
import com.welearn.entity.vo.request.equipment.AfterApplyPass;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.CompanyFeignClient;
import com.welearn.feign.equipment.SparePartOutItemFeignClient;
import com.welearn.feign.equipment.SparePartOutRepairApplyFeignClient;
import com.welearn.service.ApprovalApplicationService;
import com.welearn.service.SparePartStockOutRepairApplyHandlerService;
import com.welearn.util.GlobalContextUtil;
import com.welearn.util.MoneyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description : 配件维修出库申请
 * Created by Setsuna Jin on 2019/3/1.
 */
@Slf4j
@Service
public class SparePartStockOutRepairApplyHandlerServiceImpl
        extends ApplicationServiceImpl<SparePartOutRepairApply> implements SparePartStockOutRepairApplyHandlerService {

    @Autowired
    private ApprovalApplicationService approvalApplicationService;

    @Autowired
    private SparePartOutRepairApplyFeignClient sparePartOutRepairApplyFeignClient;

    @Autowired
    private SparePartOutItemFeignClient sparePartOutItemFeignClient;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(SparePartOutRepairApply content) {
        SparePartOutRepairApply sparePartOutRepairApply = sparePartOutRepairApplyFeignClient.create(content).getData();
        content.setId(sparePartOutRepairApply.getId());
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        sparePartOutRepairApplyFeignClient.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(SparePartOutRepairApply content) {
        sparePartOutRepairApplyFeignClient.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public SparePartOutRepairApply selectContent(String contentId) {
        return sparePartOutRepairApplyFeignClient.select(contentId).getData();
    }

    private List<SparePartOutItem> selectOutItemByApplyId(String applyId){
        SparePartOutItemQueryCondition condition = new SparePartOutItemQueryCondition();
        condition.setIsEnable(true);
        condition.setSparePartOutApplyId(applyId);
        return sparePartOutItemFeignClient.search(condition).getData();
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(SparePartOutRepairApply content) {
        List<SparePartOutItem> items = this.selectOutItemByApplyId(content.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("配件:");
        for (int i = 0; i < items.size(); i++) {
            if( i > 0 )
                sb.append(", ");
            sb.append(items.get(i).getName());
        }
        return sb.toString();
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.SPARE_PART_STOCK_OUT_REPAIR_APPLY;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) throws Exception {
        // 查询申请信息
        ApprovalApplication application = approvalApplicationService.select(applicationId);
        // 构建出库单
        List<SparePartOutItem> items = this.selectOutItemByApplyId(contentId);
        int sumCount = 0;
        double sumPrice = 0d;
        for (int i = 0; i < items.size(); i++) {
            SparePartOutItem item = items.get(i);
            sumCount += item.getCount();
            sumPrice += item.getSumPrice();
        }
        SparePartOutBill sparePartOutBill = new SparePartOutBill();
        sparePartOutBill.setSumCount(sumCount);
        sparePartOutBill.setSumPrice(sumPrice);
        sparePartOutBill.setSumPriceChinese(MoneyUtil.toChinese(sumPrice));
        sparePartOutBill.setOutlook(application.getOutlook());
        sparePartOutBill.setStatus(SparePartOutBillStatusConst.UN_STOCK_OUT.ordinal());
        // 获取最后审批人的信息
        AuthResult authResult = GlobalContextUtil.getAuthResult();
        sparePartOutBill.setApproverId(authResult.getAccessToken().getUser().getId());
        sparePartOutBill.setApproverName(authResult.getAccessToken().getUser().getName());
        sparePartOutBill.setApproverDepartmentId(authResult.getDepartment().getId());
        sparePartOutBill.setApproverDepartmentName(authResult.getDepartment().getName());
        sparePartOutBill.setApproverCompanyId(authResult.getCompany().getId());
        sparePartOutBill.setApproverCompanyName(authResult.getCompany().getName());
        // 登记申请人信息
        sparePartOutBill.setRecipientId(application.getApplicantId());
        sparePartOutBill.setRecipientName(application.getApplicantName());
        sparePartOutBill.setRecipientDepartmentId(application.getDepartmentId());
        sparePartOutBill.setRecipientDepartmentName(application.getDepartmentName());
        sparePartOutBill.setRecipientCompanyId(application.getCompanyId());
        if (sparePartOutBill.getRecipientCompanyId().equals(sparePartOutBill.getApproverCompanyId()))
            sparePartOutBill.setRecipientCompanyName(sparePartOutBill.getApproverCompanyName());
        else {
            Company data = companyFeignClient.select(sparePartOutBill.getRecipientCompanyId()).getData();
            if (Objects.isNull(data))
                throw new BusinessVerifyFailedException("recipientCompanyId 非法");
            sparePartOutBill.setRecipientCompanyName(data.getName());
        }
        // 发起回调请求
        AfterApplyPass afterApplyPass = new AfterApplyPass();
        afterApplyPass.setApplyId(contentId);
        afterApplyPass.setSparePartOutBill(sparePartOutBill);
        sparePartOutRepairApplyFeignClient.afterApplyPass(afterApplyPass);
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) throws Exception {
        throw new BusinessVerifyFailedException("此申请不允许审批失败, 请在页端禁用调审批失败按钮");
    }
}
