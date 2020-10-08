package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.equipment.SparePartOutRepairApply;
import com.welearn.entity.qo.equipment.SparePartOutRepairApplyQueryCondition;
import com.welearn.entity.vo.request.equipment.AfterApplyPass;
import com.welearn.entity.vo.request.equipment.SparePartOutRepairApplyAutoSubmit;
import com.welearn.entity.vo.response.CommonResponse;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-equipment-service / com.welearn.controller.SparePartOutRepairApplyController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface SparePartOutRepairApplyFeignClient {

    @RequestMapping(value = "/sparePartOutRepairApply/sparePartOutRepairApplyAutoSubmit")
    CommonResponse<String> sparePartOutRepairApplyAutoSubmit(SparePartOutRepairApplyAutoSubmit sparePartOutRepairApplyAutoSubmit);

    @RequestMapping(value = "/sparePartOutRepairApply/afterApplyPass")
    CommonResponse afterApplyPass(AfterApplyPass afterApplyPass);

    @RequestMapping(value = "/sparePartOutRepairApply/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/sparePartOutRepairApply/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/sparePartOutRepairApply/update")
    CommonResponse update(SparePartOutRepairApply entity);

    @RequestMapping(value = "/sparePartOutRepairApply/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/sparePartOutRepairApply/create")
    CommonResponse<SparePartOutRepairApply> create(SparePartOutRepairApply entity);

    @RequestMapping(value = "/sparePartOutRepairApply/search")
    CommonResponse<List<SparePartOutRepairApply>> search(SparePartOutRepairApplyQueryCondition queryCondition);

    @RequestMapping(value = "/sparePartOutRepairApply/select")
    CommonResponse<SparePartOutRepairApply> select(String id);
}