package com.welearn.feign.storage;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.storage.StatisticsRecord;
import com.welearn.entity.qo.storage.StatisticsRecordQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-storage-service / com.welearn.controller.StatisticsRecordController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-storage-server", configuration = FeignConfiguration.class)
public interface StatisticsRecordFeignClient {

    @RequestMapping(value = "/statisticsRecord/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/statisticsRecord/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/statisticsRecord/update")
    CommonResponse update(StatisticsRecord entity);

    @RequestMapping(value = "/statisticsRecord/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/statisticsRecord/create")
    CommonResponse<StatisticsRecord> create(StatisticsRecord entity);

    @RequestMapping(value = "/statisticsRecord/search")
    CommonResponse<List<StatisticsRecord>> search(StatisticsRecordQueryCondition queryCondition);

    @RequestMapping(value = "/statisticsRecord/select")
    CommonResponse<StatisticsRecord> select(String id);
}