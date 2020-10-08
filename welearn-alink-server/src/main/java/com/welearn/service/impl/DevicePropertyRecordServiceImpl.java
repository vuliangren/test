package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.JsonObject;
import com.welearn.dictionary.alink.DevicePropertyRecordTypeConst;
import com.welearn.dictionary.alink.EventTypeConst;
import com.welearn.dictionary.api.ServiceConst;
import com.welearn.dictionary.storage.StatisticRecordTypeConst;
import com.welearn.entity.po.alink.Device;
import com.welearn.entity.po.alink.DevicePropertyRecord;
import com.welearn.entity.po.alink.Product;
import com.welearn.entity.po.storage.StatisticsRecord;
import com.welearn.entity.qo.alink.DevicePropertyRecordQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.storage.StatisticsRecordFeignClient;
import com.welearn.mapper.DeviceEventMapper;
import com.welearn.mapper.DevicePropertyRecordMapper;
import com.welearn.service.DevicePropertyRecordService;
import com.welearn.service.DeviceService;
import com.welearn.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

/**
 * Description : DevicePropertyRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class DevicePropertyRecordServiceImpl extends BaseServiceImpl<DevicePropertyRecord,DevicePropertyRecordQueryCondition,DevicePropertyRecordMapper>
        implements DevicePropertyRecordService{

    @Autowired
    private ProductService productService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private StatisticsRecordFeignClient statisticsRecordFeignClient;

    @Autowired
    private DevicePropertyRecordMapper devicePropertyRecordMapper;

    @Autowired
    private DeviceEventMapper deviceEventMapper;

    @Override
    DevicePropertyRecordMapper getMapper() {
        return devicePropertyRecordMapper;
    }

    private Map<Date, List<String>> statistic(String iotId, Date reportedAtStart, Date reportedAtEnd, DevicePropertyRecordTypeConst type) {
        DevicePropertyRecordQueryCondition condition = new DevicePropertyRecordQueryCondition();
        condition.setReportedAtStart(reportedAtStart);
        condition.setReportedAtEnd(reportedAtEnd);
        condition.setIotId(iotId);
        condition.setType(type.ordinal());
        List<DevicePropertyRecord> propertyRecords = this.search(condition);
        Map<Date, List<String>> result = new HashMap<>();
        propertyRecords.forEach(record -> {
            String dataJson = record.getDataJson();
            if (StringUtils.isBlank(dataJson))
                return;
            result.put(record.getReportedAt(), JSON.parseArray(dataJson, String.class));
        });
        return result;
    }

    /**
     * 按小时统计某个设备的历史属性数据
     * @param iotId 设备iotId
     * @param reportedAtStart 属性上报开始时间
     * @param reportedAtEnd 属性上报截止时间
     * @return 统计数据
     */
    @Override
    public Map<Date, List<String>> statisticHourProperty(String iotId, Date reportedAtStart, Date reportedAtEnd) {
        Device device = deviceService.selectByIotId(iotId);
        if (Objects.isNull(device) || !device.getIsEnable())
            throw new BusinessVerifyFailedException("iotId 非法");
        if (reportedAtEnd.getTime() - reportedAtStart.getTime() > 90*60*1000)
            throw new BusinessVerifyFailedException("所选时间段超过最大限制 60 分钟");
        return this.statistic(iotId, reportedAtStart, reportedAtEnd, DevicePropertyRecordTypeConst.UPLOAD_PROPERTY);
    }

    /**
     * 按小时统计某个设备的运行时间记录
     * @param iotId 设备iotId
     * @param reportedAtStart 记录上报开始时间
     * @param reportedAtEnd 记录上报截止时间
     * @return 统计数据
     */
    @Override
    public Map<Date, List<String>> statisticDayRunningTime(String iotId, Date reportedAtStart, Date reportedAtEnd) {
        Device device = deviceService.selectByIotId(iotId);
        if (Objects.isNull(device) || !device.getIsEnable())
            throw new BusinessVerifyFailedException("iotId 非法");
        // 实际限制为只存储最近45天的运行时间记录数据
        if (reportedAtEnd.getTime() - reportedAtStart.getTime() > 25 * 60 * 60 * 1000)
            throw new BusinessVerifyFailedException("所选时间段超过最大限制 1 天");
        return this.statistic(iotId, reportedAtStart, reportedAtEnd, DevicePropertyRecordTypeConst.RUNNING_TIME);
    }

    // 每天二点钟 删除之前一周的上报属性记录
    @Scheduled(cron = "0 0 2 * * ?")
    public void autoDeleteLastWeekRecord() {
        log.info("设备上报记录定时删除任务 开始执行");
        try {

            Date day2Ago = new Date(System.currentTimeMillis() - 172800000L);
            devicePropertyRecordMapper.deleteByReportedAtLessThan(day2Ago, DevicePropertyRecordTypeConst.UPLOAD_PROPERTY.ordinal());
            log.info("* 删除  2 天前的上报属性数据");

            Date day35Ago = new Date(System.currentTimeMillis() - 3024000000L);
            devicePropertyRecordMapper.deleteByReportedAtLessThan(day35Ago, DevicePropertyRecordTypeConst.RUNNING_TIME.ordinal());
            log.info("* 删除 35 天前的运行时间数据");

            Date day7Ago = new Date(System.currentTimeMillis() - 604800000L);
            deviceEventMapper.deleteByReportedAtLessThan(day7Ago, EventTypeConst.INFO.ordinal());
            log.info("* 删除  7 天前的通知事件数据");

        } catch (Exception e) {
            log.error("设备上报记录定时删除任务 执行失败", e);
        }
        log.info("设备上报记录定时删除任务 执行成功");
    }

    // 每天一点钟 对之前一天的属性记录进行合并归类, 创建一整天的统计记录
    @Scheduled(cron = "0 0 1 * * ?")
    public void autoMergeLastDayRecord() {
        log.info("自动合并归档前一天属性记录任务 开始执行");
        // 计算前天的开始和结束时间
        Date startAt = new DateTime().withTimeAtStartOfDay().minusDays(1).toDate();
        Date endAt = new DateTime().withTimeAtStartOfDay().minusSeconds(1).toDate();

        boolean isMonthStart = new DateTime().getDayOfMonth() == 1;

        List<String> iotIds = deviceService.selectIotIds();
        for (String iotId : iotIds) {
            // 创建一整天的属性上报数据统计JSON
            Map<Date, List<String>> uploadPropertyDayStatisticsData = this.statistic(iotId, startAt, endAt, DevicePropertyRecordTypeConst.UPLOAD_PROPERTY);
            if (!uploadPropertyDayStatisticsData.isEmpty()) {
                StatisticsRecord uploadPropertyRecord = new StatisticsRecord();
                uploadPropertyRecord.setStartAt(startAt);
                uploadPropertyRecord.setEndAt(endAt);
                uploadPropertyRecord.setDataJson(JSON.toJSONString(uploadPropertyDayStatisticsData, SerializerFeature.WriteDateUseDateFormat));
                uploadPropertyRecord.setRefId(iotId);
                uploadPropertyRecord.setService(ServiceConst.ALINK_SERVER.getServiceApplicationName());
                uploadPropertyRecord.setType(StatisticRecordTypeConst.DAY_UPLOAD_PROPERTY.name());
                uploadPropertyRecord.setRemark("设备一天的上报属性统计合并记录");
                statisticsRecordFeignClient.create(uploadPropertyRecord);
                log.info("创建设备: {} 的 {} 统计记录", iotId, StatisticRecordTypeConst.DAY_UPLOAD_PROPERTY);
            }
            // 如果是月初 则合并上月的所有运行时间记录
            if (isMonthStart) {
                // 创建一整天的运行时间数据统计JSON
                Map<Date, List<String>> runningTimeDayStatisticsData = this.statistic(iotId,
                        new DateTime().withTimeAtStartOfDay().minusMonths(1).toDate(), endAt, DevicePropertyRecordTypeConst.RUNNING_TIME);
                if (!runningTimeDayStatisticsData.isEmpty()) {
                    StatisticsRecord runningTimeRecord = new StatisticsRecord();
                    runningTimeRecord.setStartAt(startAt);
                    runningTimeRecord.setEndAt(endAt);
                    runningTimeRecord.setDataJson(JSON.toJSONString(runningTimeDayStatisticsData, SerializerFeature.WriteDateUseDateFormat));
                    runningTimeRecord.setRefId(iotId);
                    runningTimeRecord.setService(ServiceConst.ALINK_SERVER.getServiceApplicationName());
                    runningTimeRecord.setType(StatisticRecordTypeConst.MONTH_RUNNING_TIME.name());
                    runningTimeRecord.setRemark("设备一月的运行时间统计合并记录");
                    statisticsRecordFeignClient.create(runningTimeRecord);
                    log.info("创建设备: {} 的 {} 统计记录", iotId, StatisticRecordTypeConst.MONTH_RUNNING_TIME);
                }
            }
        }
        log.info("自动合并归档前一天属性记录任务 执行成功");
    }
}
