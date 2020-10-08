package com.welearn.service.impl;

import com.welearn.entity.po.alink.DeviceMonitorRecord;
import com.welearn.entity.qo.alink.DeviceMonitorRecordQueryCondition;
import com.welearn.mapper.DeviceMonitorRecordMapper;
import com.welearn.service.DeviceMonitorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : DeviceMonitorRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class DeviceMonitorRecordServiceImpl extends BaseServiceImpl<DeviceMonitorRecord,DeviceMonitorRecordQueryCondition,DeviceMonitorRecordMapper>
        implements DeviceMonitorRecordService{
    
    @Autowired
    private DeviceMonitorRecordMapper deviceMonitorRecordMapper;
    
    @Override
    DeviceMonitorRecordMapper getMapper() {
        return deviceMonitorRecordMapper;
    }

}
