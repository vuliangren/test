package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.equipment.SparePartOutBill;
import com.welearn.entity.qo.equipment.SparePartOutBillQueryCondition;
import com.welearn.entity.vo.request.equipment.Finish;
import com.welearn.entity.vo.response.CommonResponse;

import java.util.List;

/**
 * Description : welearn-equipment-service / com.welearn.controller.SparePartOutBillController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface SparePartOutBillFeignClient {

    @RequestMapping(value = "/sparePartOutBill/finish")
    CommonResponse finish(Finish finish);

    @RequestMapping(value = "/sparePartOutBill/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/sparePartOutBill/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/sparePartOutBill/update")
    CommonResponse update(SparePartOutBill entity);

    @RequestMapping(value = "/sparePartOutBill/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/sparePartOutBill/create")
    CommonResponse<SparePartOutBill> create(SparePartOutBill entity);

    @RequestMapping(value = "/sparePartOutBill/search")
    CommonResponse<List<SparePartOutBill>> search(SparePartOutBillQueryCondition queryCondition);

    @RequestMapping(value = "/sparePartOutBill/select")
    CommonResponse<SparePartOutBill> select(String id);
}