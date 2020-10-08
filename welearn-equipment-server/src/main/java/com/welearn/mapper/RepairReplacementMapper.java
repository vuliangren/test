package com.welearn.mapper;

import com.welearn.entity.po.equipment.RepairReplacement;
import com.welearn.entity.qo.equipment.RepairReplacementQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RepairReplacement Mapper Interface : ryme_equipment : repair_replacement
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:14:02
 * @see com.welearn.entity.po.equipment.RepairReplacement
 */
@Mapper 
public interface RepairReplacementMapper extends BaseMapper<RepairReplacement, RepairReplacementQueryCondition> {

}