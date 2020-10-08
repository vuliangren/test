package com.welearn.feign.apply;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Description : welearn-apply-service / com.welearn.controller.SparePartStockInController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-apply-server", configuration = FeignConfiguration.class)
public interface OverPricePartProcurementApplicationFeignClient {

    @RequestMapping(value = "/overPricePartProcurement/apply")
    CommonResponse<ApprovalApplication> apply(Apply<RepairReplacement> entity);

    @RequestMapping(value = "/overPricePartProcurement/cancel")
    CommonResponse<Boolean> cancel(String applicationId);
}