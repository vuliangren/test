package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.request.equipment.CancelBorrow;
import com.welearn.entity.vo.request.equipment.GiveBack;
import com.welearn.entity.vo.request.equipment.LoanOut;
import com.welearn.entity.vo.response.equipment.EquipmentBorrowInfo;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.qo.equipment.EquipmentBorrowQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.EquipmentBorrowService;

import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipmentBorrow")
@Api(value = "equipmentBorrow 接口")
public class EquipmentBorrowController extends BaseController<EquipmentBorrow, EquipmentBorrowQueryCondition, EquipmentBorrowService> {

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询借入借出设备信息", httpMethod = "POST")
    public CommonResponse<List<EquipmentBorrowInfo>> searchInfo(@RequestBody EquipmentBorrowQueryCondition equipmentBorrowQueryCondition) {
        List<EquipmentBorrowInfo> result = service.searchInfo(equipmentBorrowQueryCondition);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/afterBorrowApplicationReject")
    @ApiOperation(value = "afterBorrowApplicationReject", httpMethod = "POST")
    public CommonResponse afterBorrowApplicationReject(@RequestBody String string)  {
        service.afterBorrowApplicationReject(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/afterBorrowApplicationPass")
    @ApiOperation(value = "afterBorrowApplicationPass", httpMethod = "POST")
    public CommonResponse afterBorrowApplicationPass(@RequestBody String string)  {
        service.afterBorrowApplicationPass(string);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/loanOut")
    @ApiOperation(value = "设备借出确认", httpMethod = "POST")
    public CommonResponse loanOut(@RequestBody LoanOut loanOut)  {
        service.loanOut(loanOut.getBorrow(), loanOut.getAccessories());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/giveBack")
    @ApiOperation(value = "设备归还确认", httpMethod = "POST")
    public CommonResponse giveBack(@RequestBody GiveBack giveBack)  {
        service.giveBack(giveBack.getBorrow(), giveBack.getAccessories());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/cancel")
    @ApiOperation(value = "取消设备借用", httpMethod = "POST")
    public CommonResponse cancel(@RequestBody CancelBorrow cancel)  {
        service.cancel(cancel.getBorrowId(), cancel.getReason());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/equipmentBorrowApplyAutoSubmit")
    @ApiOperation(value = "设备借用申请自动提交", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> equipmentBorrowApplyAutoSubmit(@RequestBody EquipmentBorrow equipmentBorrow, @RequestUser User user)  {
        ApprovalApplication result = service.equipmentBorrowApplyAutoSubmit(equipmentBorrow, user);
        return new CommonResponse<>(result);
    }

}