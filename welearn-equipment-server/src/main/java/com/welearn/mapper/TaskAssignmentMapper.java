package com.welearn.mapper;

import com.welearn.entity.dto.equipment.TaskAssignmentStatistic;
import com.welearn.entity.po.equipment.TaskAssignment;
import com.welearn.entity.qo.equipment.TaskAssignmentQueryCondition;
import com.welearn.entity.vo.response.equipment.TaskAssignmentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * TaskAssignment Mapper Interface : ryme_equipment : task_assignment
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:40
 * @see com.welearn.entity.po.equipment.TaskAssignment
 */
@Mapper 
public interface TaskAssignmentMapper extends BaseMapper<TaskAssignment, TaskAssignmentQueryCondition> {

    /**
     * 根据条件查询任务分配详情
     * @param condition 查询条件
     * @return 任务分配详情列表
     */
    List<TaskAssignmentInfo> searchInfo(TaskAssignmentQueryCondition condition);


    /**
     * 数据统计分析使用
     * @param companyId 公司id
     * @param startAt 查询开始时间
     * @return 统计用数据
     */
    List<TaskAssignmentStatistic> taskAssignmentStatistic(@Param("companyId") String companyId, @Param("startAt") Date startAt);
}