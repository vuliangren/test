package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.RepairHelpApply;
import com.welearn.entity.qo.equipment.RepairHelpApplyQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.RepairHelpApplyController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface RepairHelpApplyFeignClient {

    @RequestMapping(value = "/repairHelpApply/afterApplicationReject")
    CommonResponse afterApplicationReject(String string);

    @RequestMapping(value = "/repairHelpApply/afterApplicationPass")
    CommonResponse afterApplicationPass(String string);

    @RequestMapping(value = "/repairHelpApply/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/repairHelpApply/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/repairHelpApply/update")
    CommonResponse update(RepairHelpApply entity);

    @RequestMapping(value = "/repairHelpApply/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/repairHelpApply/create")
    CommonResponse<RepairHelpApply> create(RepairHelpApply entity);

    @RequestMapping(value = "/repairHelpApply/search")
    CommonResponse<List<RepairHelpApply>> search(RepairHelpApplyQueryCondition queryCondition);

    @RequestMapping(value = "/repairHelpApply/select")
    CommonResponse<RepairHelpApply> select(String id);
}