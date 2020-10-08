package com.welearn.feign.apply;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.equipment.EquipmentScrapApply;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Description : welearn-apply-service / com.welearn.controller.EquipmentScrapApplyController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-apply-server", configuration = FeignConfiguration.class)
public interface EquipmentScrapApplyFeignClient {

    @RequestMapping(value = "/equipmentScrapApply/apply")
    CommonResponse<ApprovalApplication> apply(Apply<EquipmentScrapApply> entity);
}