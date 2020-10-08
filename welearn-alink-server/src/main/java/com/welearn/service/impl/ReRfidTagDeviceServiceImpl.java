package com.welearn.service.impl;

import com.welearn.entity.po.alink.ReRfidTagDevice;
import com.welearn.entity.qo.alink.ReRfidTagDeviceQueryCondition;
import com.welearn.mapper.ReRfidTagDeviceMapper;
import com.welearn.service.ReRfidTagDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReRfidTagDeviceService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReRfidTagDeviceServiceImpl extends BaseServiceImpl<ReRfidTagDevice,ReRfidTagDeviceQueryCondition,ReRfidTagDeviceMapper>
        implements ReRfidTagDeviceService{
    
    @Autowired
    private ReRfidTagDeviceMapper reRfidTagDeviceMapper;
    
    @Override
    ReRfidTagDeviceMapper getMapper() {
        return reRfidTagDeviceMapper;
    }

    /**
     * 根据标签id 删除相关的设备关联
     *
     * @param tagId 标签id
     */
    @Override
    public void deleteByTagId(String tagId) {
        reRfidTagDeviceMapper.deleteByTagId(tagId);
    }
}
