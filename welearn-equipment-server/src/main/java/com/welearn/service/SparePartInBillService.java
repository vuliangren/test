package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.po.procurement.Procurement;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.equipment.SparePartInBillQueryCondition;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : SparePartInBillService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SparePartInBillService extends BaseService<SparePartInBill, SparePartInBillQueryCondition>{

    /**
     * 配件入库成功
     * @param sparePartInBill 入库单
     */
    void finish(@NotNull SparePartInBill sparePartInBill);

    /**
     * 配件入库失败
     * @param sparePartInBill 入库单
     */
    void failed(@NotNull SparePartInBill sparePartInBill);

    /**
     * 入库申请自动创建
     * @param bill 入库单
     * @param items 入库项
     * @return 申请
     */
    ApprovalApplication sparePartStockInAutoSubmit(@Valid SparePartInBill bill, @NotEmpty List<SparePartInItem> items, @NotNull User user);

    /**
     * 配件采购流程采购完成的后续操作
     * @param procurement 采购项目
     * @param details 采购详情
     */
    void sparePartProcurementFinish(Procurement procurement, List<ProcurementDetail> details) throws Exception;
}