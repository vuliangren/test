package com.welearn.controller;

import com.welearn.entity.po.equipment.MaintenanceRecord;
import com.welearn.entity.vo.request.equipment.TaskAssignmentCancel;
import com.welearn.entity.vo.response.equipment.TaskAssignmentInfo;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.TaskAssignment;
import com.welearn.entity.qo.equipment.TaskAssignmentQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.TaskAssignmentService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/taskAssignment")
public class TaskAssignmentController extends BaseController<TaskAssignment, TaskAssignmentQueryCondition, TaskAssignmentService>{

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询任务分配详情", httpMethod = "POST")
    public CommonResponse<List<TaskAssignmentInfo>> searchInfo(@RequestBody TaskAssignmentQueryCondition condition) {
        return new CommonResponse<>(service.searchInfo(condition));
    }

    @RequestMapping(value = "/receive")
    @ApiOperation(value = "领取任务分配", httpMethod = "POST")
    public CommonResponse receive(@RequestBody String taskAssignmentId) {
        service.receive(taskAssignmentId);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/process")
    @ApiOperation(value = "处理任务分配", httpMethod = "POST")
    public CommonResponse<MaintenanceRecord> process(@RequestBody String taskAssignmentId) {
        return new CommonResponse<>(service.process(taskAssignmentId));
    }

    @RequestMapping(value = "/cancel")
    @ApiOperation(value = "取消任务分配", httpMethod = "POST")
    public CommonResponse cancel(@RequestBody TaskAssignmentCancel cancel) {
        service.cancel(cancel.getTaskAssignmentId(), cancel.getReason());
        return CommonResponse.getSuccessResponse();
    }
}