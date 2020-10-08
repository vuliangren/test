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

import com.welearn.entity.po.alink.ReDeviceGroupUser;
import com.welearn.entity.qo.alink.ReDeviceGroupUserQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.ReDeviceGroupUserService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/reDeviceGroupUser")
@Api(value = "reDeviceGroupUser 接口")
public class ReDeviceGroupUserController extends BaseController<ReDeviceGroupUser, ReDeviceGroupUserQueryCondition, ReDeviceGroupUserService>{

    @RequestMapping(value = "/allDeleteOldAndCreate")
    @ApiOperation(value = "删除旧的所有关联并创建新关联", httpMethod = "POST")
    public CommonResponse<List<ReDeviceGroupUser>> allDeleteOldAndCreate(@RequestBody AllDeleteOldAndCreate<ReDeviceGroupUser> allDeleteOldAndCreate)  {
        List<ReDeviceGroupUser> result = service.allDeleteOldAndCreate(allDeleteOldAndCreate.getGroupId(), allDeleteOldAndCreate.getReItems());
        return new CommonResponse<>(result);
    }

}