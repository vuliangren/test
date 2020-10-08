package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.EquipmentScrapApply;
import com.welearn.entity.qo.equipment.EquipmentScrapApplyQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.EquipmentScrapApplyController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface EquipmentScrapApplyFeignClient {

    @RequestMapping(value = "/equipmentScrapApply/afterApplicationPass")
    CommonResponse afterApplicationPass(String equipmentScrapApplyId);

    @RequestMapping(value = "/equipmentScrapApply/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/equipmentScrapApply/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/equipmentScrapApply/update")
    CommonResponse update(EquipmentScrapApply entity);

    @RequestMapping(value = "/equipmentScrapApply/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/equipmentScrapApply/create")
    CommonResponse<EquipmentScrapApply> create(EquipmentScrapApply entity);

    @RequestMapping(value = "/equipmentScrapApply/search")
    CommonResponse<List<EquipmentScrapApply>> search(EquipmentScrapApplyQueryCondition queryCondition);

    @RequestMapping(value = "/equipmentScrapApply/select")
    CommonResponse<EquipmentScrapApply> select(String id);
}