package com.welearn.mapper;

import com.welearn.entity.po.common.Building;
import com.welearn.entity.qo.common.BuildingQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * Building Mapper Interface : ryme_common : building
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/13 10:55:57
 * @see com.welearn.entity.po.common.Building
 */
@Mapper 
public interface BuildingMapper extends BaseMapper<Building, BuildingQueryCondition> {
    
}