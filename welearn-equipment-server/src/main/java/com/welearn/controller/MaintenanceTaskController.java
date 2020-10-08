package com.welearn.controller;

import com.welearn.entity.vo.response.equipment.MaintenanceTaskInfo;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.MaintenanceTask;
import com.welearn.entity.qo.equipment.MaintenanceTaskQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.MaintenanceTaskService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/maintenanceTask")
public class MaintenanceTaskController extends BaseController<MaintenanceTask, MaintenanceTaskQueryCondition, MaintenanceTaskService>{

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询维护任务详情", httpMethod = "POST")
    public CommonResponse<List<MaintenanceTaskInfo>> searchInfo(@RequestBody MaintenanceTaskQueryCondition condition) {
        return new CommonResponse<>(service.searchInfo(condition));
    }

}