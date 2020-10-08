package com.welearn.entity.qo.alink;

import com.welearn.entity.po.alink.Device;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : Device Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:38:42
 * @see Device
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "DeviceQueryCondition", description = "Device 查询条件")
public class DeviceQueryCondition extends Device {
    private Boolean withPropertySnapshot;
    private Boolean withDocumentFile;
    private Boolean ignoreJsonField;

    private String userId;
    private String groupId;

    private String rfidTagId;
}
