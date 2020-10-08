package com.welearn.mapper;

import com.welearn.entity.po.equipment.RepairDispatchOutside;
import com.welearn.entity.qo.equipment.RepairDispatchOutsideQueryCondition;
import com.welearn.entity.vo.response.equipment.RepairDispatchOutsideInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RepairDispatchOutside Mapper Interface : ryme_equipment : repair_dispatch_outside
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:13:59
 * @see com.welearn.entity.po.equipment.RepairDispatchOutside
 */
@Mapper 
public interface RepairDispatchOutsideMapper extends BaseMapper<RepairDispatchOutside, RepairDispatchOutsideQueryCondition> {

}