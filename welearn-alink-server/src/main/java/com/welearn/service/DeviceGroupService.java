package com.welearn.service;

import com.welearn.entity.po.alink.DeviceGroup;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.alink.DeviceGroupQueryCondition;
import com.welearn.entity.vo.response.alink.DeviceGroupInfo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : DeviceGroupService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface DeviceGroupService extends BaseService<DeviceGroup, DeviceGroupQueryCondition>{

    /**
     * 查询设备分组详情信息
     * @param condition 查询条件
     * @return 设备分组列表
     */
    List<DeviceGroupInfo> searchInfo(@NotNull DeviceGroupQueryCondition condition);

    /**
     * 根据设备的 IotId 和 所属分组的类型获取组成员
     * @param iotId iotId
     * @param groupType 分组类型
     * @see com.welearn.dictionary.alink.DeviceGroupTypeConst
     * @return 对应组成员用户列表
     */
    List<User> selectGroupUserByIotIdAndGroupType(String iotId, Integer groupType);
}