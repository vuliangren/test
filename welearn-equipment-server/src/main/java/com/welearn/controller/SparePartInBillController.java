package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.vo.request.equipment.SparePartProcurementFinish;
import com.welearn.entity.vo.request.equipment.SparePartStockInAutoSubmit;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.entity.qo.equipment.SparePartInBillQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.SparePartInBillService;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/sparePartInBill")
@Api(value = "sparePartInBill 接口")
public class SparePartInBillController extends BaseController<SparePartInBill, SparePartInBillQueryCondition, SparePartInBillService>{

    @RequestMapping(value = "/failed")
    @ApiOperation(value = "入库单核验失败", httpMethod = "POST")
    public CommonResponse failed(@RequestBody SparePartInBill sparePartInBill)  {
        service.failed(sparePartInBill);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/finish")
    @ApiOperation(value = "入库单核验成功", httpMethod = "POST")
    public CommonResponse finish(@RequestBody SparePartInBill sparePartInBill)  {
        service.finish(sparePartInBill);
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/sparePartStockInAutoSubmit")
    @ApiOperation(value = "提交入库申请", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> sparePartStockInAutoSubmit(@RequestBody SparePartStockInAutoSubmit sparePartStockInAutoSubmit, @RequestUser User user)  {
        ApprovalApplication result = service.sparePartStockInAutoSubmit(sparePartStockInAutoSubmit.getBill(), sparePartStockInAutoSubmit.getItems(), user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/sparePartProcurementFinish")
    @ApiOperation(value = "sparePartProcurementFinish", httpMethod = "POST")
    public CommonResponse sparePartProcurementFinish(@RequestBody SparePartProcurementFinish sparePartProcurementFinish) throws Exception {
        service.sparePartProcurementFinish(sparePartProcurementFinish.getProcurement(), sparePartProcurementFinish.getDetails());
        return CommonResponse.getSuccessResponse();
    }
}