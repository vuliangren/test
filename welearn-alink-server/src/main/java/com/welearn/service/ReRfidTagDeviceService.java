package com.welearn.service;

import com.welearn.entity.po.alink.ReRfidTagDevice;
import com.welearn.entity.qo.alink.ReRfidTagDeviceQueryCondition;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ReRfidTagDeviceService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReRfidTagDeviceService extends BaseService<ReRfidTagDevice, ReRfidTagDeviceQueryCondition>{

    /**
     * 根据标签id 删除相关的设备关联
     * @param tagId 标签id
     */
    void deleteByTagId(@NotBlank String tagId);
}