package com.welearn.mapper;

import com.welearn.entity.po.equipment.RepairDispatchInside;
import com.welearn.entity.qo.equipment.RepairDispatchInsideQueryCondition;
import com.welearn.entity.vo.response.equipment.RepairDispatchInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * RepairDispatchInside Mapper Interface : ryme_equipment : repair_dispatch_inside
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/12 17:13:58
 * @see com.welearn.entity.po.equipment.RepairDispatchInside
 */
@Mapper 
public interface RepairDispatchInsideMapper extends BaseMapper<RepairDispatchInside, RepairDispatchInsideQueryCondition> {

    /**
     * 查询报修派工详情信息
     * @param queryCondition 查询条件
     * @return 报修派工详情列表
     */
    List<RepairDispatchInfo> searchInfoByCondition(RepairDispatchInsideQueryCondition queryCondition);

    /**
     * 维修内部派工 工程师统计数据查询
     * @param engineerId 工程师id
     * @param startAt 查询开始时间
     * @return 统计分析用数据
     */
    List<RepairDispatchInfo> RepairDispatchInfoStatistic(@Param("engineerId") String engineerId, @Param("startAt") Date startAt);
}