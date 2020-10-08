package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.SparePartOutBill;
import com.welearn.entity.qo.equipment.SparePartOutBillQueryCondition;
import com.welearn.entity.vo.request.equipment.Finish;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.SparePartOutBillService;
import java.lang.String;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/sparePartOutBill")
@Api(value = "sparePartOutBill 接口")
public class SparePartOutBillController extends BaseController<SparePartOutBill, SparePartOutBillQueryCondition, SparePartOutBillService>{

    @RequestMapping(value = "/finish")
    @ApiOperation(value = "出库单接收人签字确认", httpMethod = "POST")
    public CommonResponse finish(@RequestBody Finish finish)  {
        service.finish(finish.getSparePartOutBillId(), finish.getRecipientSignatureId());
        return CommonResponse.getSuccessResponse();
    }

}