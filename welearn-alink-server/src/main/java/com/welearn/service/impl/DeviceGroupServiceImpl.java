package com.welearn.service.impl;

import com.welearn.dictionary.alink.DeviceGroupTypeConst;
import com.welearn.entity.po.alink.DeviceGroup;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.alink.DeviceGroupQueryCondition;
import com.welearn.entity.qo.alink.ReDeviceGroupDeviceQueryCondition;
import com.welearn.entity.qo.alink.ReDeviceGroupUserQueryCondition;
import com.welearn.entity.vo.response.alink.DeviceGroupInfo;
import com.welearn.feign.common.UserFeignClient;
import com.welearn.mapper.DeviceGroupMapper;
import com.welearn.service.DeviceGroupService;
import com.welearn.service.ReDeviceGroupDeviceService;
import com.welearn.service.ReDeviceGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description : DeviceGroupService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class DeviceGroupServiceImpl extends BaseServiceImpl<DeviceGroup,DeviceGroupQueryCondition,DeviceGroupMapper>
        implements DeviceGroupService{
    
    @Autowired
    private DeviceGroupMapper deviceGroupMapper;

    @Autowired
    private ReDeviceGroupDeviceService reDeviceGroupDeviceService;

    @Autowired
    private ReDeviceGroupUserService reDeviceGroupUserService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    DeviceGroupMapper getMapper() {
        return deviceGroupMapper;
    }

    /**
     * 查询设备分组详情信息
     *
     * @param condition 查询条件
     * @return 设备分组列表
     */
    @Override
    public List<DeviceGroupInfo> searchInfo(DeviceGroupQueryCondition condition) {
        List<DeviceGroup> search = this.search(condition);
        List<DeviceGroupInfo> result = new ArrayList<>();
        search.forEach(deviceGroup -> {
            DeviceGroupInfo deviceGroupInfo = new DeviceGroupInfo();
            deviceGroupInfo.setId(deviceGroup.getId());
            deviceGroupInfo.setIsEnable(deviceGroup.getIsEnable());
            deviceGroupInfo.setCreatedAt(deviceGroup.getCreatedAt());
            deviceGroupInfo.setUpdatedAt(deviceGroup.getUpdatedAt());
            deviceGroupInfo.setType(deviceGroup.getType());
            deviceGroupInfo.setName(deviceGroup.getName());
            deviceGroupInfo.setDescription(deviceGroup.getDescription());
            // 查询相关的设备信息
            ReDeviceGroupDeviceQueryCondition deviceQueryCondition = new ReDeviceGroupDeviceQueryCondition();
            deviceQueryCondition.setIsEnable(true);
            deviceQueryCondition.setGroupId(deviceGroup.getId());
            deviceGroupInfo.setReDevices(reDeviceGroupDeviceService.search(deviceQueryCondition));
            // 查询相关的用户信息
            ReDeviceGroupUserQueryCondition userQueryCondition = new ReDeviceGroupUserQueryCondition();
            userQueryCondition.setIsEnable(true);
            userQueryCondition.setGroupId(deviceGroup.getId());
            deviceGroupInfo.setReUsers(reDeviceGroupUserService.search(userQueryCondition));
            // 加入结果集中
            result.add(deviceGroupInfo);
        });
        return result;
    }

    /**
     * 根据设备的 IotId 和 所属分组的类型获取组成员
     *
     * @param iotId     iotId
     * @param groupType 分组类型
     * @return 对应组成员用户列表
     * @see DeviceGroupTypeConst
     */
    @Override
    public List<User> selectGroupUserByIotIdAndGroupType(String iotId, Integer groupType) {
        List<String> userIds = deviceGroupMapper.selectGroupUserIdByIotIdAndGroupType(iotId, groupType);
        // TODO: 组内某些成员可能不需要短信通知 如 系统主管理员 组与用户关系需要添加字段表明是否接受短信通知
        userIds.remove("040f2058c2785eb44d388e89e0340b2c8326");
        return userIds.stream().map(userId -> userFeignClient.select(userId).getData()).collect(Collectors.toList());
    }
}
