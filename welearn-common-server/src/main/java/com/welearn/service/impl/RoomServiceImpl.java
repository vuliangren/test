package com.welearn.service.impl;

import com.welearn.entity.po.common.Room;
import com.welearn.entity.qo.common.RoomQueryCondition;
import com.welearn.mapper.RoomMapper;
import com.welearn.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : RoomService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RoomServiceImpl extends BaseServiceImpl<Room,RoomQueryCondition,RoomMapper>
        implements RoomService{
    
    @Autowired
    private RoomMapper roomMapper;
    
    @Override
    RoomMapper getMapper() {
        return roomMapper;
    }

}
