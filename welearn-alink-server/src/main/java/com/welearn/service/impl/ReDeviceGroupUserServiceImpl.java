package com.welearn.service.impl;

import com.welearn.entity.po.alink.ReDeviceGroupUser;
import com.welearn.entity.qo.alink.ReDeviceGroupUserQueryCondition;
import com.welearn.mapper.ReDeviceGroupUserMapper;
import com.welearn.service.ReDeviceGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : ReDeviceGroupUserService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReDeviceGroupUserServiceImpl extends BaseServiceImpl<ReDeviceGroupUser,ReDeviceGroupUserQueryCondition,ReDeviceGroupUserMapper>
        implements ReDeviceGroupUserService{
    
    @Autowired
    private ReDeviceGroupUserMapper reDeviceGroupUserMapper;
    
    @Override
    ReDeviceGroupUserMapper getMapper() {
        return reDeviceGroupUserMapper;
    }

    /**
     * 删除相关的全部关联并重新创建
     *
     * @param groupId 组id
     * @param reDeviceGroupUsers 新建的关联列表
     */
    @Override
    public List<ReDeviceGroupUser> allDeleteOldAndCreate(String groupId, List<ReDeviceGroupUser> reDeviceGroupUsers) {
        reDeviceGroupUserMapper.deleteByGroupId(groupId);
        for (ReDeviceGroupUser reDeviceGroupDevice : reDeviceGroupUsers) {
            this.create(reDeviceGroupDevice);
        }
        return reDeviceGroupUsers;
    }
}
