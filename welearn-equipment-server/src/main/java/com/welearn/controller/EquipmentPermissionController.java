package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.po.equipment.EquipmentTypeItem;
import com.welearn.entity.vo.request.equipment.PermissionRef;
import com.welearn.entity.vo.response.equipment.EquipmentInitResult;
import com.welearn.entity.vo.response.equipment.EquipmentTypeItemInfo;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.EquipmentPermission;
import com.welearn.entity.qo.equipment.EquipmentPermissionQueryCondition;
import com.welearn.entity.vo.request.equipment.Cancel;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.EquipmentPermissionService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentPermission")
public class EquipmentPermissionController extends BaseController<EquipmentPermission, EquipmentPermissionQueryCondition, EquipmentPermissionService>{

    @RequestMapping(value = "/cancel")
    @ApiOperation(value = "赋予设备权限", httpMethod = "POST")
    public CommonResponse cancel(@RequestBody Cancel cancel) {
        service.cancel(cancel.getEquipmentPermissionId(), cancel.getLoseReason());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/allot")
    @ApiOperation(value = "撤回设备权限", httpMethod = "POST")
    public CommonResponse allot(@RequestBody EquipmentPermission equipmentPermission) {
        service.allot(equipmentPermission);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/selectRefEquipments")
    @ApiOperation(value = "查询权限关联的设备", httpMethod = "POST")
    public CommonResponse<List<Equipment>> selectRefEquipments(@RequestBody PermissionRef condition) {
        List<Equipment> result = service.selectRefEquipments(condition.getPermissionCode(), condition.getType(), condition.getTypeId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectRefEquipmentProducts")
    @ApiOperation(value = "查询权限关联的设备产品", httpMethod = "POST")
    public CommonResponse<List<EquipmentProduct>> selectRefEquipmentProducts(@RequestBody PermissionRef condition) {
        List<EquipmentProduct> result = service.selectRefEquipmentProducts(condition.getPermissionCode(), condition.getType(), condition.getTypeId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectRefEquipmentTypes")
    @ApiOperation(value = "查询权限关联的设备类型", httpMethod = "POST")
    public CommonResponse<List<EquipmentTypeItemInfo>> selectRefEquipmentTypes(@RequestBody PermissionRef condition) {
        List<EquipmentTypeItemInfo> result = service.selectRefEquipmentTypes(condition.getPermissionCode(), condition.getType(), condition.getTypeId());
        return new CommonResponse<>(result);
    }
}