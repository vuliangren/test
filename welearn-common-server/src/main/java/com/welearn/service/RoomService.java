package com.welearn.service;

import com.welearn.entity.po.common.Room;
import com.welearn.entity.qo.common.RoomQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : RoomService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RoomService extends BaseService<Room, RoomQueryCondition>{

}