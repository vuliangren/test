package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.response.equipment.EquipmentScrapInfo;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import com.welearn.service.EquipmentBorrowService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.EquipmentScrapApply;
import com.welearn.entity.qo.equipment.EquipmentScrapApplyQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.EquipmentScrapApplyService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentScrapApply")
@Api(value = "equipmentScrapApply 接口")
public class EquipmentScrapApplyController extends BaseController<EquipmentScrapApply, EquipmentScrapApplyQueryCondition, EquipmentScrapApplyService>{

    @RequestMapping(value = "/afterApplicationPass")
    @ApiOperation(value = "afterApplicationPass", httpMethod = "POST")
    public CommonResponse afterApplicationPass(@RequestBody String equipmentScrapApplyId)  {
        service.afterApplicationPass(equipmentScrapApplyId);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询申请报废的设备信息", httpMethod = "POST")
    public CommonResponse<List<EquipmentScrapInfo>> searchInfo(@RequestBody EquipmentScrapApplyQueryCondition equipmentScrapApplyQueryCondition) {
        List<EquipmentScrapInfo> result = service.searchInfo(equipmentScrapApplyQueryCondition);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/equipmentScrapApplyAutoSubmit")
    @ApiOperation(value = "自动创建报废申请", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> equipmentScrapApplyAutoSubmit(@RequestBody EquipmentScrapApply equipmentScrapApply, @RequestUser User user) {
        ApprovalApplication result = service.equipmentScrapApplyAutoSubmit(equipmentScrapApply, user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/scrapProcess")
    @ApiOperation(value = "封存设备报废处理", httpMethod = "POST")
    public CommonResponse scrapProcess(@RequestBody String equipmentId)  {
        service.scrapProcess(equipmentId);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/sealedStorage")
    @ApiOperation(value = "报废设备封存入库", httpMethod = "POST")
    public CommonResponse sealedStorage(@RequestBody String equipmentId)  {
        service.sealedStorage(equipmentId);
        return CommonResponse.getSuccessResponse();
    }
}