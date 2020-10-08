package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.RepairInterrupt;
import com.welearn.entity.qo.equipment.RepairInterruptQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.RepairInterruptController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface RepairInterruptFeignClient {

    @RequestMapping(value = "/repairInterrupt/afterApplicationPass")
    CommonResponse afterApplicationPass(String string);

    @RequestMapping(value = "/repairInterrupt/afterApplicationReject")
    CommonResponse afterApplicationReject(String string);

    @RequestMapping(value = "/repairInterrupt/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/repairInterrupt/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/repairInterrupt/update")
    CommonResponse update(RepairInterrupt entity);

    @RequestMapping(value = "/repairInterrupt/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/repairInterrupt/create")
    CommonResponse<RepairInterrupt> create(RepairInterrupt entity);

    @RequestMapping(value = "/repairInterrupt/search")
    CommonResponse<List<RepairInterrupt>> search(RepairInterruptQueryCondition queryCondition);

    @RequestMapping(value = "/repairInterrupt/select")
    CommonResponse<RepairInterrupt> select(String id);
}