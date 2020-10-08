package com.welearn.entity.qo.alink;

import com.welearn.entity.po.alink.DeviceGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : DeviceGroup Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/7/1 11:26:33
 * @see com.welearn.entity.po.alink.DeviceGroup
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "DeviceGroupQueryCondition", description = "DeviceGroup 查询条件")
public class DeviceGroupQueryCondition extends DeviceGroup {
    private String userId;
    private String iotId;
}
