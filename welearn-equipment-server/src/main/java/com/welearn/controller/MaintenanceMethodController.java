package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.response.equipment.MaintenanceRecordDetail;
import com.welearn.entity.vo.response.equipment.TaskAssignmentDetail;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.MaintenanceMethod;
import com.welearn.entity.qo.equipment.MaintenanceMethodQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.MaintenanceMethodService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/maintenanceMethod")
public class MaintenanceMethodController extends BaseController<MaintenanceMethod, MaintenanceMethodQueryCondition, MaintenanceMethodService>{

    @RequestMapping(value = "/selectByEquipmentId")
    @ApiOperation(value = "查询设备相关的所有维护方式", httpMethod = "POST")
    public CommonResponse<List<MaintenanceMethod>> selectByEquipmentId(@RequestBody String equipmentId, @RequestUser User user)  {
        List<MaintenanceMethod> result = service.selectByEquipmentId(equipmentId, user.getCompanyId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectByEquipmentProductId")
    @ApiOperation(value = "查询产品相关的所有维护方式", httpMethod = "POST")
    public CommonResponse<List<MaintenanceMethod>> selectByEquipmentProductId(@RequestBody String equipmentProductId, @RequestUser User user)  {
        List<MaintenanceMethod> result = service.selectByEquipmentProductId(equipmentProductId, user.getCompanyId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectByEquipmentTypeId")
    @ApiOperation(value = "查询类型相关的所有维护方式", httpMethod = "POST")
    public CommonResponse<List<MaintenanceMethod>> selectByEquipmentTypeId(@RequestBody String equipmentTypeId, @RequestUser User user)  {
        List<MaintenanceMethod> result = service.selectByEquipmentTypeId(equipmentTypeId, user.getCompanyId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectByTaskId")
    @ApiOperation(value = "查询维护任务相关的维护方式", httpMethod = "POST")
    public CommonResponse<List<MaintenanceMethod>> selectByTaskId(@RequestBody String taskId) {
        return new CommonResponse<>(service.selectByTaskId(taskId));
    }

    @RequestMapping(value = "/selectTaskAssignmentDetail")
    @ApiOperation(value = "查询维护任务分配相关的维护方式详情(含是否处理)", httpMethod = "POST")
    public CommonResponse<List<TaskAssignmentDetail>> selectTaskAssignmentDetail(@RequestBody String assignmentId) {
        return new CommonResponse<>(service.selectTaskAssignmentDetail(assignmentId));
    }

    @RequestMapping(value = "/selectMaintenanceRecordDetail")
    @ApiOperation(value = "查询维护记录相关的维护方式详情(含维护数据)", httpMethod = "POST")
    public CommonResponse<List<MaintenanceRecordDetail>> selectMaintenanceRecordDetail(@RequestBody String recordId) {
        return new CommonResponse<>(service.selectMaintenanceRecordDetail(recordId));
    }

}