package com.welearn.feign.alink;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.alink.Device;
import com.welearn.entity.qo.alink.DeviceQueryCondition;
import com.welearn.entity.vo.request.alink.RunService;
import com.welearn.entity.vo.request.alink.SecretLogin;
import com.welearn.entity.vo.request.alink.SetProperty;
import com.welearn.entity.vo.response.CommonResponse;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-alink-service / com.welearn.controller.DeviceController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-alink-server", configuration = FeignConfiguration.class)
public interface DeviceFeignClient {

    @RequestMapping(value = "/device/setProperty")
    CommonResponse setProperty(SetProperty setProperty);

    @RequestMapping(value = "/device/register")
    CommonResponse register(Device device);

    @RequestMapping(value = "/device/updateSelective")
    CommonResponse updateSelective(Device device);

    @RequestMapping(value = "/device/secretLogin")
    CommonResponse<Device> secretLogin(SecretLogin secretLogin);

    @RequestMapping(value = "/device/selectByIotId")
    CommonResponse<Device> selectByIotId(String string);

    @RequestMapping(value = "/device/runService")
    CommonResponse runService(RunService runService);

    @RequestMapping(value = "/device/updateNickname")
    CommonResponse updateNickname(Device device);

    @RequestMapping(value = "/device/updateDeviceFromAlink")
    CommonResponse<Device> updateDeviceFromAlink(String string);

    @RequestMapping(value = "/device/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/device/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/device/update")
    CommonResponse update(Device entity);

    @RequestMapping(value = "/device/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/device/create")
    CommonResponse<Device> create(Device entity);

    @RequestMapping(value = "/device/search")
    CommonResponse<List<Device>> search(DeviceQueryCondition queryCondition);

    @RequestMapping(value = "/device/select")
    CommonResponse<Device> select(String id);
}