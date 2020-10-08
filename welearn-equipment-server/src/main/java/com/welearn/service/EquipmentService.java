package com.welearn.service;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.equipment.EquipmentQueryCondition;
import com.welearn.entity.vo.response.common.LocationInfo;
import com.welearn.entity.vo.response.equipment.EquipmentInfo;
import com.welearn.entity.vo.response.equipment.EquipmentInitResult;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Description : EquipmentService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentService extends BaseService<Equipment, EquipmentQueryCondition>{

    /**
     * 带检查的更新, 先根据id 获取设备
     * 比对新设备的 产品id 是否和旧设备的 产品id 一致
     * 如果 产品id 不一致则根据 新产品id 更新设备信息
     * @param equipment 新设备信息
     */
    void updateWithCheck(@EntityCheck(checkId = true) Equipment equipment);

    /**
     * 根据设备的权限信息, 查询设备当前的所在位置
     * @param equipmentId 设备id
     * @return 设备的位置信息
     */
    LocationInfo locationInfo(@NotBlank String equipmentId);

    /**
     * 根据设备的权限信息, 查询设备当前的所在位置
     * @param equipment 设备
     * @return 设备的位置信息
     */
    LocationInfo locationInfo(@NotNull Equipment equipment);

    /**
     * 查询设备详情信息
     * @param condition 查询条件
     * @return List<EquipmentInfo>
     */
    List<EquipmentInfo> searchInfo(@NotNull EquipmentQueryCondition condition);

    /**
     * 查询部门设备简述信息
     * @param condition 查询条件
     * @return List<EquipmentInfo> 仅部分字段
     */
    List<EquipmentInfo> searchDepartmentEquipmentOutlook(@NotNull EquipmentQueryCondition condition);

    /**
     * 采购项目设备初始化构建
     * @param details 采购项目详情
     * @return EquipmentInitResult
     */
    EquipmentInitResult procurementEquipmentInit(List<ProcurementDetail> details);

    /**
     * 根据 设备产品 初始化设备信息
     * @param productId 产品id
     * @param initCount 初始化数量
     * @param procurementId 采购项目id
     * @param detailId 采购详情id
     * @param guaranteeRepairExpiredAt 设备保修期限
     * @param companyId 设备所属企业id
     * @param departmentId 设备所属部门id
     * @param obtainReason 设备添加原因
     * @return EquipmentInitResult
     */
    EquipmentInitResult productEquipmentInit(@NotNull String productId, @NotNull Integer initCount,
                                             String procurementId, String detailId, Date guaranteeRepairExpiredAt,
                                             @NotNull String companyId, String departmentId, String obtainReason);
}