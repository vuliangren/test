package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.apply.ContractTypeConst;
import com.welearn.dictionary.common.PersistantConst;
import com.welearn.dictionary.finance.InvoiceStatusConst;
import com.welearn.dictionary.procurement.EquipmentAccessoryTypeConst;
import com.welearn.dictionary.procurement.ProcurementCheckStatusConst;
import com.welearn.dictionary.procurement.ProcurementDetailStatusConst;
import com.welearn.dictionary.procurement.ProcurementTypeConst;
import com.welearn.entity.po.apply.Contract;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.po.finance.Invoice;
import com.welearn.entity.po.procurement.Procurement;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.finance.InvoiceQueryCondition;
import com.welearn.entity.qo.procurement.ProcurementDetailQueryCondition;
import com.welearn.entity.qo.procurement.ProcurementQueryCondition;
import com.welearn.entity.vo.request.equipment.SparePartProcurementFinish;
import com.welearn.entity.vo.response.procurement.ProcurementContent;
import com.welearn.entity.vo.response.procurement.ProcurementDetailInfo;
import com.welearn.entity.vo.response.procurement.ProcurementInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.apply.ContractFeignClient;
import com.welearn.feign.common.UserFeignClient;
import com.welearn.feign.equipment.EquipmentFeignClient;
import com.welearn.feign.equipment.EquipmentProductFeignClient;
import com.welearn.feign.equipment.SparePartInBillFeignClient;
import com.welearn.feign.finance.InvoiceFeignClient;
import com.welearn.mapper.ProcurementDetailMapper;
import com.welearn.mapper.ProcurementMapper;
import com.welearn.service.ProcurementDetailService;
import com.welearn.service.ProcurementService;
import com.welearn.util.PaginateUtil;
import com.welearn.util.UUIDGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.welearn.dictionary.procurement.EquipmentAccessoryTypeConst.*;
import static com.welearn.dictionary.procurement.ProcurementCheckStatusConst.*;
import static com.welearn.dictionary.procurement.ProcurementStatusConst.*;

/**
 * Description : ProcurementService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ProcurementServiceImpl extends BaseServiceImpl<Procurement,ProcurementQueryCondition,ProcurementMapper>
        implements ProcurementService{
    
    @Autowired
    private ProcurementMapper procurementMapper;

    @Autowired
    private ProcurementDetailMapper procurementDetailMapper;

    @Autowired
    private ProcurementDetailService procurementDetailService;

    @Autowired
    private ContractFeignClient contractFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private EquipmentProductFeignClient equipmentProductFeignClient;

    @Autowired
    private EquipmentFeignClient equipmentFeignClient;

    @Autowired
    private SparePartInBillFeignClient sparePartInBillFeignClient;

    @Autowired
    private InvoiceFeignClient invoiceFeignClient;

    @Override
    ProcurementMapper getMapper() {
        return procurementMapper;
    }


    /**
     * 根据 查询条件 查询 采购项目详情
     * 带分页
     * @param condition 查询条件
     * @return 采购项目详情列表
     */
    @Override
    public List<ProcurementInfo> searchInfo(ProcurementQueryCondition condition) {
        return PaginateUtil.page(()->procurementMapper.searchInfo(condition));
    }

    /**
     * 选择待审批项创建采购项目
     *
     * @param name      采购项目名称
     * @param type      采购项目类型
     * @param budget    采购项目预算
     * @param method    采购方式
     * @param detailIds 带采购项id列表
     * @param userId    创建人id
     * @return Procurement
     */
    @Override @Transactional
    public Procurement selectItem(String name, Integer type, Double budget, Integer method, List<String> detailIds, String userId) {
        User user = userFeignClient.select(userId).getData();
        if (Objects.isNull(user))
            throw new BusinessVerifyFailedException("userId 非法");
        // 创建采购项目
        Procurement procurement = new Procurement();
        procurement.setCreatorId(userId);
        procurement.setPurchaserCompanyId(user.getCompanyId());
        procurement.setName(name);
        procurement.setBudget(budget);
        procurement.setMethod(method);
        procurement.setType(type);
        procurement.setStatus(PROCESS_RECORD.ordinal());
        this.create(procurement);
        detailIds.forEach(detailId -> {
            ProcurementDetail detail = procurementDetailService.select(detailId);
            detail.setStatus(ProcurementDetailStatusConst.PURCHASING.ordinal());
            detail.setProcurementId(procurement.getId());
            procurementDetailService.update(detail);
        });
        return procurement;
    }

    /**
     * 采购项目流程记录
     *
     * @param documentJson  采购流程记录 Json
     * @param procurementId 采购项目id
     * @param isNextStep    采购项目是否进入下一步
     * @return Procurement
     */
    @Override @Transactional
    public Procurement processRecord(String documentJson, String procurementId, Boolean isNextStep) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || procurement.getStatus() != PROCESS_RECORD.ordinal())
            throw new BusinessVerifyFailedException("procurementId 非法");
        procurement.setDocumentJson(documentJson);
        String winner = JSON.parseObject(documentJson).getString("winner");
        if (isNextStep && StringUtils.isBlank(winner))
            throw new BusinessVerifyFailedException("winner 为空");
        // 存储 中标供应商名称
        procurement.setSellerCompanyName(winner);
        // 更新状态 是否进入下一流程
        procurement.setStatus(isNextStep ? SIGNING_CONTRACT.ordinal() : PROCESS_RECORD.ordinal());
        this.update(procurement);
        return procurement;
    }

    private List<ProcurementDetail> searchProcurementDetails(String procurementId){
        ProcurementDetailQueryCondition condition = new ProcurementDetailQueryCondition();
        condition.setIsEnable(true);
        condition.setProcurementId(procurementId);
        return procurementDetailService.search(condition);
    }

    /**
     * 双方签订合同
     *
     * @param details 采购项目详情列表
     * @param contract      合同
     * @param procurementId 采购项目id
     * @param salespersonIDCard 销售负责人身份证号
     * @param salespersonName 销售负责人姓名
     * @param salespersonIDCardPhotocopy 销售负责人身份证复印件加盖公章
     * @return Procurement
     */
    @Override @Transactional
    public Procurement signingContract(List<ProcurementDetail> details, Contract contract, String procurementId,
                                       String salespersonIDCard, String salespersonName, String salespersonIDCardPhotocopy) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || procurement.getStatus() != SIGNING_CONTRACT.ordinal())
            throw new BusinessVerifyFailedException("procurementId 非法");
        // 存储销售负责人信息
        procurement.setSalespersonIDCard(salespersonIDCard);
        procurement.setSalespersonName(salespersonName);
        procurement.setSalespersonIDCardPhotocopy(salespersonIDCardPhotocopy);
        // 查找关联的详情列表
        List<ProcurementDetail> detailList = this.searchProcurementDetails(procurementId);
        Map<String, ProcurementDetail> detailMap = new HashMap<>();
        details.forEach(detail -> detailMap.put(detail.getId(), detail));
        for (ProcurementDetail detail : detailList) {
            ProcurementDetail pd = detailMap.get(detail.getId());
            if (Objects.isNull(pd))
                throw new BusinessVerifyFailedException(" details 缺失");
            // 仅修改部分相关参数
            detail.setManufacturer(pd.getManufacturer());
            detail.setNativeOrigin(pd.getNativeOrigin());
            detail.setModel(pd.getModel());
            detail.setCount(pd.getCount());
            detail.setActualPrice(pd.getActualPrice());
            detail.setSumPrice(pd.getSumPrice());
            detail.setGuaranteeTime(pd.getGuaranteeTime());
            detail.setSpecification(pd.getSpecification());
            procurementDetailService.update(detail);
        }
        // 保存合同信息
        contract.setType(ContractTypeConst.PROCUREMENT.ordinal());
        contract.setTargetId(procurementId);
        contract.setOutlook(String.format("采购项目:%s 的采购合同", procurement.getName()));
        contractFeignClient.create(contract);
        // 更新项目状态
        procurement.setStatus(REGISTER.ordinal());
        this.update(procurement);
        return procurement;
    }

    /**
     * 构建 ProcurementContent
     * @param detail ProcurementDetail
     * @param name 名称
     * @param description 描述
     * @param count 数量
     * @param typeConst 类型
     * @return ProcurementContent
     */
    private ProcurementContent initContent(ProcurementDetail detail, String name, String description, Integer count, EquipmentAccessoryTypeConst typeConst){
        ProcurementContent content = new ProcurementContent();
        content.setId(UUIDGenerator.get());
        content.setDetailId(detail.getId());
        content.setDetailOutlook(String.format("%s-%s-%s", detail.getTypeName(), detail.getModel(), detail.getManufacturer()));
        content.setName(name);
        content.setDescription(description);
        if (Objects.isNull(count) || count <= 0)
            count = 1;
        count = count * detail.getCount();
        content.setCount(count);
        content.setRemain(count);
        content.setType(typeConst.ordinal());
        return content;
    }

    /**
     * 构建 List<ProcurementContent>
     * @param detailList List<ProcurementDetail>
     * @return List<ProcurementContent>
     */
    private List<ProcurementContent> initContents(List<ProcurementDetail> detailList){
        List<ProcurementContent> contents = new ArrayList<>();
        detailList.forEach(procurementDetail -> {
            EquipmentProduct product = equipmentProductFeignClient.select(procurementDetail.getProductId()).getData();
            String deliveryComponentsJson = product.getDeliveryComponentsJson();
            String accessoryListJson = product.getAccessoryListJson();
            String sparePartsListJson = product.getSparePartsListJson();
            JSONArray deliveryComponents = JSON.parseArray(deliveryComponentsJson);
            JSONArray accessoryList = JSON.parseArray(accessoryListJson);
            JSONArray sparePartList = JSON.parseArray(sparePartsListJson);
            // 设备主体设置
            if (Objects.nonNull(deliveryComponents) && deliveryComponents.size() > 0){
                for (int i = 0; i < deliveryComponents.size(); i++) {
                    JSONObject o = deliveryComponents.getJSONObject(i);
                    contents.add(initContent(procurementDetail, "主体-" + o.getString("name"), o.getString("description"), o.getInteger("count"), MAIN_PART));
                }
            } else {
                contents.add(initContent(procurementDetail, "设备主体", String.format("%s 设备的主体", procurementDetail.getTypeName()), 1, MAIN_PART));
            }
            // 设备附件设置
            if (Objects.nonNull(accessoryList) && accessoryList.size() > 0){
                for (int i = 0; i < accessoryList.size(); i++) {
                    JSONObject o = accessoryList.getJSONObject(i);
                    EquipmentAccessoryTypeConst typeConst = ATTACHMENT;
                    Integer type = o.getInteger("type");
                    if (Objects.nonNull(type)){
                        typeConst = EquipmentAccessoryTypeConst.values()[type];
                    }
                    contents.add(initContent(procurementDetail, o.getString("name"), o.getString("description"), o.getInteger("count"), typeConst));
                }
            }
            // 设备备件设置
            if (Objects.nonNull(sparePartList) && sparePartList.size() > 0){
                for (int i = 0; i < sparePartList.size(); i++) {
                    JSONObject o = sparePartList.getJSONObject(i);
                    contents.add(initContent(procurementDetail, o.getString("name"), o.getString("description"), o.getInteger("count"), SPARE_PART));
                }
            }
        });
        return contents;
    }

    /**
     * 根据 采购项目id 获取采购项目详情信息列表
     * @param procurementId 采购项目id
     * @return 采购项目详情信息列表
     */
    @Override
    public List<ProcurementDetailInfo> registerInfo(String procurementId){
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || procurement.getStatus() <= REGISTER.ordinal())
            throw new BusinessVerifyFailedException("procurementId 非法 或 未到规定流程无法调用");
        List<ProcurementDetailInfo> detailInfos = procurementDetailMapper.selectByProcurementIdAndIsEnable(procurementId, true);
        for (ProcurementDetailInfo detailInfo : detailInfos) {
            EquipmentProduct product = equipmentProductFeignClient.select(detailInfo.getProductId()).getData();
            if (Objects.isNull(product))
                throw new BusinessVerifyFailedException("采购项:%s 的 productId 非法", detailInfo.getId());
            detailInfo.setProductName(String.format("%s-%s-%s", product.getEquipmentTypeName(), product.getModelNumber(), product.getManufacturerName()));
            detailInfo.setIsImportProduct(product.getIsImportProduct());
            detailInfo.setIsLargeEquipment(product.getIsLargeEquipment());
            detailInfo.setManagementLever(product.getManagementLever());
            detailInfo.setManufacturerName(product.getManufacturerName());
            detailInfo.setProductApplicationId(product.getApplicationId());
            detailInfo.setProductModel(product.getModelNumber());
        }
        return detailInfos;
    }

    /**
     * 采购设备供应商登记设备对应的产品
     *
     * @param details       采购项目详情列表
     * @param procurementId 采购项目id
     * @return Procurement
     */
    @Override @Transactional
    public Procurement register(List<ProcurementDetail> details, String procurementId) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || procurement.getStatus() != REGISTER.ordinal())
            throw new BusinessVerifyFailedException("procurementId 非法");
        // 查询 采购项目相关详情
        List<ProcurementDetail> detailList = this.searchProcurementDetails(procurementId);
        // 构建参数详情索引
        Map<String, ProcurementDetail> detailMap = new HashMap<>();
        details.forEach(detail -> detailMap.put(detail.getId(), detail));
        // 验证是否所有详情对应的产品类型都登记
        boolean isAllRegister = true;
        for (ProcurementDetail detail : detailList) {
            ProcurementDetail pd = detailMap.get(detail.getId());
            if (Objects.nonNull(pd) && StringUtils.isNotBlank(pd.getProductId())){
                // 仅更新 productId
                detail.setProductId(pd.getProductId());
                procurementDetailService.update(detail);
            } else isAllRegister = false;
        }
        if (isAllRegister){
            // 初始化 contentsJson 内容
            List<ProcurementContent> contents = this.initContents(detailList);
            // 全部登记 进入下一阶段
            procurement.setStatus(DISTRIBUTE.ordinal());
            procurement.setContentsJson(JSON.toJSONString(contents));
            this.update(procurement);
        }
        return procurement;
    }

    /**
     * 供应商对商品进行装箱与运输
     *
     * @param contentJson   商品清单Json
     * @param packageJson   运输包装Json
     * @param procurementId 采购项目id
     * @param isNextStep    采购项目是否进入下一步
     * @return Procurement
     */
    @Override @Transactional
    public Procurement distribution(String contentJson, String packageJson, String procurementId, Boolean isNextStep) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || (procurement.getStatus() != DISTRIBUTE.ordinal() && procurement.getStatus() != COMMERCIAL_ACCEPTANCE.ordinal() ))
            throw new BusinessVerifyFailedException("procurementId 非法");
        procurement.setContentsJson(contentJson);
        procurement.setPackagesJson(packageJson);
        if (isNextStep){
            if (procurement.getStatus() == DISTRIBUTE.ordinal())
                procurement.setStatus(COMMERCIAL_ACCEPTANCE.ordinal());
            // 处理补货问题
            else if (procurement.getStatus() == COMMERCIAL_ACCEPTANCE.ordinal() && procurement.getCommercialCheckStatus() == FAILED.ordinal()){
                procurement.setCommercialCheckStatus(UN_CHECKED.ordinal());
            }
        }
        this.update(procurement);
        return procurement;
    }

    /**
     * 院方对供应商运输来的商品进行商业验收
     *
     * @param contentJson     商品清单Json
     * @param packageJson     运输包装Json
     * @param checkRecordJson 验收记录Json
     * @param procurementId   采购项目id
     * @param isNextStep      采购项目是否进入下一步
     * @return Procurement
     */
    @Override @Transactional
    public Procurement commercialAcceptance(String contentJson, String packageJson, String checkRecordJson, String procurementId, Boolean isNextStep) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || procurement.getStatus() != COMMERCIAL_ACCEPTANCE.ordinal())
            throw new BusinessVerifyFailedException("procurementId 非法");
        procurement.setContentsJson(contentJson);
        procurement.setPackagesJson(packageJson);
        procurement.setCheckRecordJson(checkRecordJson);
        if (isNextStep){
            // 判断是否验收通过
            JSONArray contents = JSON.parseArray(contentJson);
            ProcurementCheckStatusConst checkStatus = SUCCESS;
            for (int i = 0; i < contents.size(); i++) {
                JSONObject content = contents.getJSONObject(i);
                Boolean isChecked = content.getBoolean("isChecked");
                if (Objects.nonNull(isChecked) && !isChecked)
                    checkStatus = FAILED;
            }
            procurement.setCommercialCheckStatus(checkStatus.ordinal());
            if (checkStatus == SUCCESS)
                procurement.setStatus(TECHNOLOGY_ACCEPTANCE.ordinal());
        }
        this.update(procurement);
        return procurement;
    }



    /**
     * 院方对供应商的商品进行技术验收
     *
     * @param checkRecordJson      验收记录Json
     * @param checkReportPhotocopy 验收报告图片
     * @param procurementId        采购项目id
     * @param isNextStep           采购项目是否进入下一步
     * @return Procurement
     */
    @Override @Transactional
    public Procurement technologyAcceptance(String checkRecordJson, String checkReportPhotocopy, String procurementId, Boolean isNextStep) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || procurement.getStatus() != TECHNOLOGY_ACCEPTANCE.ordinal() || procurement.getTechnologyCheckStatus() != UN_CHECKED.ordinal())
            throw new BusinessVerifyFailedException("procurementId 非法");
        procurement.setCheckRecordJson(checkRecordJson);
        procurement.setCheckReportPhotocopy(checkReportPhotocopy);
        if (isNextStep){
            if (StringUtils.isBlank(procurement.getCheckReportPhotocopy()))
                throw new BusinessVerifyFailedException("checkReportPhotocopy 为空");
            procurement.setStatus(SETTLEMENT_INVOICE.ordinal());
            procurement.setTechnologyCheckStatus(SUCCESS.ordinal());
            // 根据项目类型完成采购内容的入库操作
            List<ProcurementDetail> procurementDetails = this.searchProcurementDetails(procurementId);
            if (procurement.getType() == ProcurementTypeConst.EQUIPMENT.ordinal()){
                // 设备采购完成 状态变为 未用
                equipmentFeignClient.procurementEquipmentInit(procurementDetails);
            }
            else if (procurement.getType() == ProcurementTypeConst.SPAREPART.ordinal()){
                // 配件采购完成 以当前人身份发起入库申请
                SparePartProcurementFinish payload = new SparePartProcurementFinish();
                payload.setProcurement(procurement);
                payload.setDetails(procurementDetails);
                sparePartInBillFeignClient.sparePartProcurementFinish(payload);
            }
        } else {
            procurement.setTechnologyCheckStatus(FAILED.ordinal());
        }
        this.update(procurement);
        return procurement;
    }

    /**
     * 供应商申请对项目技术验收进行重审
     *
     * @param procurementId 采购项目id
     */
    @Override
    public Procurement technologyAcceptanceRetrial(String procurementId) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || procurement.getStatus() != TECHNOLOGY_ACCEPTANCE.ordinal() || procurement.getTechnologyCheckStatus() != FAILED.ordinal())
            throw new BusinessVerifyFailedException("procurementId 非法");
        procurement.setTechnologyCheckStatus(UN_CHECKED.ordinal());
        this.update(procurement);
        return procurement;
    }


    /**
     * 采购项目款项结算 TODO: 发票参数
     *
     * @param isNextStep    采购项目是否进入下一步
     * @param invoiceIds    未关联的 发票id 列表
     * @param procurementId 采购项目id
     * @return Procurement
     */
    @Override @Transactional
    public Procurement settlementInvoice(String procurementId, List<String> invoiceIds, Boolean isNextStep) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || procurement.getStatus() != SETTLEMENT_INVOICE.ordinal())
            throw new BusinessVerifyFailedException("procurementId 非法");
        // 用于判断当前发票是否都已支付过了
        boolean isFinish = true;
        // 查询当前关联的发票
        InvoiceQueryCondition condition = new InvoiceQueryCondition();
        condition.setIsEnable(true);
        condition.setRefId(procurementId);
        List<Invoice> invoices = invoiceFeignClient.search(condition).getData();
        if (Objects.nonNull(invoices) && !invoices.isEmpty()){
            for (Invoice invoice : invoices) {
                boolean shouldUnRef = !invoiceIds.contains(invoice.getId());
                if (shouldUnRef){
                    invoiceFeignClient.cancelRef(invoice.getId());
                } else {
                    invoiceIds.remove(invoice.getId());
                    if (invoice.getStatus() < InvoiceStatusConst.ALL_SETTLED.ordinal())
                        isFinish = false;
                }
            }
        }
        for (String invoiceId : invoiceIds) {
            Invoice invoice = invoiceFeignClient.select(invoiceId).getData();
            if (Objects.isNull(invoice) || StringUtils.isNotBlank(invoice.getRefId()))
                throw new BusinessVerifyFailedException("invoiceIds 非法 或 发票已关联其它项目");
            if (invoice.getStatus() < InvoiceStatusConst.ALL_SETTLED.ordinal())
                isFinish = false;
            invoice.setRefId(procurementId);
            invoice.setRefType(PersistantConst.Procurement.name());
            invoiceFeignClient.update(invoice);
        }
        // 是否更新状态
        if (isNextStep){
            // 如果全部都支付过 则直接完成采购流程 无需等待支付
            if (isFinish)
                procurement.setStatus(FINISH.ordinal());
            else
                procurement.setStatus(SETTLEMENT_PAYMENT.ordinal());
            this.update(procurement);
        }
        return procurement;
    }

    /**
     * 采购项目款项结算 支付凭证
     *
     * @param procurementId 采购项目id
     * @return Procurement
     */
    @Override
    public Procurement settlementPayment(String procurementId) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement))
            throw new BusinessVerifyFailedException("procurementId 非法");
        if (procurement.getStatus() != SETTLEMENT_PAYMENT.ordinal())
            return procurement;
        procurement.setStatus(FINISH.ordinal());
        // 更新采购项目的状态
        List<ProcurementDetail> details = searchProcurementDetails(procurementId);
        for (ProcurementDetail detail : details) {
            if (detail.getStatus() >= ProcurementDetailStatusConst.FINISH.ordinal())
                continue;
            detail.setStatus(ProcurementDetailStatusConst.FINISH.ordinal());
            procurementDetailService.update(detail);
        }
        this.update(procurement);
        return procurement;
    }

    /**
     * 采购项目取消
     * 在签订合同前允许取消采购
     * @param cancellationReasonId 项目取消原因id
     * @param procurementId        采购项目id
     * @return Procurement
     */
    @Override @Transactional
    public Procurement cancel(String cancellationReasonId, String procurementId) {
        Procurement procurement = this.select(procurementId);
        if (Objects.isNull(procurement) || procurement.getStatus() < SIGNING_CONTRACT.ordinal())
            throw new BusinessVerifyFailedException("procurementId 非法 或 状态异常");
        procurement.setIsCanceled(true);
        procurement.setCancellationReasonId(cancellationReasonId);
        this.update(procurement);
        List<ProcurementDetail> detailList = searchProcurementDetails(procurementId);
        for (ProcurementDetail detail : detailList) {
            detail.setStatus(ProcurementDetailStatusConst.UN_PURCHASE.ordinal());
            procurementDetailService.update(detail);
        }
        return procurement;
    }

}
