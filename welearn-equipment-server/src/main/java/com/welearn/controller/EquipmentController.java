package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.vo.request.equipment.CreateFromProduct;
import com.welearn.entity.vo.response.common.LocationInfo;
import com.welearn.entity.vo.response.equipment.EquipmentInfo;
import com.welearn.generator.ControllerGenerator;
import com.welearn.generator.FeignClientGenerator;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.qo.equipment.EquipmentQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.equipment.EquipmentInitResult;
import com.welearn.service.EquipmentService;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/equipment")
public class EquipmentController extends BaseController<Equipment, EquipmentQueryCondition, EquipmentService>{

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "查询设备详情", httpMethod = "POST")
    public CommonResponse<List<EquipmentInfo>> searchInfo(@RequestBody EquipmentQueryCondition condition) {
        return new CommonResponse<>(service.searchInfo(condition));
    }

    @RequestMapping(value = "/searchDepartmentEquipmentOutlook")
    @ApiOperation(value = "查询部门设备简述信息", httpMethod = "POST")
    public CommonResponse<List<EquipmentInfo>> searchDepartmentEquipmentOutlook(@RequestBody EquipmentQueryCondition condition) {
        return new CommonResponse<>(service.searchDepartmentEquipmentOutlook(condition));
    }

    @RequestMapping(value = "/procurementEquipmentInit")
    @ApiOperation(value = "procurementEquipmentInit", httpMethod = "POST")
    public CommonResponse<EquipmentInitResult> procurementEquipmentInit(@RequestBody List<ProcurementDetail> list) {
        EquipmentInitResult result = service.procurementEquipmentInit(list);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/createFromProduct")
    @ApiOperation(value = "根据设备产品创建设备", httpMethod = "POST")
    public CommonResponse<EquipmentInitResult> createFromProduct(@RequestBody CreateFromProduct create, @RequestUser User user) {
        EquipmentInitResult result = service.productEquipmentInit(create.getProductId(), create.getInitCount(),
                create.getProcurementId(), create.getDetailId(), create.getGuaranteeRepairExpiredAt(), user.getCompanyId(), create.getDepartmentId(), null);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/locationInfo")
    @ApiOperation(value = "获取设备的位置信息(缓存有效期3个小时)", httpMethod = "POST")
    public CommonResponse<LocationInfo> locationInfo(@RequestBody String string)  {
        LocationInfo result = service.locationInfo(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/updateWithCheck")
    @ApiOperation(value = "带检查的更新, 可自动更新产品类型相关数据", httpMethod = "POST")
    public CommonResponse updateWithCheck(@RequestBody Equipment equipment)  {
        service.updateWithCheck(equipment);
        return CommonResponse.getSuccessResponse();
    }
}