package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.vo.response.equipment.RepairDispatchOutsideDetailInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.po.equipment.RepairDispatchOutsideDetail;
import com.welearn.entity.qo.equipment.RepairDispatchOutsideDetailQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.RepairDispatchOutsideDetailService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairDispatchOutsideDetail")
@Api(value = "repairDispatchOutsideDetail 接口")
public class RepairDispatchOutsideDetailController extends BaseController<RepairDispatchOutsideDetail, RepairDispatchOutsideDetailQueryCondition, RepairDispatchOutsideDetailService>{

    @RequestMapping(value = "/searchDispatchOutsideEngineers")
    @ApiOperation(value = "查询外部派工关联的工程师信息", httpMethod = "POST")
    public CommonResponse<List<RepairDispatchOutsideDetailInfo>> searchDispatchOutsideEngineers(@RequestBody String string)  {
        List<RepairDispatchOutsideDetailInfo> result = service.searchDispatchOutsideEngineers(string);
        return new CommonResponse<>(result);
    }

}