package com.welearn.controller;

import com.welearn.cache.getter.EquipmentCacheGetter;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * Description : 数据统计分析接口
 * Created by Setsuna Jin on 2019/3/12.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private EquipmentCacheGetter equipmentCacheGetter;

    @RequestMapping(value = "/equipments")
    @ApiOperation(value = "获取以公司为基准的统计数据", httpMethod = "POST")
    public CommonResponse<Map<String, Object>> equipments(@RequestBody String companyId) {
        Map<String, Object> result = equipmentCacheGetter.getEquipmentsStatistics(companyId);
        if (Objects.isNull(result)){
            result = statisticsService.getEquipmentsStatistics(companyId);
        }
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/equipment")
    @ApiOperation(value = "获取以设备为基准的统计数据", httpMethod = "POST")
    public CommonResponse<Map<String, Object>> equipment(@RequestBody String equipmentId) {
        Map<String, Object> result = equipmentCacheGetter.getEquipmentStatistics(equipmentId);
        if (Objects.isNull(result)){
            result = statisticsService.getEquipmentStatistics(equipmentId);
        }
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/engineer")
    @ApiOperation(value = "获取以工程师为基准的统计数据", httpMethod = "POST")
    public CommonResponse<Map<String, Object>> engineer(@RequestBody String engineerId) {
        Map<String, Object> result = equipmentCacheGetter.getEngineerStatistics(engineerId);
        if (Objects.isNull(result)){
            result = statisticsService.getEngineerStatistics(engineerId);
        }
        return new CommonResponse<>(result);
    }
}
