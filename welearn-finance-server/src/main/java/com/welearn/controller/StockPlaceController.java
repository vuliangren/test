package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.finance.StockPlace;
import com.welearn.entity.qo.finance.StockPlaceQueryCondition;
import com.welearn.entity.vo.request.finance.DepartmentStockInit;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.StockPlaceService;
import java.lang.String;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/stockPlace")
public class StockPlaceController extends BaseController<StockPlace, StockPlaceQueryCondition, StockPlaceService>{
    @RequestMapping(value = "/departmentStockInit")
    @ApiOperation(value = "初始化创建部门库存信息", httpMethod = "POST")
    public CommonResponse departmentStockInit(@RequestBody DepartmentStockInit departmentStockInit)  {
        service.departmentStockInit(departmentStockInit.getCompanyId(), departmentStockInit.getDepartmentId(), departmentStockInit.getDepartmentName(), departmentStockInit.getBuildingId(), departmentStockInit.getFloorId());
        return CommonResponse.getSuccessResponse();
    }

}