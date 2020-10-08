package com.welearn.feign.equipment;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.qo.equipment.EquipmentBorrowQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-equipment-service / com.welearn.controller.EquipmentBorrowController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-equipment-server", configuration = FeignConfiguration.class)
public interface EquipmentBorrowFeignClient {

    @RequestMapping(value = "/equipmentBorrow/afterBorrowApplicationPass")
    CommonResponse afterBorrowApplicationPass(String string);

    @RequestMapping(value = "/equipmentBorrow/afterBorrowApplicationReject")
    CommonResponse afterBorrowApplicationReject(String string);

    @RequestMapping(value = "/equipmentBorrow/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/equipmentBorrow/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/equipmentBorrow/update")
    CommonResponse update(EquipmentBorrow entity);

    @RequestMapping(value = "/equipmentBorrow/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/equipmentBorrow/create")
    CommonResponse<EquipmentBorrow> create(EquipmentBorrow entity);

    @RequestMapping(value = "/equipmentBorrow/search")
    CommonResponse<List<EquipmentBorrow>> search(EquipmentBorrowQueryCondition queryCondition);

    @RequestMapping(value = "/equipmentBorrow/select")
    CommonResponse<EquipmentBorrow> select(String id);
}