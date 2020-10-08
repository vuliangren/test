package com.welearn.entity.vo.response.alink;

import com.welearn.entity.po.alink.Device;
import com.welearn.entity.po.alink.DeviceGroup;
import com.welearn.entity.po.alink.ReDeviceGroupDevice;
import com.welearn.entity.po.alink.ReDeviceGroupUser;
import com.welearn.entity.po.equipment.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Description : 设备组详情
 * Created by Setsuna Jin on 2019/1/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeviceGroupInfo extends DeviceGroup {
    private List<ReDeviceGroupDevice> reDevices;
    private List<ReDeviceGroupUser> reUsers;
}
