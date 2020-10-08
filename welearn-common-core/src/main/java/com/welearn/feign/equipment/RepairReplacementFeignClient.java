package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.qo.equipment.RepairReplacementQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.RepairReplacementController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface RepairReplacementFeignClient {

    @RequestMapping(value = "/repairReplacement/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/repairReplacement/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/repairReplacement/update")
    CommonResponse update(RepairReplacement entity);

    @RequestMapping(value = "/repairReplacement/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/repairReplacement/create")
    CommonResponse<RepairReplacement> create(RepairReplacement entity);

    @RequestMapping(value = "/repairReplacement/search")
    CommonResponse<List<RepairReplacement>> search(RepairReplacementQueryCondition queryCondition);

    @RequestMapping(value = "/repairReplacement/select")
    CommonResponse<RepairReplacement> select(String id);
}