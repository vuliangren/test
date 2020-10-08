package com.welearn.service.impl;

import com.welearn.entity.po.alink.ReRfidTagDevice;
import com.welearn.entity.po.alink.RfidTag;
import com.welearn.entity.qo.alink.RfidTagQueryCondition;
import com.welearn.mapper.ReRfidTagDeviceMapper;
import com.welearn.mapper.RfidTagMapper;
import com.welearn.service.ReRfidTagDeviceService;
import com.welearn.service.RfidTagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Description : RfidTagService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RfidTagServiceImpl extends BaseServiceImpl<RfidTag,RfidTagQueryCondition,RfidTagMapper>
        implements RfidTagService{
    
    @Autowired
    private RfidTagMapper rfidTagMapper;

    @Autowired
    private ReRfidTagDeviceService reRfidTagDeviceService;

    @Override
    RfidTagMapper getMapper() {
        return rfidTagMapper;
    }

    /**
     * 删除旧的关联, 并创建新关联
     *
     * @param tagId  标签id
     * @param iotIds 设备iotIds列表
     * @return 关联关系
     */
    @Transactional
    @Override
    public List<ReRfidTagDevice> deleteOldAndCreateNew(String tagId, List<String> iotIds) {
        List<ReRfidTagDevice> response = new ArrayList<>();
        reRfidTagDeviceService.deleteByTagId(tagId);
        for (String iotId : iotIds) {
            ReRfidTagDevice reRfidTagDevice = new ReRfidTagDevice(iotId, tagId);
            reRfidTagDeviceService.create(reRfidTagDevice);
            response.add(reRfidTagDevice);
        }
        return response;
    }
}
