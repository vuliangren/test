package com.welearn.feign.procurement;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.procurement.ProcurementDetailQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-procurement-service / com.welearn.controller.ProcurementDetailController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-procurement-server", configuration = FeignConfiguration.class)
public interface ProcurementDetailFeignClient {

    @RequestMapping(value = "/procurementDetail/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/procurementDetail/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/procurementDetail/update")
    CommonResponse update(ProcurementDetail entity);

    @RequestMapping(value = "/procurementDetail/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/procurementDetail/create")
    CommonResponse<ProcurementDetail> create(ProcurementDetail entity);

    @RequestMapping(value = "/procurementDetail/search")
    CommonResponse<List<ProcurementDetail>> search(ProcurementDetailQueryCondition queryCondition);

    @RequestMapping(value = "/procurementDetail/select")
    CommonResponse<ProcurementDetail> select(String id);
}