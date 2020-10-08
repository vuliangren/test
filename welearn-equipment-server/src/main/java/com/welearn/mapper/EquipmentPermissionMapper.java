package com.welearn.mapper;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentPermission;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.po.equipment.EquipmentTypeItem;
import com.welearn.entity.qo.equipment.EquipmentPermissionQueryCondition;
import com.welearn.entity.vo.response.equipment.EquipmentTypeItemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EquipmentPermission Mapper Interface : ryme_equipment : equipment_permission
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/12/6 20:33:29
 * @see com.welearn.entity.po.equipment.EquipmentPermission
 */
@Mapper 
public interface EquipmentPermissionMapper extends BaseMapper<EquipmentPermission, EquipmentPermissionQueryCondition> {
    /**
     * 查询权限关联的设备
     * @param permissionCode 权限编码
     * @param type 关联类型
     * @param typeId 关联id
     * @return 设备列表
     */
    List<Equipment> selectRefEquipments(@Param("permissionCode") Integer permissionCode,
                                        @Param("type") Integer type,
                                        @Param("typeId") String typeId);

    /**
     * 查询权限关联的设备产品
     * @param permissionCode 权限编码
     * @param type 关联类型
     * @param typeId 关联id
     * @return 设备产品列表
     */
    List<EquipmentProduct> selectRefEquipmentProducts(@Param("permissionCode") Integer permissionCode,
                                                      @Param("type") Integer type,
                                                      @Param("typeId") String typeId);

    /**
     * 查询权限关联的设备类型
     * @param permissionCode 权限编码
     * @param type 关联类型
     * @param typeId 关联id
     * @return 设备类型列表
     */
    List<EquipmentTypeItemInfo> selectRefEquipmentTypes(@Param("permissionCode") Integer permissionCode,
                                                        @Param("type") Integer type,
                                                        @Param("typeId") String typeId);

    /**
     * 查询权限关联的设备 数量
     * @param permissionCode 权限编码
     * @param type 关联类型
     * @param typeId 关联id
     * @return 设备列表
     */
    Integer selectRefEquipmentsCount(@Param("permissionCode") Integer permissionCode,
                                        @Param("type") Integer type,
                                        @Param("typeId") String typeId);

    /**
     * 查询权限关联的设备产品 数量
     * @param permissionCode 权限编码
     * @param type 关联类型
     * @param typeId 关联id
     * @return 设备产品列表
     */
    Integer selectRefEquipmentProductsCount(@Param("permissionCode") Integer permissionCode,
                                                      @Param("type") Integer type,
                                                      @Param("typeId") String typeId);

    /**
     * 查询权限关联的设备类型 数量
     * @param permissionCode 权限编码
     * @param type 关联类型
     * @param typeId 关联id
     * @return 设备类型列表
     */
    Integer selectRefEquipmentTypesCount(@Param("permissionCode") Integer permissionCode,
                                                    @Param("type") Integer type,
                                                    @Param("typeId") String typeId);

}