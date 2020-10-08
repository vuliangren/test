package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.equipment.RepairPartReplaceStatusConst;
import com.welearn.dictionary.equipment.SparePartInBillStatusConst;
import com.welearn.dictionary.equipment.SparePartInItemStatusConst;
import com.welearn.dictionary.equipment.SparePartOriginConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.po.equipment.SparePartType;
import com.welearn.entity.po.procurement.Procurement;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.equipment.SparePartInBillQueryCondition;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.apply.SparePartStockInApplyFeignClient;
import com.welearn.mapper.SparePartInBillMapper;
import com.welearn.service.*;
import com.welearn.util.GlobalContextUtil;
import com.welearn.util.MoneyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description : SparePartInBillService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SparePartInBillServiceImpl extends BaseServiceImpl<SparePartInBill,SparePartInBillQueryCondition,SparePartInBillMapper>
        implements SparePartInBillService{
    
    @Autowired
    private SparePartInBillMapper sparePartInBillMapper;

    @Autowired
    private SparePartInItemService sparePartInItemService;

    @Autowired
    private SparePartStockInApplyFeignClient sparePartStockInApplyFeignClient;

    @Autowired
    private RepairReplacementService repairReplacementService;

    @Autowired
    private SparePartTypeService sparePartTypeService;

    @Override
    SparePartInBillMapper getMapper() {
        return sparePartInBillMapper;
    }


    /**
     * 配件入库成功
     * 须保证已填写了 核验人信息
     * @param sparePartInBill 入库单id
     */
    @Override @Transactional
    public void finish(SparePartInBill sparePartInBill) {
        if (StringUtils.isBlank(sparePartInBill.getApproverId()) ||
                StringUtils.isBlank(sparePartInBill.getApproverDepartmentId()) ||
                StringUtils.isBlank(sparePartInBill.getApproverCompanyId()))
            throw new BusinessVerifyFailedException("approverId 或 approverDepartmentId 或 approverCompanyId 为空");
        SparePartInBill bill = this.select(sparePartInBill.getId());
        bill.copyApproverInfo(sparePartInBill);

        // 如果没有申请id, 则需签名数据
        if (StringUtils.isBlank(sparePartInBill.getApplyId())){
            String signatureId = sparePartInBill.getApproverSignatureId();
            if (StringUtils.isBlank(signatureId))
                throw new BusinessVerifyFailedException("signatureId 为空");
            bill.setApproverSignatureId(signatureId);
        }

        bill.setStatus(SparePartInBillStatusConst.STOCK_IN.ordinal());
        this.update(bill);
        // 处理入库项
        sparePartInItemService.finish(bill);
    }

    /**
     * 配件入库取消
     *
     * @param sparePartInBill 入库单
     */
    @Override @Transactional
    public void failed(SparePartInBill sparePartInBill) {
        if (StringUtils.isBlank(sparePartInBill.getApproverId()) ||
                StringUtils.isBlank(sparePartInBill.getApproverDepartmentId()) ||
                StringUtils.isBlank(sparePartInBill.getApproverCompanyId()))
            throw new BusinessVerifyFailedException("approverId 或 approverDepartmentId 或 approverCompanyId 为空");
        SparePartInBill bill = this.select(sparePartInBill.getId());
        bill.copyApproverInfo(sparePartInBill);
        bill.setStatus(SparePartInBillStatusConst.CANCEL.ordinal());
        this.update(bill);
        // 处理入库项
        sparePartInItemService.failed(bill);
    }

    /**
     * 入库申请自动创建
     *
     * @param bill  入库单
     * @param items 入库项
     * @return 申请id
     */
    @Override
    public ApprovalApplication sparePartStockInAutoSubmit(SparePartInBill bill, List<SparePartInItem> items, User user) {
        int sumCount = 0, sort = 0;
        double sumPrice = 0d;
        for (SparePartInItem item : items) {
            sumCount += item.getCount();
            sumPrice += item.getCount() * item.getPrice();
        }

        // 检查维修配件更换 是否需要更换状态为待入库
        for (SparePartInItem item : items) {
            if (StringUtils.isNotBlank(item.getRepairReplacementId())){
                RepairReplacement replacement = repairReplacementService.select(item.getRepairReplacementId());
                if (Objects.isNull(replacement) || !replacement.getIsEnable() || replacement.getStatus() > RepairPartReplaceStatusConst.UN_STOCK_IN.ordinal())
                    throw new BusinessVerifyFailedException("item.repairReplacementId 非法 或 状态异常");
                replacement.setStatus(RepairPartReplaceStatusConst.UN_STOCK_IN.ordinal());
                repairReplacementService.update(replacement);
            }
        }
        // 创建入库单
        bill.setSumPrice(sumPrice);
        bill.setSumCount(sumCount);
        bill.setSumPriceChinese(MoneyUtil.toChinese(sumPrice));
        bill.setStatus(SparePartInBillStatusConst.UN_STOCK_IN.ordinal());
        this.create(bill);
        // 创建入库项
        for (SparePartInItem item : items) {
            item.setStatus(SparePartInItemStatusConst.UN_CHECK.ordinal());
            item.setSparePartInBillId(bill.getId());
            item.setSort(sort++);
            sparePartInItemService.create(item);
        }
        // 提交入库申请
        Apply<SparePartInBill> request = new Apply<>();
        request.setContent(bill);
        request.setApplicantId(user.getId());
        request.setType(ApplicationTypeConst.FORM_APPLICATION.ordinal());
        return sparePartStockInApplyFeignClient.apply(request).getData();
    }

    /**
     * 配件采购流程采购完成的后续操作
     *
     * @param procurement 采购项目
     * @param details     采购详情
     */
    @Override
    public void sparePartProcurementFinish(Procurement procurement, List<ProcurementDetail> details) throws Exception {
        AuthResult authResult = GlobalContextUtil.getAuthResult();
        // 构建入库单
        SparePartInBill bill = new SparePartInBill();
        bill.setOutlook(String.format("采购项目: %s 采购完成后申请入库", procurement.getName()));
        bill.setOrigin(SparePartOriginConst.PROCUREMENT.ordinal());
        bill.setSenderId(authResult.getAccessToken().getUser().getId());
        bill.setSenderName(authResult.getAccessToken().getUser().getName());
        bill.setSenderCompanyId(authResult.getCompany().getId());
        bill.setSenderCompanyName(authResult.getCompany().getName());
        bill.setSenderDepartmentId(authResult.getDepartment().getId());
        bill.setSenderDepartmentName(authResult.getDepartment().getName());
        // 构建入库项
        List<SparePartInItem> items = details.stream().map(detail -> {
            SparePartInItem item = new SparePartInItem();
            SparePartType partType = sparePartTypeService.select(detail.getTypeId());
            if (Objects.isNull(partType))
                throw new BusinessVerifyFailedException("procurementDetail.typeId 非法");
            item.setPriceType(partType.getPriceType());
            item.setName(partType.getName());
            item.setSpecification(partType.getSpecification());
            item.setUnit(partType.getUnit());
            item.setPrice(detail.getActualPrice());
            item.setCount(detail.getCount());
            item.setSumPrice(detail.getSumPrice());
            item.setProcurementId(procurement.getId());
            item.setProcurementDetailId(detail.getId());
            item.setModel(detail.getModel());
            item.setManufacturerName(detail.getManufacturer());
            // 判断是否有设备维修更换id
            String contentJson = detail.getContentJson();
            if (StringUtils.isNotBlank(contentJson)){
                JSONObject object = JSON.parseObject(contentJson);
                if (Objects.nonNull(object) && Objects.nonNull(object.getString("repairReplacementId"))){
                    item.setRepairReplacementId(object.getString("repairReplacementId"));
                }
            }
            return item;
        }).collect(Collectors.toList());
        // 发起入库申请
        sparePartStockInAutoSubmit(bill, items, authResult.getAccessToken().getUser());
    }

}
