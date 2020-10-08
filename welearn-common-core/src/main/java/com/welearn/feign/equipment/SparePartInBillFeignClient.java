package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.equipment.SparePartInBill;
import com.welearn.entity.qo.equipment.SparePartInBillQueryCondition;
import com.welearn.entity.vo.request.equipment.SparePartProcurementFinish;
import com.welearn.entity.vo.request.equipment.SparePartStockInAutoSubmit;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.SparePartInBillController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface SparePartInBillFeignClient {

    @RequestMapping(value = "/sparePartInBill/sparePartStockInAutoSubmit")
    CommonResponse<ApprovalApplication> sparePartStockInAutoSubmit(SparePartStockInAutoSubmit sparePartStockInAutoSubmit);

    @RequestMapping(value = "/sparePartInBill/sparePartProcurementFinish")
    CommonResponse sparePartProcurementFinish(SparePartProcurementFinish sparePartProcurementFinish);

    @RequestMapping(value = "/sparePartInBill/failed")
    CommonResponse failed(SparePartInBill sparePartInBill);

    @RequestMapping(value = "/sparePartInBill/finish")
    CommonResponse finish(SparePartInBill sparePartInBill);

    @RequestMapping(value = "/sparePartInBill/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/sparePartInBill/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/sparePartInBill/update")
    CommonResponse update(SparePartInBill entity);

    @RequestMapping(value = "/sparePartInBill/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/sparePartInBill/create")
    CommonResponse<SparePartInBill> create(SparePartInBill entity);

    @RequestMapping(value = "/sparePartInBill/search")
    CommonResponse<List<SparePartInBill>> search(SparePartInBillQueryCondition queryCondition);

    @RequestMapping(value = "/sparePartInBill/select")
    CommonResponse<SparePartInBill> select(String id);
}