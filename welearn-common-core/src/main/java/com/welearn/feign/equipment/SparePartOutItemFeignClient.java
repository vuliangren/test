package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.SparePartOutItem;
import com.welearn.entity.qo.equipment.SparePartOutItemQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.SparePartOutItemController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface SparePartOutItemFeignClient {

    @RequestMapping(value = "/sparePartOutItem/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/sparePartOutItem/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/sparePartOutItem/update")
    CommonResponse update(SparePartOutItem entity);

    @RequestMapping(value = "/sparePartOutItem/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/sparePartOutItem/create")
    CommonResponse<SparePartOutItem> create(SparePartOutItem entity);

    @RequestMapping(value = "/sparePartOutItem/search")
    CommonResponse<List<SparePartOutItem>> search(SparePartOutItemQueryCondition queryCondition);

    @RequestMapping(value = "/sparePartOutItem/select")
    CommonResponse<SparePartOutItem> select(String id);
}