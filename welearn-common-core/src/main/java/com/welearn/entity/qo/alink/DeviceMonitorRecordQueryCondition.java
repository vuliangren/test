package com.welearn.entity.qo.alink;

import com.welearn.entity.po.alink.DeviceMonitorRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * Description : DeviceMonitorRecord Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/10 14:18:31
 * @see com.welearn.entity.po.alink.DeviceMonitorRecord
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "DeviceMonitorRecordQueryCondition", description = "DeviceMonitorRecord 查询条件")
public class DeviceMonitorRecordQueryCondition extends DeviceMonitorRecord {
    private Date monitorAtStart;
    private Date monitorAtEnd;
}
