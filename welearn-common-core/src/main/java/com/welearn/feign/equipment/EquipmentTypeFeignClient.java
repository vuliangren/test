package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.EquipmentTypeItem;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.equipment.EquipmentTypeInfo;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-equipment-service / com.welearn.controller.EquipmentTypeController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface EquipmentTypeFeignClient {

    @RequestMapping(value = "/equipmentType/searchInfoByNameOrId")
    CommonResponse<List<EquipmentTypeInfo>> searchInfoByNameOrId(String content);

    @RequestMapping(value = "/equipmentType/select")
    CommonResponse<EquipmentTypeItem> select(String id);
}