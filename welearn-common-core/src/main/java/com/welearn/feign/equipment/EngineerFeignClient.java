package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.qo.equipment.EngineerQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.EngineerController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface EngineerFeignClient {

    @RequestMapping(value = "/engineer/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/engineer/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/engineer/update")
    CommonResponse update(Engineer entity);

    @RequestMapping(value = "/engineer/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/engineer/create")
    CommonResponse<Engineer> create(Engineer entity);

    @RequestMapping(value = "/engineer/search")
    CommonResponse<List<Engineer>> search(EngineerQueryCondition queryCondition);

    @RequestMapping(value = "/engineer/select")
    CommonResponse<Engineer> select(String id);
}