package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.vo.request.alink.CreateFromJsonData;
import com.welearn.entity.vo.request.alink.EventCount;
import com.welearn.entity.vo.response.alink.DeviceEventTypeCount;
import com.welearn.generator.ControllerGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.alink.DeviceEvent;
import com.welearn.entity.qo.alink.DeviceEventQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.DeviceEventService;

import java.util.Map;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/deviceEvent")
@Api(value = "deviceEvent 接口")
public class DeviceEventController extends BaseController<DeviceEvent, DeviceEventQueryCondition, DeviceEventService>{

    @RequestMapping(value = "/count")
    @ApiOperation(value = "按类型进行数量统计", httpMethod = "POST")
    public CommonResponse<Map<String, Map<Integer, Long>>> count(@RequestBody EventCount count)  {
        Map<String, Map<Integer, Long>> result = service.count(count.getTypeGreaterThan(), count.getShouldHandle(), count.getHandleStatus(), count.getIotId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/report")
    @ApiOperation(value = "设备异常事件上报, 阿里云的事件服务延迟过大", httpMethod = "POST")
    public CommonResponse deviceEventReport(@RequestBody CreateFromJsonData createFromJsonData) {
        service.createFromJsonData(createFromJsonData.getProductKey(), createFromJsonData.getDeviceName(), createFromJsonData.getJsonData());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/updateAllNoNeedHandle")
    @ApiOperation(value = "更新设备的所有事件为不需要处理", httpMethod = "POST")
    public CommonResponse updateAllNoNeedHandle(@RequestBody String iotId)  {
        service.updateAllNoNeedHandle(iotId);
        return CommonResponse.getSuccessResponse();
    }
}