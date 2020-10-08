package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentPermission;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.qo.equipment.EquipmentPermissionQueryCondition;
import com.welearn.entity.vo.request.equipment.Cancel;
import com.welearn.entity.vo.request.equipment.PermissionRef;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.equipment.EquipmentTypeItemInfo;
import java.util.List;

/**
 * Description : welearn-equipment-service / com.welearn.controller.EquipmentPermissionController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface EquipmentPermissionFeignClient {

    @RequestMapping(value = "/equipmentPermission/selectRefEquipmentProducts")
    CommonResponse<List<EquipmentProduct>> selectRefEquipmentProducts(PermissionRef condition);

    @RequestMapping(value = "/equipmentPermission/selectRefEquipments")
    CommonResponse<List<Equipment>> selectRefEquipments(PermissionRef condition);

    @RequestMapping(value = "/equipmentPermission/selectRefEquipmentTypes")
    CommonResponse<List<EquipmentTypeItemInfo>> selectRefEquipmentTypes(PermissionRef condition);

    @RequestMapping(value = "/equipmentPermission/allot")
    CommonResponse allot(EquipmentPermission equipmentPermission);

    @RequestMapping(value = "/equipmentPermission/cancel")
    CommonResponse cancel(Cancel cancel);

    @RequestMapping(value = "/equipmentPermission/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/equipmentPermission/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/equipmentPermission/update")
    CommonResponse update(EquipmentPermission entity);

    @RequestMapping(value = "/equipmentPermission/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/equipmentPermission/create")
    CommonResponse<EquipmentPermission> create(EquipmentPermission entity);

    @RequestMapping(value = "/equipmentPermission/search")
    CommonResponse<List<EquipmentPermission>> search(EquipmentPermissionQueryCondition queryCondition);

    @RequestMapping(value = "/equipmentPermission/select")
    CommonResponse<EquipmentPermission> select(String id);
}