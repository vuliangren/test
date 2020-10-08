package com.welearn.entity.qo.alink;

import com.welearn.entity.po.alink.DeviceEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : DeviceEvent Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:38:44
 * @see DeviceEvent
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "DeviceEventQueryCondition", description = "DeviceEvent 查询条件")
public class DeviceEventQueryCondition extends DeviceEvent {

    private Integer typeGreaterThan;
}
