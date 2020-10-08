package com.welearn.service;

import com.welearn.dictionary.equipment.EquipmentPermissionCodeConst;
import com.welearn.dictionary.equipment.EquipmentPermissionTypeConst;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentPermission;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.po.equipment.EquipmentTypeItem;
import com.welearn.entity.qo.equipment.EquipmentPermissionQueryCondition;
import com.welearn.entity.vo.response.common.LocationInfo;
import com.welearn.entity.vo.response.equipment.EquipmentTypeItemInfo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : EquipmentPermissionService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentPermissionService extends BaseService<EquipmentPermission, EquipmentPermissionQueryCondition>{

    /**
     * 分配设备权限
     * @param equipmentPermission 设备权限
     */
    void allot(@NotNull EquipmentPermission equipmentPermission);

    /**
     * 撤回设备权限
     * @param equipmentPermissionId 设备权限id
     * @param loseReason 测绘原因
     */
    void cancel(@NotBlank String equipmentPermissionId, @NotBlank String loseReason);

    /**
     * 查询权限关联的设备
     * @param permissionCode 权限编码
     * @param type 关联类型
     * @param typeId 关联id
     * @return 设备列表
     */
    List<Equipment> selectRefEquipments(@NotNull Integer permissionCode, @NotNull Integer type, @NotBlank String typeId);

    /**
     * 查询权限关联的设备产品
     * @param permissionCode 权限编码
     * @param type 关联类型
     * @param typeId 关联id
     * @return 设备产品列表
     */
    List<EquipmentProduct> selectRefEquipmentProducts(@NotNull Integer permissionCode, @NotNull Integer type, @NotBlank String typeId);

    /**
     * 查询权限关联的设备类型
     * @param permissionCode 权限编码
     * @param type 关联类型
     * @param typeId 关联id
     * @return 设备类型列表
     */
    List<EquipmentTypeItemInfo> selectRefEquipmentTypes(@NotNull Integer permissionCode, @NotNull Integer type, @NotBlank String typeId);

    /**
     * 验证 type 对应的 id 是否具备 equipmentId 对应设备的 permissionCode 权限
     * @param type 设备权限类型
     * @param permissionCode 权限编码
     * @param equipmentId 设备id
     * @param typeId 类型关联的id
     * @return 是否具备权限
     */
    Boolean verify(@NotNull Integer type, @NotNull Integer permissionCode,
                   @NotBlank String equipmentId, @NotBlank String typeId);
}