package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.User;
import com.welearn.entity.po.finance.StockPackage;
import com.welearn.entity.qo.finance.StockPackageQueryCondition;
import com.welearn.entity.vo.request.finance.Merge;
import com.welearn.entity.vo.request.finance.Split;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.StockPackageService;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/stockPackage")
public class StockPackageController extends BaseController<StockPackage, StockPackageQueryCondition, StockPackageService>{

    @RequestMapping(value = "/split")
    @ApiOperation(value = "货运包装拆分", httpMethod = "POST")
    public CommonResponse<StockPackage> split(@RequestBody Split split, @RequestUser User user)  {
        StockPackage result = service.split(split.getStockPackageId(), split.getCount(), user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/merge")
    @ApiOperation(value = "货运包装合并", httpMethod = "POST")
    public CommonResponse<StockPackage> merge(@RequestBody Merge merge, @RequestUser User user)  {
        StockPackage result = service.merge(merge.getStockPackageIds(), merge.getMergePackageId(), user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/batchCreatePackages")
    @ApiOperation(value = "批量创建货运包装", httpMethod = "POST")
    public CommonResponse<List<StockPackage>> batchCreatePackages(@RequestBody List<StockPackage> list)  {
        List<StockPackage> result = service.batchCreatePackages(list);
        return new CommonResponse<>(result);
    }

}