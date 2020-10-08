package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.vo.request.alink.AllDeleteOldAndCreate;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.alink.ReDeviceGroupDevice;
import com.welearn.entity.qo.alink.ReDeviceGroupDeviceQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.ReDeviceGroupDeviceService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/reDeviceGroupDevice")
@Api(value = "reDeviceGroupDevice 接口")
public class ReDeviceGroupDeviceController extends BaseController<ReDeviceGroupDevice, ReDeviceGroupDeviceQueryCondition, ReDeviceGroupDeviceService>{

    @RequestMapping(value = "/allDeleteOldAndCreate")
    @ApiOperation(value = "allDeleteOldAndCreate", httpMethod = "POST")
    public CommonResponse<List<ReDeviceGroupDevice>> allDeleteOldAndCreate(@RequestBody AllDeleteOldAndCreate<ReDeviceGroupDevice> allDeleteOldAndCreate)  {
        List<ReDeviceGroupDevice> result = service.allDeleteOldAndCreate(allDeleteOldAndCreate.getGroupId(), allDeleteOldAndCreate.getReItems());
        return new CommonResponse<>(result);
    }

}