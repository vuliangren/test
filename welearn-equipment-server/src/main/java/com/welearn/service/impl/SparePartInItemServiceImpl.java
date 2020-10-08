package com.welearn.service.impl;

import com.welearn.dictionary.equipment.RepairPartReplaceStatusConst;
import com.welearn.dictionary.equipment.SparePartInItemStatusConst;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.po.equipment.SparePart;
import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.qo.equipment.SparePartInItemQueryCondition;
import com.welearn.mapper.RepairReplacementMapper;
import com.welearn.mapper.SparePartInItemMapper;
import com.welearn.service.SparePartInItemService;
import com.welearn.service.SparePartService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Description : SparePartInItemService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SparePartInItemServiceImpl extends BaseServiceImpl<SparePartInItem,SparePartInItemQueryCondition,SparePartInItemMapper>
        implements SparePartInItemService{
    
    @Autowired
    private SparePartInItemMapper sparePartInItemMapper;

    @Autowired
    private SparePartService sparePartService;

    @Autowired
    private RepairReplacementMapper repairReplacementMapper;

    @Override
    SparePartInItemMapper getMapper() {
        return sparePartInItemMapper;
    }

    private List<SparePartInItem> searchByBillId(String sparePartInBillId){
        SparePartInItemQueryCondition condition = new SparePartInItemQueryCondition();
        condition.setIsEnable(true);
        condition.setSparePartInBillId(sparePartInBillId);
        return this.search(condition);
    }

    /**
     * 配件入库成功
     *
     * @param sparePartInBill 入库单
     */
    @Override
    public void finish(SparePartInBill sparePartInBill) {
        List<SparePartInItem> items = this.searchByBillId(sparePartInBill.getId());
        for (SparePartInItem item : items) {
            // 创建批次
            SparePart sparePart = sparePartService.createFromSparePartInItem(item, sparePartInBill.getOrigin());
            // 标记已入库
            item.setStatus(SparePartInItemStatusConst.STOCK_IN.ordinal());
            item.setSparePartId(sparePart.getId());
            // 查询是否有关联维修配件更换
            if (StringUtils.isNotBlank(item.getRepairReplacementId())){
                RepairReplacement repairReplacement = repairReplacementMapper.selectByPK(item.getRepairReplacementId());
                if (Objects.nonNull(repairReplacement)){
                    repairReplacement.setStatus(RepairPartReplaceStatusConst.UN_STOCK_OUT.ordinal());
                    repairReplacementMapper.updateByPK(repairReplacement);
                }
            }
            // 更新
            this.update(item);
        }
    }

    /**
     * 配件入库失败
     *
     * @param sparePartInBill 入库单
     */
    @Override
    public void failed(SparePartInBill sparePartInBill) {
        List<SparePartInItem> items = this.searchByBillId(sparePartInBill.getId());
        for (SparePartInItem item : items) {
            // 标记已取消
            item.setStatus(SparePartInItemStatusConst.CANCEL.ordinal());
            // 更新
            this.update(item);
        }
    }
}
