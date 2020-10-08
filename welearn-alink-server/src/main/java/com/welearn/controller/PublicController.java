package com.welearn.controller;

import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.DeviceEventService;
import com.welearn.service.DeviceService;
import com.welearn.service.ReRfidTagDeviceService;
import com.welearn.service.RfidTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * Description :
 * Created by Setsuna Jin on 2019/6/4.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/public")
@Api(value = "开放接口")
public class PublicController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceEventService deviceEventService;

    // 已被废弃 除特殊原因使用 其它情况下应使用 基于 WEB SOCKET 的形式获取设备的配置文件
    @Deprecated
    @RequestMapping(value = "/deviceConfiguration", method = RequestMethod.GET)
    @ApiOperation(value = "获取设备的配置文件信息", httpMethod = "GET")
    public CommonResponse<JSONObject> deviceConfiguration(@RequestParam(value="productKey") String productKey, @RequestParam(value="deviceName") String deviceName) {
        JSONObject result = deviceService.getConfiguration(productKey, deviceName);
        return new CommonResponse<>(result);
    }

    // 已被废弃 除特殊原因使用 其它情况下应使用 基于 WEB SOCKET 的形式上报设备的异常事件
    @Deprecated
    @RequestMapping(value = "/deviceEventReport", method = RequestMethod.GET)
    @ApiOperation(value = "设备异常事件上报, 阿里云的事件服务延迟过大", httpMethod = "GET")
    public CommonResponse deviceEventReport(@RequestParam(value="productKey") String productKey,
                                            @RequestParam(value="deviceName") String deviceName,
                                            @RequestParam(value="eventData") String eventData) {
        // base64 解码
        String jsonData = new String(new Base64().decode(eventData), StandardCharsets.UTF_8);
        // 创建设备事件
        deviceEventService.createFromJsonData(productKey, deviceName, jsonData);
        return CommonResponse.getSuccessResponse();
    }
}
