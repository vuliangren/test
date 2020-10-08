package com.welearn.dictionary.apply;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/19.
 */
@Getter
@AllArgsConstructor
public enum ApplicationHandleTypeConst {
    DEPARTMENT_IN("departmentEntry"),
    DEPARTMENT_OUT("departmentLeave"),
    EQUIPMENT_YEAR_PLAN_COMMITTEE_APPROVAL("equipmentYearPlanCommitteeApproval"),
    EQUIPMENT_YEAR_PLAN_DIRECTOR_APPROVAL("equipmentYearPlanDirectorApproval"),
    EQUIPMENT_YEAR_PLAN_APPLICATION("equipmentYearPlanApplication"),
    LARGE_EQUIPMENT_APPLICATION("largeEquipmentApplication"),
    CERTIFICATE("certificate"),
    // 医疗设备产品登记申请
    EQUIPMENT_PRODUCT_REGISTER("equipmentProductRegister"),
    // 低价配件更换申请
    LOW_PRICE_PART_PROCUREMENT("lowPricePartProcurement"),
    // 高价配件更换申请
    HIGH_PRICE_PART_PROCUREMENT("highPricePartProcurement"),
    // 超额配件更换申请
    OVER_PRICE_PART_PROCUREMENT("overPricePartProcurement"),
    // 配件入库申请
    SPARE_PART_STOCK_IN_APPLY("sparePartStockInApply"),
    // 维修用配件出库申请
    SPARE_PART_STOCK_OUT_REPAIR_APPLY("sparePartStockOutRepairApply"),
    // 维修中止申请
    REPAIR_INTERRUPT_APPLY("repairInterruptApply"),
    // 申请第三方维修
    REPAIR_HELP_APPLY("repairHelpApply"),
    // 第三方维修报价审批
    REPAIR_HELP_QUOTATION_APPROVAL("repairHelpQuotationApproval"),
    // 固定资产入账确认
    FIXED_ASSETS_ENTER_CHECK("fixedAssetsEnterCheck"),
    // 设备借用申请
    EQUIPMENT_BORROW_APPLY("equipmentBorrowApply"),
    // 设备报废申请
    EQUIPMENT_SCRAP_APPLY("equipmentScrapApply"),
    // -----------------------------------------------------------------------------------------------------------------
    ;
    private String code;
}
