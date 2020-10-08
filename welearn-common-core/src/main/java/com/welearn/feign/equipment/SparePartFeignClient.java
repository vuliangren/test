package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.SparePart;
import com.welearn.entity.qo.equipment.SparePartQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.SparePartController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface SparePartFeignClient {

    @RequestMapping(value = "/sparePart/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/sparePart/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/sparePart/update")
    CommonResponse update(SparePart entity);

    @RequestMapping(value = "/sparePart/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/sparePart/create")
    CommonResponse<SparePart> create(SparePart entity);

    @RequestMapping(value = "/sparePart/search")
    CommonResponse<List<SparePart>> search(SparePartQueryCondition queryCondition);

    @RequestMapping(value = "/sparePart/select")
    CommonResponse<SparePart> select(String id);
}