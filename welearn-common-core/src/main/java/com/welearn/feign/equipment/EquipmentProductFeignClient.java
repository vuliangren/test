package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.qo.equipment.EquipmentProductQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description : welearn-equipment-service / com.welearn.controller.EquipmentProductController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface EquipmentProductFeignClient {

    @RequestMapping(value = "/equipmentProduct/updateEquipments")
    CommonResponse updateEquipments(String productId);

    @RequestMapping(value = "/equipmentProduct/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/equipmentProduct/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/equipmentProduct/update")
    CommonResponse update(EquipmentProduct entity);

    @RequestMapping(value = "/equipmentProduct/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/equipmentProduct/create")
    CommonResponse<EquipmentProduct> create(EquipmentProduct entity);

    @RequestMapping(value = "/equipmentProduct/search")
    CommonResponse<List<EquipmentProduct>> search(EquipmentProductQueryCondition queryCondition);

    @RequestMapping(value = "/equipmentProduct/select")
    CommonResponse<EquipmentProduct> select(String id);
}