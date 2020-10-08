package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.equipment.EquipmentQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.equipment.EquipmentInitResult;
import java.util.List;

/**
 * Description : welearn-equipment-service / com.welearn.controller.EquipmentController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface EquipmentFeignClient {

    @RequestMapping(value = "/equipment/procurementEquipmentInit")
    CommonResponse<EquipmentInitResult> procurementEquipmentInit(List<ProcurementDetail> list);

    @RequestMapping(value = "/equipment/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/equipment/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/equipment/update")
    CommonResponse update(Equipment entity);

    @RequestMapping(value = "/equipment/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/equipment/create")
    CommonResponse<Equipment> create(Equipment entity);

    @RequestMapping(value = "/equipment/search")
    CommonResponse<List<Equipment>> search(EquipmentQueryCondition queryCondition);

    @RequestMapping(value = "/equipment/select")
    CommonResponse<Equipment> select(String id);
}