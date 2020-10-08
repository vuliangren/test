package com.welearn.dictionary.common;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.alink.*;
import com.welearn.entity.po.export.*;
import com.welearn.entity.po.common.*;
import com.welearn.entity.po.notify.*;
import com.welearn.entity.po.storage.*;
import com.welearn.entity.po.apply.*;
import com.welearn.entity.po.equipment.*;
import com.welearn.entity.po.finance.*;
import com.welearn.entity.po.supplier.*;
import com.welearn.entity.po.procurement.*;
import com.welearn.generator.PersistantConstGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.welearn.dictionary.api.ServiceConst.*;

/**
 * Description : 项目实体列表 按服务划分
 * Created by Setsuna Jin on 2019/3/10.
 * @see PersistantConstGenerator
 */
@Getter
@AllArgsConstructor
public enum PersistantConst {
    // 项目所有实体都存放在此处, uuid 按 会十六进制编号在生成实体id时使用
    // 此部分如添加比较少可手动添加, 如添加较多则可使用生成器生成
    // ------------------------------------------------------------------

    Building(COMMON_SERVER, Building.class, 0, "建筑"),
    Company(COMMON_SERVER, Company.class, 1, "公司"),
    Tag(COMMON_SERVER, Tag.class, 2, "标签"),
    Department(COMMON_SERVER, Department.class, 3, "部门"),
    Floor(COMMON_SERVER, Floor.class, 4, "楼层"),
    Permission(COMMON_SERVER, Permission.class, 5, "权限"),
    Position(COMMON_SERVER, Position.class, 6, "职位"),
    ReRolePermission(COMMON_SERVER, ReRolePermission.class, 7, "ReRolePermission"),
    ReRouteHelp(COMMON_SERVER, ReRouteHelp.class, 8, "ReRouteHelp"),
    ReRouteRole(COMMON_SERVER, ReRouteRole.class, 9, "ReRouteRole"),
    ReUserDepartment(COMMON_SERVER, ReUserDepartment.class, 10, "ReUserDepartment"),
    ReUserPosition(COMMON_SERVER, ReUserPosition.class, 11, "ReUserPosition"),
    ReUserRole(COMMON_SERVER, ReUserRole.class, 12, "ReUserRole"),
    Role(COMMON_SERVER, Role.class, 13, "角色"),
    SupplierRegister(COMMON_SERVER, SupplierRegister.class, 14, "SupplierRegister"),
    User(COMMON_SERVER, User.class, 15, "用户"),
    WechatAppUser(COMMON_SERVER, WechatAppUser.class, 16, "微信小程序用户"),
    Address(COMMON_SERVER, Address.class, 17, "地址"),
    Room(COMMON_SERVER, Room.class, 18, "房间"),
    Area(COMMON_SERVER, Area.class, 19, "区域"),
    LinkCode(COMMON_SERVER, LinkCode.class, 20, "LinkCode"),
    ReRoutePermission(COMMON_SERVER, ReRoutePermission.class, 21, "ReRoutePermission"),
    ReTagItem(COMMON_SERVER, ReTagItem.class, 22, "ReTagItem"),
    // ------------------------------------------------------------------
    EmailHtmlTemplate(NOTIFY_SERVER, EmailHtmlTemplate.class, 0, "HTML邮件模板"),
    Feedback(NOTIFY_SERVER, Feedback.class, 1, "用户反馈"),
    Notice(NOTIFY_SERVER, Notice.class, 2, "通知"),
    NoticeRecord(NOTIFY_SERVER, NoticeRecord.class, 3, "通知发放记录"),
    SmsSendRecord(NOTIFY_SERVER, SmsSendRecord.class, 4, "短信发放记录"),
    SmsSendTemplate(NOTIFY_SERVER, SmsSendTemplate.class, 5, "短信发送模板"),
    // ------------------------------------------------------------------
    RichText(STORAGE_SERVER, RichText.class, 0, "富文本"),
    SignatureRecord(STORAGE_SERVER, SignatureRecord.class, 1, "签名记录"),
    UploadRecord(STORAGE_SERVER, UploadRecord.class, 2, "上传记录"),
    StatisticsRecord(STORAGE_SERVER, StatisticsRecord.class, 3, "StatisticsRecord"),
    // ------------------------------------------------------------------
    ApprovalApplication(APPLY_SERVER, ApprovalApplication.class, 0, "ApprovalApplication"),
    ApprovalProcess(APPLY_SERVER, ApprovalProcess.class, 1, "ApprovalProcess"),
    ApprovalProcessPoint(APPLY_SERVER, ApprovalProcessPoint.class, 2, "ApprovalProcessPoint"),
    ApprovalResult(APPLY_SERVER, ApprovalResult.class, 3, "ApprovalResult"),
    Certificate(APPLY_SERVER, Certificate.class, 4, "证书"),
    Contract(APPLY_SERVER, Contract.class, 5, "合同"),
    EquipmentYearPlan(APPLY_SERVER, EquipmentYearPlan.class, 6, "EquipmentYearPlan"),
    EquipmentYearPlanApplication(APPLY_SERVER, EquipmentYearPlanApplication.class, 7, "EquipmentYearPlanApplication"),
    LargeEquipmentApplication(APPLY_SERVER, LargeEquipmentApplication.class, 8, "LargeEquipmentApplication"),
    // ------------------------------------------------------------------
    Engineer(EQUIPMENT_SERVER, Engineer.class, 0, "工程师"),
    Equipment(EQUIPMENT_SERVER, Equipment.class, 1, "设备"),
    EquipmentAccessory(EQUIPMENT_SERVER, EquipmentAccessory.class, 2, "设备附件"),
    EquipmentGuaranteeRepairRecord(EQUIPMENT_SERVER, EquipmentGuaranteeRepairRecord.class, 3, "EquipmentGuaranteeRepairRecord"),
    EquipmentPermission(EQUIPMENT_SERVER, EquipmentPermission.class, 4, "设备权限"),
    EquipmentProduct(EQUIPMENT_SERVER, EquipmentProduct.class, 5, "产品"),
    EquipmentTypeDescription(EQUIPMENT_SERVER, EquipmentTypeDescription.class, 6, "EquipmentTypeDescription"),
    EquipmentTypeIndex(EQUIPMENT_SERVER, EquipmentTypeIndex.class, 7, "EquipmentTypeIndex"),
    EquipmentTypeItem(EQUIPMENT_SERVER, EquipmentTypeItem.class, 8, "EquipmentTypeItem"),
    MaintenanceMethod(EQUIPMENT_SERVER, MaintenanceMethod.class, 9, "MaintenanceMethod"),
    MaintenanceRecord(EQUIPMENT_SERVER, MaintenanceRecord.class, 10, "MaintenanceRecord"),
    MaintenanceTask(EQUIPMENT_SERVER, MaintenanceTask.class, 11, "MaintenanceTask"),
    ReEquipmentTypeItemDescription(EQUIPMENT_SERVER, ReEquipmentTypeItemDescription.class, 12, "ReEquipmentTypeItemDescription"),
    ReMaintenanceRecordMethod(EQUIPMENT_SERVER, ReMaintenanceRecordMethod.class, 13, "ReMaintenanceRecordMethod"),
    ReMaintenanceTaskMethod(EQUIPMENT_SERVER, ReMaintenanceTaskMethod.class, 14, "ReMaintenanceTaskMethod"),
    ReMaintenanceTaskUser(EQUIPMENT_SERVER, ReMaintenanceTaskUser.class, 15, "ReMaintenanceTaskUser"),
    RepairCheck(EQUIPMENT_SERVER, RepairCheck.class, 16, "RepairCheck"),
    RepairCostItem(EQUIPMENT_SERVER, RepairCostItem.class, 17, "RepairCostItem"),
    RepairDispatchInside(EQUIPMENT_SERVER, RepairDispatchInside.class, 18, "RepairDispatchInside"),
    RepairDispatchOutside(EQUIPMENT_SERVER, RepairDispatchOutside.class, 19, "RepairDispatchOutside"),
    RepairDispatchOutsideDetail(EQUIPMENT_SERVER, RepairDispatchOutsideDetail.class, 20, "RepairDispatchOutsideDetail"),
    RepairEvaluation(EQUIPMENT_SERVER, RepairEvaluation.class, 21, "RepairEvaluation"),
    RepairInterrupt(EQUIPMENT_SERVER, RepairInterrupt.class, 22, "RepairInterrupt"),
    RepairPrecept(EQUIPMENT_SERVER, RepairPrecept.class, 23, "RepairPrecept"),
    RepairReplacement(EQUIPMENT_SERVER, RepairReplacement.class, 24, "RepairReplacement"),
    RepairRequest(EQUIPMENT_SERVER, RepairRequest.class, 25, "RepairRequest"),
    RepairSummary(EQUIPMENT_SERVER, RepairSummary.class, 26, "RepairSummary"),
    ReTaskAssignmentMethod(EQUIPMENT_SERVER, ReTaskAssignmentMethod.class, 27, "ReTaskAssignmentMethod"),
    SparePart(EQUIPMENT_SERVER, SparePart.class, 28, "SparePart"),
    SparePartInBill(EQUIPMENT_SERVER, SparePartInBill.class, 29, "SparePartInBill"),
    SparePartInItem(EQUIPMENT_SERVER, SparePartInItem.class, 30, "SparePartInItem"),
    SparePartOutBill(EQUIPMENT_SERVER, SparePartOutBill.class, 31, "SparePartOutBill"),
    SparePartOutItem(EQUIPMENT_SERVER, SparePartOutItem.class, 32, "SparePartOutItem"),
    SparePartOutRepairApply(EQUIPMENT_SERVER, SparePartOutRepairApply.class, 33, "SparePartOutRepairApply"),
    SparePartReplacement(EQUIPMENT_SERVER, SparePartReplacement.class, 34, "SparePartReplacement"),
    SparePartType(EQUIPMENT_SERVER, SparePartType.class, 35, "SparePartType"),
    SparePartUsage(EQUIPMENT_SERVER, SparePartUsage.class, 36, "SparePartUsage"),
    TaskAssignment(EQUIPMENT_SERVER, TaskAssignment.class, 37, "TaskAssignment"),
    EquipmentBorrow(EQUIPMENT_SERVER, EquipmentBorrow.class, 38, "EquipmentBorrow"),
    EquipmentBorrowAccessory(EQUIPMENT_SERVER, EquipmentBorrowAccessory.class, 39, "EquipmentBorrowAccessory"),
    EquipmentScrapApply(EQUIPMENT_SERVER, EquipmentScrapApply.class, 40, "EquipmentScrapApply"),
    InspectionPlan(EQUIPMENT_SERVER, InspectionPlan.class, 41, "InspectionPlan"),
    InspectionRecord(EQUIPMENT_SERVER, InspectionRecord.class, 42, "InspectionRecord"),
    MaintenanceCostItem(EQUIPMENT_SERVER, MaintenanceCostItem.class, 43, "MaintenanceCostItem"),
    RepairHelpApply(EQUIPMENT_SERVER, RepairHelpApply.class, 44, "RepairHelpApply"),
    RepairHelpQuotationApproval(EQUIPMENT_SERVER, RepairHelpQuotationApproval.class, 45, "RepairHelpQuotationApproval"),
    ReEquipmentEquipment(EQUIPMENT_SERVER, ReEquipmentEquipment.class, 46, "ReEquipmentEquipment"),
    // ------------------------------------------------------------------
    Procurement(PROCUREMENT_SERVER, Procurement.class, 1, "采购项目"),
    ProcurementDetail(PROCUREMENT_SERVER, ProcurementDetail.class, 2, "采购详情"),
    // ------------------------------------------------------------------
    FixedAssets(FINANCE_SERVER, FixedAssets.class, 0, "固定资产"),
    Invoice(FINANCE_SERVER, Invoice.class, 1, "发票"),
    InvoicePayment(FINANCE_SERVER, InvoicePayment.class, 2, "InvoicePayment"),
    ReBatchAllocation(FINANCE_SERVER, ReBatchAllocation.class, 3, "ReBatchAllocation"),
    ReBatchPackage(FINANCE_SERVER, ReBatchPackage.class, 4, "ReBatchPackage"),
    RePackageItem(FINANCE_SERVER, RePackageItem.class, 5, "RePackageItem"),
    ReTaskBatch(FINANCE_SERVER, ReTaskBatch.class, 6, "ReTaskBatch"),
    ReTaskPackage(FINANCE_SERVER, ReTaskPackage.class, 7, "ReTaskPackage"),
    Stock(FINANCE_SERVER, Stock.class, 8, "Stock"),
    StockAllocation(FINANCE_SERVER, StockAllocation.class, 9, "StockAllocation"),
    StockBatch(FINANCE_SERVER, StockBatch.class, 10, "StockBatch"),
    StockLog(FINANCE_SERVER, StockLog.class, 11, "StockLog"),
    StockPackage(FINANCE_SERVER, StockPackage.class, 12, "StockPackage"),
    StockPlace(FINANCE_SERVER, StockPlace.class, 13, "StockPlace"),
    StockTask(FINANCE_SERVER, StockTask.class, 14, "StockTask"),
    StockType(FINANCE_SERVER, StockType.class, 15, "StockType"),
    // ------------------------------------------------------------------
    Device(ALINK_SERVER, Device.class, 0, "Device"),
    DeviceEvent(ALINK_SERVER, DeviceEvent.class, 1, "DeviceEvent"),
    Product(ALINK_SERVER, Product.class, 2, "Product"),
    DeviceMonitorRecord(ALINK_SERVER, DeviceMonitorRecord.class, 3, "DeviceMonitorRecord"),
    DevicePropertyRecord(ALINK_SERVER, DevicePropertyRecord.class, 4, "DevicePropertyRecord"),
    DeviceGroup(ALINK_SERVER, DeviceGroup.class, 5, "DeviceGroup"),
    ReDeviceGroupDevice(ALINK_SERVER, ReDeviceGroupDevice.class, 6, "ReDeviceGroupDevice"),
    ReDeviceGroupUser(ALINK_SERVER, ReDeviceGroupUser.class, 7, "ReDeviceGroupUser"),
    ReRfidTagDevice(ALINK_SERVER, ReRfidTagDevice.class, 8, "ReRfidTagDevice"),
    RfidTag(ALINK_SERVER, RfidTag.class, 9, "RfidTag"),
    // ------------------------------------------------------------------
    SupplierCard(SUPPLIER_SERVER, SupplierCard.class, 0, "供应商名片"),
    // ------------------------------------------------------------------
    ExportTask(EXPORT_SERVER, ExportTask.class, 0, "数据导出任务"),
    ExportTemplate(EXPORT_SERVER, ExportTemplate.class, 1, "数据导出模板"),
    // ------------------------------------------------------------------
    ;
    // -----------------------------------------------------------------------
    private ServiceConst service;
    private Class clazz;
    private Integer uuid;
    private String description;

    private static final Map<String, PersistantConst> PrefixPersistantIndex = getPrefixPersistantIndex();

    private static Map<String, PersistantConst> getPrefixPersistantIndex(){
        return Arrays.stream(PersistantConst.values())
                .collect(Collectors.toMap(PersistantConst::getPrefix, p -> p));
    }

    public static Map<String, String> getPrefixPersistantNameIndex(){
        return Arrays.stream(PersistantConst.values())
                .collect(Collectors.toMap(PersistantConst::getPrefix, Enum::name));
    }

    /**
     * 获取十六进制形式的前缀
     * @return %02x(2位)%02x(2位)
     */
    public String getPrefix(){
        return String.format("%02x%02x", this.getService().getUuid(), this.getUuid());
    }

    /**
     * 根据UUID解析得到该实体的类型和所属服务
     * @param uuid 36位UUID
     * @return PersistantConst
     */
    public static PersistantConst getByUUID(String uuid){
        if (StringUtils.isNotBlank(uuid) && uuid.length() == 36){
            return PrefixPersistantIndex.get(uuid.substring(0, 4));
        } else {
            return null;
        }
    }
}
