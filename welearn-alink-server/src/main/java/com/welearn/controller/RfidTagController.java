package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.po.alink.ReRfidTagDevice;
import com.welearn.entity.vo.request.alink.DeleteOldAndCreateNew;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.alink.RfidTag;
import com.welearn.entity.qo.alink.RfidTagQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RfidTagService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/rfidTag")
@Api(value = "rfidTag 接口")
public class RfidTagController extends BaseController<RfidTag, RfidTagQueryCondition, RfidTagService>{

    @RequestMapping(value = "/deleteOldAndCreateNew")
    @ApiOperation(value = "deleteOldAndCreateNew", httpMethod = "POST")
    public CommonResponse<List<ReRfidTagDevice>> deleteOldAndCreateNew(@RequestBody DeleteOldAndCreateNew deleteOldAndCreateNew)  {
        List<ReRfidTagDevice> result = service.deleteOldAndCreateNew(deleteOldAndCreateNew.getTagId(), deleteOldAndCreateNew.getIotIds());
        return new CommonResponse<>(result);
    }

}