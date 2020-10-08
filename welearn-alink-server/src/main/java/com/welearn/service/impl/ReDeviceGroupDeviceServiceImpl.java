package com.welearn.service.impl;

import com.welearn.entity.po.alink.ReDeviceGroupDevice;
import com.welearn.entity.qo.alink.ReDeviceGroupDeviceQueryCondition;
import com.welearn.mapper.ReDeviceGroupDeviceMapper;
import com.welearn.service.ReDeviceGroupDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : ReDeviceGroupDeviceService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReDeviceGroupDeviceServiceImpl extends BaseServiceImpl<ReDeviceGroupDevice,ReDeviceGroupDeviceQueryCondition,ReDeviceGroupDeviceMapper>
        implements ReDeviceGroupDeviceService{
    
    @Autowired
    private ReDeviceGroupDeviceMapper reDeviceGroupDeviceMapper;
    
    @Override
    ReDeviceGroupDeviceMapper getMapper() {
        return reDeviceGroupDeviceMapper;
    }

    /**
     * 删除相关的全部关联并重新创建
     *
     * @param groupId 组id
     * @param reDeviceGroupDevices 新建的关联列表
     */
    @Override
    public List<ReDeviceGroupDevice> allDeleteOldAndCreate(String groupId, List<ReDeviceGroupDevice> reDeviceGroupDevices) {
        reDeviceGroupDeviceMapper.deleteByGroupId(groupId);
        for (ReDeviceGroupDevice reDeviceGroupDevice : reDeviceGroupDevices) {
            this.create(reDeviceGroupDevice);
        }
        return reDeviceGroupDevices;
    }
}
