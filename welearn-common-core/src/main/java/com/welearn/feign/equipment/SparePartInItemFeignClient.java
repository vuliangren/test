package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.SparePartInItem;
import com.welearn.entity.qo.equipment.SparePartInItemQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.SparePartInItemController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface SparePartInItemFeignClient {

    @RequestMapping(value = "/sparePartInItem/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/sparePartInItem/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/sparePartInItem/update")
    CommonResponse update(SparePartInItem entity);

    @RequestMapping(value = "/sparePartInItem/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/sparePartInItem/create")
    CommonResponse<SparePartInItem> create(SparePartInItem entity);

    @RequestMapping(value = "/sparePartInItem/search")
    CommonResponse<List<SparePartInItem>> search(SparePartInItemQueryCondition queryCondition);

    @RequestMapping(value = "/sparePartInItem/select")
    CommonResponse<SparePartInItem> select(String id);
}