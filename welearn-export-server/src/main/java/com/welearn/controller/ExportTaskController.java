package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.ControllerGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.export.ExportTask;
import com.welearn.entity.qo.export.ExportTaskQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.ExportTaskService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/exportTask")
@Api(value = "exportTask 接口")
public class ExportTaskController extends BaseController<ExportTask, ExportTaskQueryCondition, ExportTaskService>{

    @RequestMapping(value = "/retry")
    @ApiOperation(value = "重新执行", httpMethod = "POST")
    public CommonResponse retry(@RequestBody String exportTaskId) {
        service.retry(exportTaskId);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/reExport")
    @ApiOperation(value = "再次导出", httpMethod = "POST")
    public CommonResponse reExport(@RequestBody String exportTaskId) {
        service.reExport(exportTaskId);
        return CommonResponse.getSuccessResponse();
    }
}