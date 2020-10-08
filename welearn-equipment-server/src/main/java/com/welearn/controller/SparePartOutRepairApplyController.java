package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.util.UUIDGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.SparePartOutBill;
import com.welearn.entity.po.equipment.SparePartOutRepairApply;
import com.welearn.entity.qo.equipment.SparePartOutRepairApplyQueryCondition;
import com.welearn.entity.vo.request.equipment.AfterApplyPass;
import com.welearn.entity.vo.request.equipment.SparePartOutRepairApplyAutoSubmit;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.SparePartOutRepairApplyService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/sparePartOutRepairApply")
@Api(value = "sparePartOutRepairApply 接口")
public class SparePartOutRepairApplyController extends BaseController<SparePartOutRepairApply, SparePartOutRepairApplyQueryCondition, SparePartOutRepairApplyService>{

    @RequestMapping(value = "/sparePartOutRepairApplyAutoSubmit")
    @ApiOperation(value = "自动创建并提交维修配件出库申请", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> sparePartOutRepairApplyAutoSubmit(@RequestBody SparePartOutRepairApplyAutoSubmit sparePartOutRepairApplyAutoSubmit, @RequestUser User user)  {
        ApprovalApplication result = service.sparePartOutRepairApplyAutoSubmit(sparePartOutRepairApplyAutoSubmit.getSparePartOutRepairApply(),
                sparePartOutRepairApplyAutoSubmit.getRepairReplacementIds(), user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/afterApplyPass")
    @ApiOperation(value = "申请审批通过后的系统内部回调处理", httpMethod = "POST")
    public CommonResponse afterApplyPass(@RequestBody AfterApplyPass afterApplyPass)  {
        service.afterApplyPass(afterApplyPass.getApplyId(), afterApplyPass.getSparePartOutBill());
        return CommonResponse.getSuccessResponse();
    }

}