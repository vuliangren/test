package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.equipment.RepairPrecept;
import com.welearn.entity.qo.equipment.RepairPreceptQueryCondition;
import com.welearn.entity.vo.request.equipment.SearchByEquipmentId;
import com.welearn.entity.vo.response.CommonResponse;
import java.util.List;

/**
 * Description : welearn-equipment-service / com.welearn.controller.RepairPreceptController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface RepairPreceptFeignClient {

    @RequestMapping(value = "/repairPrecept/searchByEquipmentId")
    CommonResponse<List<RepairPrecept>> searchByEquipmentId(SearchByEquipmentId searchByEquipmentId);

    @RequestMapping(value = "/repairPrecept/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/repairPrecept/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/repairPrecept/update")
    CommonResponse update(RepairPrecept entity);

    @RequestMapping(value = "/repairPrecept/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/repairPrecept/create")
    CommonResponse<RepairPrecept> create(RepairPrecept entity);

    @RequestMapping(value = "/repairPrecept/search")
    CommonResponse<List<RepairPrecept>> search(RepairPreceptQueryCondition queryCondition);

    @RequestMapping(value = "/repairPrecept/select")
    CommonResponse<RepairPrecept> select(String id);
}