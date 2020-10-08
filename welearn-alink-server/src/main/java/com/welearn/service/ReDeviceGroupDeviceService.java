package com.welearn.service;

import com.welearn.entity.po.alink.ReDeviceGroupDevice;
import com.welearn.entity.qo.alink.ReDeviceGroupDeviceQueryCondition;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : ReDeviceGroupDeviceService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReDeviceGroupDeviceService extends BaseService<ReDeviceGroupDevice, ReDeviceGroupDeviceQueryCondition>{

    /**
     * 删除相关的全部关联并重新创建
     * @param reDeviceGroupDevices 新建的关联列表
     */
    List<ReDeviceGroupDevice> allDeleteOldAndCreate(@NotBlank String groupId, @NotNull List<ReDeviceGroupDevice> reDeviceGroupDevices);
}