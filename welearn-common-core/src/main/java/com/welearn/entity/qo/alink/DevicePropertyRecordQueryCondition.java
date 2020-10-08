package com.welearn.entity.qo.alink;

import com.welearn.entity.po.alink.DevicePropertyRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * Description : DevicePropertyRecord Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/25 17:30:03
 * @see com.welearn.entity.po.alink.DevicePropertyRecord
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "DevicePropertyRecordQueryCondition", description = "DevicePropertyRecord 查询条件")
public class DevicePropertyRecordQueryCondition extends DevicePropertyRecord {
    private Date reportedAtStart;
    private Date reportedAtEnd;
}
