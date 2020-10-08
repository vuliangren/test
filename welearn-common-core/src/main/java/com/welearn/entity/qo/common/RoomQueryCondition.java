package com.welearn.entity.qo.common;

import com.welearn.entity.po.common.Room;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;

/**
 * Description : Room Query Condition
 * Created by Setsuna Jin.
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/16 18:24:38
 * @see com.welearn.entity.po.common.Room
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "RoomQueryCondition", description = "Room 查询条件")
public class RoomQueryCondition extends Room {

}
