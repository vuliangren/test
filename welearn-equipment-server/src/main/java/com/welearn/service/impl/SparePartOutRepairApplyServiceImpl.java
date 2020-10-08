package com.welearn.service.impl;

import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.equipment.RepairPartReplaceStatusConst;
import com.welearn.dictionary.equipment.SparePartOutItemStatusConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.po.equipment.SparePartOutBill;
import com.welearn.entity.po.equipment.SparePartOutItem;
import com.welearn.entity.po.equipment.SparePartOutRepairApply;
import com.welearn.entity.qo.equipment.SparePartOutItemQueryCondition;
import com.welearn.entity.qo.equipment.SparePartOutRepairApplyQueryCondition;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.apply.SparePartStockOutRepairApplyFeignClient;
import com.welearn.feign.equipment.SparePartOutRepairApplyFeignClient;
import com.welearn.mapper.RepairReplacementMapper;
import com.welearn.mapper.SparePartOutRepairApplyMapper;
import com.welearn.service.SparePartOutBillService;
import com.welearn.service.SparePartOutItemService;
import com.welearn.service.SparePartOutRepairApplyService;
import com.welearn.service.SparePartService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description : SparePartOutRepairApplyService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SparePartOutRepairApplyServiceImpl extends BaseServiceImpl<SparePartOutRepairApply,SparePartOutRepairApplyQueryCondition,SparePartOutRepairApplyMapper>
        implements SparePartOutRepairApplyService{
    
    @Autowired
    private SparePartOutRepairApplyMapper sparePartOutRepairApplyMapper;

    @Autowired
    private RepairReplacementMapper repairReplacementMapper;

    @Autowired
    private SparePartStockOutRepairApplyFeignClient sparePartStockOutRepairApplyFeignClient;

    @Autowired
    private SparePartOutItemService sparePartOutItemService;

    @Autowired
    private SparePartOutBillService sparePartOutBillService;

    @Autowired
    private SparePartService sparePartService;

    @Override
    SparePartOutRepairApplyMapper getMapper() {
        return sparePartOutRepairApplyMapper;
    }

    private List<SparePartOutItem> selectByApplyId(String applyId) {
        SparePartOutItemQueryCondition condition = new SparePartOutItemQueryCondition();
        condition.setIsEnable(true);
        condition.setSparePartOutApplyId(applyId);
        List<SparePartOutItem> items = sparePartOutItemService.search(condition);
        if (Objects.isNull(items) || items.isEmpty())
            throw new BusinessVerifyFailedException("applyId 未关联出库项");
        return items;
    }

    /**
     * 创建并提交维修配件出库申请
     *
     * @param sparePartOutRepairApply 维修配件出库申请
     * @param repairReplacementIds    选中的维修配件更换id
     * @return 申请id
     */
    @Override
    public ApprovalApplication sparePartOutRepairApplyAutoSubmit(SparePartOutRepairApply sparePartOutRepairApply, List<String> repairReplacementIds, User user) {
        this.create(sparePartOutRepairApply);
        // 创建关联
        for (String repairReplacementId : repairReplacementIds) {
            RepairReplacement repairReplacement = repairReplacementMapper.selectByPK(repairReplacementId);
            repairReplacement.setSparePartOutRepairApplyId(sparePartOutRepairApply.getId());
            repairReplacementMapper.updateByPK(repairReplacement);
        }
        // 系统自动提交申请
        Apply<SparePartOutRepairApply> request = new Apply<>();
        request.setContent(sparePartOutRepairApply);
        request.setApplicantId(user.getId());
        request.setType(ApplicationTypeConst.FORM_APPLICATION.ordinal());
        return sparePartStockOutRepairApplyFeignClient.apply(request).getData();
    }

    /**
     * 出库申请通过, 创建出库单 并 更新出库项
     *
     * @param applyId          申请id
     * @param sparePartOutBill 出库单
     */
    @Override
    public void afterApplyPass(String applyId, SparePartOutBill sparePartOutBill) {
        sparePartOutBillService.create(sparePartOutBill);
        // 获取 出库项, 锁配件库存 改出库项状态为 待确认
        List<SparePartOutItem> items = this.selectByApplyId(applyId);
        for (SparePartOutItem item : items) {
            item.setStatus(SparePartOutItemStatusConst.UN_CHECK.ordinal());
            item.setSparePartOutBillId(sparePartOutBill.getId());
            sparePartService.inTransitOut(item.getSparePartId(), item.getCount());
            sparePartOutItemService.update(item);
            if (StringUtils.isNotBlank(item.getRepairReplacementId())){
                // 更改维修配件更换 的 出库单id 出库项id
                RepairReplacement repairReplacement = repairReplacementMapper.selectByPK(item.getRepairReplacementId());
                if (Objects.isNull(repairReplacement) || !repairReplacement.getIsEnable())
                    throw new BusinessVerifyFailedException("repairReplacementId 非法");
                repairReplacement.setSparePartOutItemId(item.getId());
                repairReplacement.setSparePartOutBillId(sparePartOutBill.getId());
                repairReplacementMapper.updateByPK(repairReplacement);
            }
        }
    }
}
