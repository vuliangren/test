package com.welearn.service.impl;

import com.welearn.dictionary.equipment.RepairPartReplaceStatusConst;
import com.welearn.dictionary.equipment.SparePartOutBillStatusConst;
import com.welearn.dictionary.equipment.SparePartOutItemStatusConst;
import com.welearn.entity.po.equipment.RepairCostItem;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.po.equipment.SparePartOutBill;
import com.welearn.entity.po.equipment.SparePartOutItem;
import com.welearn.entity.qo.equipment.SparePartOutBillQueryCondition;
import com.welearn.entity.qo.equipment.SparePartOutItemQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.RepairReplacementMapper;
import com.welearn.mapper.SparePartOutBillMapper;
import com.welearn.service.RepairCostItemService;
import com.welearn.service.SparePartOutBillService;
import com.welearn.service.SparePartOutItemService;
import com.welearn.service.SparePartService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description : SparePartOutBillService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SparePartOutBillServiceImpl extends BaseServiceImpl<SparePartOutBill,SparePartOutBillQueryCondition,SparePartOutBillMapper>
        implements SparePartOutBillService{
    
    @Autowired
    private SparePartOutBillMapper sparePartOutBillMapper;

    @Autowired
    private SparePartOutItemService sparePartOutItemService;

    @Autowired
    private SparePartService sparePartService;

    @Autowired
    private RepairReplacementMapper repairReplacementMapper;

    @Autowired
    private RepairCostItemService repairCostItemService;

    @Override
    SparePartOutBillMapper getMapper() {
        return sparePartOutBillMapper;
    }

    /**
     * 出库单接受人签字确认
     *
     * @param sparePartOutBillId   出库单
     * @param recipientSignatureId 接受人签字
     */
    @Override
    public void finish(String sparePartOutBillId, String recipientSignatureId) {
        SparePartOutBill sparePartOutBill = this.select(sparePartOutBillId);
        if (Objects.isNull(sparePartOutBill) || sparePartOutBill.getStatus() != SparePartOutBillStatusConst.UN_STOCK_OUT.ordinal())
            throw new BusinessVerifyFailedException("sparePartOutBillId 非法 或 状态异常");
        sparePartOutBill.setStatus(SparePartOutBillStatusConst.STOCK_OUT.ordinal());
        sparePartOutBill.setRecipientSignatureId(recipientSignatureId);
        this.update(sparePartOutBill);
        // 获取出库项
        SparePartOutItemQueryCondition condition = new SparePartOutItemQueryCondition();
        condition.setIsEnable(true);
        condition.setSparePartOutBillId(sparePartOutBillId);
        List<SparePartOutItem> sparePartOutItems = sparePartOutItemService.search(condition);
        for (SparePartOutItem item : sparePartOutItems) {
            // 出库操作
            sparePartService.stockOut(item.getSparePartId(), item.getCount());
            // 更改状态
            item.setStatus(SparePartOutItemStatusConst.STOCK_OUT.ordinal());
            sparePartOutItemService.update(item);
            // 更改维修配件更换状态
            if (StringUtils.isNotBlank(item.getRepairReplacementId())){
                RepairReplacement replacement = repairReplacementMapper.selectByPK(item.getRepairReplacementId());
                if (Objects.isNull(replacement) || !replacement.getIsEnable())
                    throw new BusinessVerifyFailedException("repairReplacementId 非法");
                replacement.setStatus(RepairPartReplaceStatusConst.UN_REPLACE.ordinal());
                repairReplacementMapper.updateByPK(replacement);
                // 添加维修消费记录
                repairCostItemService.createSparePartCostItem(replacement, item.getPrice());
            }
        }
    }
}
