package com.welearn.mapper;

import com.welearn.entity.po.common.Floor;
import com.welearn.entity.qo.common.FloorQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * Floor Mapper Interface : ryme_common : floor
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/13 10:55:58
 * @see com.welearn.entity.po.common.Floor
 */
@Mapper 
public interface FloorMapper extends BaseMapper<Floor, FloorQueryCondition> {
    
}