package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.alink.DevicePropertyRecord;
import com.welearn.entity.qo.alink.DevicePropertyRecordQueryCondition;
import com.welearn.entity.vo.request.alink.StatisticHour;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.DevicePropertyRecordService;
import java.lang.String;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/devicePropertyRecord")
@Api(value = "devicePropertyRecord 接口")
public class DevicePropertyRecordController extends BaseController<DevicePropertyRecord, DevicePropertyRecordQueryCondition, DevicePropertyRecordService>{

    @RequestMapping(value = "/statisticHourProperty")
    @ApiOperation(value = "统计设备的属性数据(时间范围限制为1小时)", httpMethod = "POST")
    public CommonResponse<Map<Date, List<String>>> statisticHourProperty(@RequestBody StatisticHour statisticHour)  {
        Map<Date, List<String>> result = service.statisticHourProperty(statisticHour.getIotId(), statisticHour.getReportedAtStart(), statisticHour.getReportedAtEnd());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/statisticDayRunningTime")
    @ApiOperation(value = "统计设备的每天运行时间数据(时间范围限制为1天)", httpMethod = "POST")
    public CommonResponse<Map<Date, List<String>>> statisticDayRunningTime(@RequestBody StatisticHour statisticHour)  {
        Map<Date, List<String>> result = service.statisticDayRunningTime(statisticHour.getIotId(), statisticHour.getReportedAtStart(), statisticHour.getReportedAtEnd());
        return new CommonResponse<>(result);
    }
}