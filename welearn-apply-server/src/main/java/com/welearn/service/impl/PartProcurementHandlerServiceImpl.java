package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.common.CompanyConfigConst;
import com.welearn.dictionary.equipment.RepairPartReplaceStatusConst;
import com.welearn.dictionary.procurement.CapitalSourceConst;
import com.welearn.dictionary.procurement.ProcurementDetailStatusConst;
import com.welearn.dictionary.procurement.ProcurementTypeConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.po.equipment.SparePartType;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.equipment.SparePartTypeQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.equipment.RepairReplacementFeignClient;
import com.welearn.feign.equipment.SparePartTypeFeignClient;
import com.welearn.feign.procurement.ProcurementDetailFeignClient;
import com.welearn.service.ApprovalApplicationService;
import com.welearn.service.PartProcurementHandlerService;
import com.welearn.util.GlobalContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2019/2/15.
 */
@Slf4j
public class PartProcurementHandlerServiceImpl extends ApplicationServiceImpl<RepairReplacement>
        implements PartProcurementHandlerService {

    @Autowired
    private RepairReplacementFeignClient repairReplacementFeignClient;

    @Autowired
    private ProcurementDetailFeignClient procurementDetailFeignClient;

    @Autowired
    private ApprovalApplicationService approvalApplicationService;

    @Autowired
    private SparePartTypeFeignClient sparePartTypeFeignClient;

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void createContent(RepairReplacement content) {
        RepairReplacement replacement = repairReplacementFeignClient.create(content).getData();
        content.setId(replacement.getId());
    }

    @Override
    public void afterCreateContent(RepairReplacement content, ApprovalApplication application){
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
        repairReplacementFeignClient.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(RepairReplacement content) {
        repairReplacementFeignClient.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public RepairReplacement selectContent(String contentId) {
        return repairReplacementFeignClient.select(contentId).getData();
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(RepairReplacement content) {
        throw new BusinessVerifyFailedException("getOutlook 方法须被覆盖");
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        throw new BusinessVerifyFailedException("getApplicationType 方法须被覆盖");
    }

    private SparePartType createSparePartType(RepairReplacement repairReplacement){
        SparePartType sparePartType = new SparePartType();
        sparePartType.setName(repairReplacement.getName());
        sparePartType.setSpecification(repairReplacement.getSpecification());
        sparePartType.setDescription(repairReplacement.getDescription());
        sparePartType.setPriceType(repairReplacement.getType());
        sparePartType.setInTransitInCount(0);
        sparePartType.setInTransitOutCount(0);
        sparePartType.setConsumption(0);
        sparePartType.setRetailPurchases(0);
        return sparePartTypeFeignClient.create(sparePartType).getData();
    }

    /**
     * 创建待采购项
     * @param sparePartType
     * @param repairReplacement
     * @param application
     * @return 待采购项
     */
    private ProcurementDetail createProcurementDetail(SparePartType sparePartType, RepairReplacement repairReplacement,
                                                      ApprovalApplication application) {
        ProcurementDetail detail = new ProcurementDetail();
        detail.setClassification(ProcurementTypeConst.SPAREPART.ordinal());
        detail.setTypeId(sparePartType.getId());
        detail.setTypeName(sparePartType.getName());
        detail.setSpecification(repairReplacement.getSpecification());
        detail.setCount(repairReplacement.getCount() - sparePartType.calLogicBalance());
        detail.setExpectedPrice(repairReplacement.getPrice());
        // 配件更换 列为 专项资金
        detail.setCapitalSource(CapitalSourceConst.SPECIAL.ordinal());
        detail.setDepartmentId(application.getDepartmentId());
        detail.setDepartmentName(application.getDepartmentName());
        detail.setApplicantId(application.getApplicantId());
        detail.setApplicantName(application.getApplicantName());
        detail.setCompanyId(application.getCompanyId());
        detail.setApplicationId(application.getId());
        detail.setApplicationType(getApplicationType().getCode());
        detail.setStatus(ProcurementDetailStatusConst.UN_PURCHASE.ordinal());
        // 记录下来设备维修更换id
        Map<String, String> content = new HashMap<>();
        content.put("repairReplacementId", repairReplacement.getId());
        detail.setContentJson(JSON.toJSONString(content));
        return procurementDetailFeignClient.create(detail).getData();
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) {
        RepairReplacement repairReplacement = this.selectContent(contentId);
        if (Objects.isNull(repairReplacement))
            throw new BusinessVerifyFailedException("contentId: %s 非法", contentId);
        // 查询申请信息
        ApprovalApplication application = approvalApplicationService.select(applicationId);
        // 获取公司配置,  此时 假设 审批人所在公司 与报修人所在公司一致 TODO 理应修改为 获取设备所属公司
        Boolean useDefaultPartStock = false;
        Boolean useDefaultPartProcurement = false;
        try {
            Company company = GlobalContextUtil.getAuthResult().getCompany();
            useDefaultPartStock = company.getConfig(CompanyConfigConst.USE_DEFAULT_SPARE_PART_STOCK);
            useDefaultPartProcurement = company.getConfig(CompanyConfigConst.USE_DEFAULT_SPARE_PART_PROCUREMENT);
        } catch (Exception e) {
            log.error("获取公司配置失败", e);
        }
        // 进行逻辑判断
        if (useDefaultPartStock) {
            // 查询是否有匹配的设备类型
            SparePartTypeQueryCondition condition = new SparePartTypeQueryCondition();
            condition.setName(repairReplacement.getName());
            condition.setSpecification(repairReplacement.getSpecification());
            condition.setIsEnable(true);
            List<SparePartType> sparePartTypes = sparePartTypeFeignClient.search(condition).getData();
            SparePartType sparePartType = null;
            if (Objects.isNull(sparePartTypes) || sparePartTypes.isEmpty())
                sparePartType = createSparePartType(repairReplacement);
            else
                sparePartType = sparePartTypes.get(0);
            // 计算库存余量
            Integer logicBalance = sparePartType.calLogicBalance();
            if (logicBalance - repairReplacement.getCount() >= 0){
                // 状态 -> 待出库
                repairReplacement.setStatus(RepairPartReplaceStatusConst.UN_STOCK_OUT.ordinal());
            } else {
                if (useDefaultPartProcurement){
                    // 存在采购部门 直接创建带采购项
                    ProcurementDetail procurementDetail = createProcurementDetail(sparePartType, repairReplacement, application);
                    repairReplacement.setProcurementDetailId(procurementDetail.getId());
                    repairReplacement.setStatus(RepairPartReplaceStatusConst.UN_PROCUREMENT.ordinal());
                } else {
                    // 状态 -> 待采购
                    // 由维修方采购 采购完申请入库
                    repairReplacement.setStatus(RepairPartReplaceStatusConst.UN_PROCUREMENT.ordinal());
                }
            }
        } else {
            if (useDefaultPartProcurement) {
                // 存在采购部门 直接创建带采购项
                SparePartType sparePartType = createSparePartType(repairReplacement);
                ProcurementDetail procurementDetail = createProcurementDetail(sparePartType, repairReplacement, application);
                repairReplacement.setProcurementDetailId(procurementDetail.getId());
                repairReplacement.setStatus(RepairPartReplaceStatusConst.UN_PROCUREMENT.ordinal());
            } else {
                // 状态 -> 待采购
                repairReplacement.setStatus(RepairPartReplaceStatusConst.UN_PROCUREMENT.ordinal());
            }
        }
        this.updateContent(repairReplacement);
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) {
        RepairReplacement repairReplacement = this.selectContent(contentId);
        repairReplacement.setStatus(RepairPartReplaceStatusConst.APPROVAL_FAIL.ordinal());
        this.updateContent(repairReplacement);
    }
}
