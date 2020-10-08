package com.welearn.service;

import com.welearn.entity.po.alink.DeviceMonitorRecord;
import com.welearn.entity.qo.alink.DeviceMonitorRecordQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : DeviceMonitorRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface DeviceMonitorRecordService extends BaseService<DeviceMonitorRecord, DeviceMonitorRecordQueryCondition>{

}