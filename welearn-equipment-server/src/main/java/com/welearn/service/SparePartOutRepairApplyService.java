package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.SparePartOutBill;
import com.welearn.entity.po.equipment.SparePartOutRepairApply;
import com.welearn.entity.qo.equipment.SparePartOutRepairApplyQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : SparePartOutRepairApplyService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SparePartOutRepairApplyService extends BaseService<SparePartOutRepairApply, SparePartOutRepairApplyQueryCondition>{

    /**
     * 创建并提交维修配件出库申请
     * @param sparePartOutRepairApply 维修配件出库申请
     * @param repairReplacementIds 选中的维修配件更换id
     * @return 申请
     */
    ApprovalApplication sparePartOutRepairApplyAutoSubmit(@Valid SparePartOutRepairApply sparePartOutRepairApply, @NotEmpty List<String> repairReplacementIds, @NotNull User user);

    /**
     * 出库申请通过, 创建出库单 并 更新出库项
     * @param applyId 申请id
     * @param sparePartOutBill 出库单
     */
    void afterApplyPass(@NotBlank String applyId, @Valid SparePartOutBill sparePartOutBill);
}