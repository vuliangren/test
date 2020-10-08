package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.request.equipment.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.RepairDispatchInside;
import com.welearn.entity.qo.equipment.RepairDispatchInsideQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.equipment.RepairDispatchInfo;
import com.welearn.service.RepairDispatchInsideService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/repairDispatchInside")
@Api(value = "repairDispatchInside 接口")
public class RepairDispatchInsideController extends BaseController<RepairDispatchInside, RepairDispatchInsideQueryCondition, RepairDispatchInsideService>{

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询报修和派工信息", httpMethod = "POST")
    public CommonResponse<List<RepairDispatchInfo>> searchInfo(@RequestBody RepairDispatchInsideQueryCondition repairDispatchInsideQueryCondition)  {
        List<RepairDispatchInfo> result = service.searchInfo(repairDispatchInsideQueryCondition);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/receiveDispatch")
    @ApiOperation(value = "领取派工", httpMethod = "POST")
    public CommonResponse receiveDispatch(@RequestBody String string)  {
        service.receiveDispatch(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/createDispatch")
    @ApiOperation(value = "创建派工 或 重新派工", httpMethod = "POST")
    public CommonResponse createDispatch(@RequestBody CreateDispatch createDispatch)  {
        service.createDispatch(createDispatch.getRequestId(), createDispatch.getEngineerIdList());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/applyReDispatch")
    @ApiOperation(value = "申请重派", httpMethod = "POST")
    public CommonResponse applyReDispatch(@RequestBody ApplyReDispatch applyReDispatch)  {
        service.applyReDispatch(applyReDispatch.getDispatchInsideId(), applyReDispatch.getReason());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/cancelDispatch")
    @ApiOperation(value = "主动取消派工 或 同意工程师发起的重新申请", httpMethod = "POST")
    public CommonResponse cancelDispatch(@RequestBody CancelDispatch cancelDispatch)  {
        service.cancelDispatch(cancelDispatch.getDispatchInsideId(), cancelDispatch.getReason());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/askHelp")
    @ApiOperation(value = "申请外部援助(返回申请) 或 申请厂商维修(返回null) 接口会自行判断", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> askHelp(@RequestBody AskHelp askHelp, @RequestUser User user) {
        ApprovalApplication result = service.askHelp(askHelp.getDispatchInsideId(), askHelp.getDescription(), user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/startRepair")
    @ApiOperation(value = "开始维修 检查报修是否属实", httpMethod = "POST")
    public CommonResponse startRepair(@RequestBody StartRepair startRepair)  {
        service.startRepair(startRepair.getDispatchInsideId(), startRepair.getIsTrue(), startRepair.getReason());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/endRepair")
    @ApiOperation(value = "结束维修", httpMethod = "POST")
    public CommonResponse endRepair(@RequestBody EndRepairInside endRepair)  {
        service.endRepair(endRepair.getDispatchId(), endRepair.getResult(), endRepair.getReason());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/continueRepair")
    @ApiOperation(value = "继续维修", httpMethod = "POST")
    public CommonResponse continueRepair(@RequestBody String dispatchInsideId)  {
        service.continueRepair(dispatchInsideId);
        return CommonResponse.getSuccessResponse();
    }
}