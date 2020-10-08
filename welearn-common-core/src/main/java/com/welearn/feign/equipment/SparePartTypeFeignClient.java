package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.SparePartType;
import com.welearn.entity.qo.equipment.SparePartTypeQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.SparePartTypeController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface SparePartTypeFeignClient {

    @RequestMapping(value = "/sparePartType/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/sparePartType/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/sparePartType/update")
    CommonResponse update(SparePartType entity);

    @RequestMapping(value = "/sparePartType/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/sparePartType/create")
    CommonResponse<SparePartType> create(SparePartType entity);

    @RequestMapping(value = "/sparePartType/search")
    CommonResponse<List<SparePartType>> search(SparePartTypeQueryCondition queryCondition);

    @RequestMapping(value = "/sparePartType/select")
    CommonResponse<SparePartType> select(String id);
}