package com.welearn.controller;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.alink.Device;
import com.welearn.entity.qo.alink.DeviceQueryCondition;
import com.welearn.entity.vo.request.alink.RunService;
import com.welearn.entity.vo.request.alink.SecretLogin;
import com.welearn.entity.vo.request.alink.SetProperty;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.generator.ControllerGenerator;
import com.welearn.service.DeviceMonitorRecordService;
import com.welearn.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/device")
@Api(value = "device 接口")
public class DeviceController extends BaseController<Device, DeviceQueryCondition, DeviceService>{

    @RequestMapping(value = "/secretLogin")
    @ApiOperation(value = "secretLogin", httpMethod = "POST")
    public CommonResponse<Device> secretLogin(@RequestBody SecretLogin secretLogin)  {
        Device result = service.secretLogin(secretLogin.getProductKey(), secretLogin.getDeviceName(), secretLogin.getDeviceSecret());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/selectByIotId")
    @ApiOperation(value = "根据 iotId 获取设备", httpMethod = "POST")
    public CommonResponse<Device> selectByIotId(@RequestBody String string)  {
        Device result = service.selectByIotId(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/updateSelective")
    @ApiOperation(value = "根据 iotId 或 id 选择性的更新设备数据", httpMethod = "POST")
    public CommonResponse updateSelective(@RequestBody Device device)  {
        service.updateSelective(device);
        return CommonResponse.getSuccessResponse();
    } 

    @RequestMapping(value = "/setProperty")
    @ApiOperation(value = "设置设备属性", httpMethod = "POST")
    public CommonResponse setProperty(@RequestBody SetProperty setProperty)  {
        service.setProperty(setProperty.getIotId(), setProperty.getProperties());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/runService")
    @ApiOperation(value = "调用设备服务", httpMethod = "POST")
    public CommonResponse runService(@RequestBody RunService runService)  {
        service.runService(runService.getIotId(), runService.getIdentifier(), runService.getArgs());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/updateNickname")
    @ApiOperation(value = "设置设备的备注名称", httpMethod = "POST")
    public CommonResponse updateNickname(@RequestBody Device device)  {
        service.updateNickname(device);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/register")
    @ApiOperation(value = "向物联云平台申请注册设备", httpMethod = "POST")
    public CommonResponse register(@RequestBody Device device)  {
        service.register(device);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/updateDeviceFromAlink")
    @ApiOperation(value = "从物联云平台同步某个设备的基本信息到服务器", httpMethod = "POST")
    public CommonResponse<Device> updateDeviceFromAlink(@RequestBody String string)  {
        Device result = service.updateDeviceFromAlink(string);
        return new CommonResponse<>(result);
    }
}