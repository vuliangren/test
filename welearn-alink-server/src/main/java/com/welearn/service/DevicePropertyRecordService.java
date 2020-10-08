package com.welearn.service;

import com.welearn.entity.po.alink.DevicePropertyRecord;
import com.welearn.entity.qo.alink.DevicePropertyRecordQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description : DevicePropertyRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface DevicePropertyRecordService extends BaseService<DevicePropertyRecord, DevicePropertyRecordQueryCondition>{
    /**
     * 按小时统计某个设备的历史属性数据
     * @param iotId 设备iotId
     * @param reportedAtStart 属性上报开始时间
     * @param reportedAtEnd 属性上报截止时间
     * @return 统计数据
     */
    Map<Date, List<String>> statisticHourProperty(@NotBlank String iotId, @NotNull Date reportedAtStart, @NotNull Date reportedAtEnd);

    /**
     * 按小时统计某个设备的运行时间记录
     * @param iotId 设备iotId
     * @param reportedAtStart 记录上报开始时间
     * @param reportedAtEnd 记录上报截止时间
     * @return 统计数据
     */
    Map<Date, List<String>> statisticDayRunningTime(@NotBlank String iotId, @NotNull Date reportedAtStart, @NotNull Date reportedAtEnd);
}