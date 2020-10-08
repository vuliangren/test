package com.welearn.service;

import com.welearn.entity.po.alink.ReDeviceGroupUser;
import com.welearn.entity.qo.alink.ReDeviceGroupUserQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : ReDeviceGroupUserService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReDeviceGroupUserService extends BaseService<ReDeviceGroupUser, ReDeviceGroupUserQueryCondition>{
    /**
     * 删除相关的全部关联并重新创建
     * @param reDeviceGroupUsers 新建的关联列表
     */
    List<ReDeviceGroupUser> allDeleteOldAndCreate(@NotBlank String groupId, @NotNull List<ReDeviceGroupUser> reDeviceGroupUsers);
}