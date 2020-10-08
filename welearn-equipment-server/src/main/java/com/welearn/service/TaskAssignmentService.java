package com.welearn.service;

import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.*;
import com.welearn.entity.qo.equipment.TaskAssignmentQueryCondition;
import com.welearn.entity.vo.response.equipment.TaskAssignmentInfo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : TaskAssignmentService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface TaskAssignmentService extends BaseService<TaskAssignment, TaskAssignmentQueryCondition>{
    /**
     * 根据条件查询任务分配详情
     * @param condition 查询条件
     * @return 任务分配详情列表
     */
    List<TaskAssignmentInfo> searchInfo(TaskAssignmentQueryCondition condition);

    /**
     * 根据条件构建一个维护任务分配 及 与维护方式的关联数据
     * @param task 维护任务
     * @param methods 维护方式列表
     * @param equipment 设备
     * @param engineer 维护工程师
     */
    void createTaskAssignment(MaintenanceTask task, List<MaintenanceMethod> methods,
                              Equipment equipment, ReMaintenanceTaskUser engineer);

    /**
     * 领取任务分配
     * 对于单独的任务分配 更改状态 为待处理
     * 对于抢单的任务分配 取消其它任务分配
     */
    void receive(String taskAssignmentId);

    /**
     * 处理任务分配
     * 创建维护记录 及维护记录详情, 更改任务分配状态为 处理中
     */
    MaintenanceRecord process(String taskAssignmentId);

    /**
     * 取消任务分配
     */
    void cancel(String taskAssignmentId, String reason);
}