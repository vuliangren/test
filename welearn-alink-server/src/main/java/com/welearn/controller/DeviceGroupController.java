package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.vo.response.alink.DeviceGroupInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.alink.DeviceGroup;
import com.welearn.entity.qo.alink.DeviceGroupQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.DeviceGroupService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/deviceGroup")
@Api(value = "deviceGroup 接口")
public class DeviceGroupController extends BaseController<DeviceGroup, DeviceGroupQueryCondition, DeviceGroupService>{

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询设备分组详情(含 与设备 用户 的关联信息)", httpMethod = "POST")
    public CommonResponse<List<DeviceGroupInfo>> searchInfo(@RequestBody DeviceGroupQueryCondition deviceGroupQueryCondition)  {
        List<DeviceGroupInfo> result = service.searchInfo(deviceGroupQueryCondition);
        return new CommonResponse<>(result);
    }


}