package com.welearn.service.impl;

import com.welearn.dictionary.equipment.EquipmentMaintainStatusConst;
import com.welearn.dictionary.equipment.EquipmentPermissionCodeConst;
import com.welearn.dictionary.equipment.EquipmentPermissionTypeConst;
import com.welearn.entity.po.equipment.*;
import com.welearn.entity.qo.equipment.ReMaintenanceRecordMethodQueryCondition;
import com.welearn.entity.qo.equipment.ReTaskAssignmentMethodQueryCondition;
import com.welearn.entity.qo.equipment.TaskAssignmentQueryCondition;
import com.welearn.entity.vo.response.common.LocationInfo;
import com.welearn.entity.vo.response.equipment.TaskAssignmentInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.ReMaintenanceRecordMethodMapper;
import com.welearn.mapper.ReTaskAssignmentMethodMapper;
import com.welearn.mapper.TaskAssignmentMapper;
import com.welearn.service.*;
import com.welearn.util.PaginateUtil;
import com.welearn.util.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.equipment.MaintenanceTaskAssignmentStatusConst.*;

/**
 * Description : TaskAssignmentService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class TaskAssignmentServiceImpl extends BaseServiceImpl<TaskAssignment,TaskAssignmentQueryCondition,TaskAssignmentMapper>
        implements TaskAssignmentService{
    
    @Autowired
    private TaskAssignmentMapper taskAssignmentMapper;

    @Autowired
    private EquipmentPermissionService equipmentPermissionService;

    @Autowired
    private ReTaskAssignmentMethodMapper reTaskAssignmentMethodMapper;

    @Autowired
    private ReMaintenanceRecordMethodMapper reMaintenanceRecordMethodMapper;

    @Autowired
    private MaintenanceRecordService maintenanceRecordService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EngineerService engineerService;

    @Override
    TaskAssignmentMapper getMapper() {
        return taskAssignmentMapper;
    }

    /**
     * 根据条件查询任务分配详情
     *
     * @param condition 查询条件
     * @return 任务分配详情列表
     */
    @Override
    public List<TaskAssignmentInfo> searchInfo(TaskAssignmentQueryCondition condition) {
        return PaginateUtil.page(()->taskAssignmentMapper.searchInfo(condition));
    }

    /**
     * 根据条件构建一个维护任务分配 及 与维护方式的关联数据
     * 由 MaintenanceTaskServiceImpl.assignment 方法调用 生成维护任务分配
     * @see MaintenanceTaskServiceImpl
     * @param task 维护任务
     * @param methods 维护方式列表
     * @param equipment 设备
     * @param reMaintenanceTaskUser 维护工程师
     */
    @Override
    @Transactional
    public void createTaskAssignment(MaintenanceTask task, List<MaintenanceMethod> methods,
                                     Equipment equipment, ReMaintenanceTaskUser reMaintenanceTaskUser){
        Engineer engineer = engineerService.select(reMaintenanceTaskUser.getEngineerId());
        // 先检查现有未完成(未领取 未处理 处理中)的维护任务分配
        TaskAssignmentQueryCondition condition = new TaskAssignmentQueryCondition();
        condition.setIsEnable(true);
        condition.setTaskId(task.getId());
        condition.setCompanyId(task.getCompanyId());
        condition.setEquipmentId(equipment.getId());
        condition.setStatusLessThan(PROCESSED.ordinal());
        List<TaskAssignment> assignments = taskAssignmentMapper.selectByCondition(condition);
        // 如果有未完成记录 则调整任务优先级
        boolean isCreate = true;
        if (Objects.nonNull(assignments) && !assignments.isEmpty()){
            for (TaskAssignment assignment : assignments) {
                // 若当前工程师不存在未完成记录, 也创建
                if (assignment.getEngineerId().equals(engineer.getId()))
                    isCreate = false;
                assignment.setPriority(assignment.getPriority() + task.getPriority());
                taskAssignmentMapper.updateByPK(assignment);
                log.info("维护任务自动分配 - 任务分配:{} 优先级提高", assignment.getId());
            }
        }
        // 创建任务分配
        if (Objects.isNull(assignments) || assignments.isEmpty() || isCreate){
            // 检查设备维护权限
            Boolean verifyPermission = equipmentPermissionService.verify(EquipmentPermissionTypeConst.EMPLOYEE.ordinal(),
                    EquipmentPermissionCodeConst.MAINTAIN.ordinal(), equipment.getId(), engineer.getUserId());
            if (!verifyPermission){
                EquipmentPermission permission = new EquipmentPermission();
                permission.setType(EquipmentPermissionTypeConst.EMPLOYEE.ordinal());
                permission.setPermission(EquipmentPermissionCodeConst.MAINTAIN.ordinal());
                permission.setEquipmentId(equipment.getId());
                permission.setObtainAt(new Date());
                permission.setObtainReason("被分配维护此设备");
                permission.setCompanyId(engineer.getUserCompanyId());
                permission.setCompanyName(engineer.getUserCompanyName());
                permission.setDepartmentId(engineer.getUserDepartmentId());
                permission.setDepartmentName(engineer.getUserDepartmentName());
                permission.setEmployeeId(engineer.getUserId());
                permission.setEmployeeName(engineer.getUserName());
                equipmentPermissionService.create(permission);
            }
            // 添加任务分配
            TaskAssignment taskAssignment = new TaskAssignment();
            taskAssignment.setPriority(task.getPriority());
            taskAssignment.setCompanyId(task.getCompanyId());
            taskAssignment.setEquipmentId(equipment.getId());
            taskAssignment.setTaskId(task.getId());
            taskAssignment.setEngineerId(engineer.getId());
            taskAssignment.setEngineerName(engineer.getUserName());
            taskAssignment.setEquipmentDescription(equipment.description());
            LocationInfo locationInfo = equipmentService.locationInfo(equipment);
            taskAssignment.setEquipmentPosition(Objects.nonNull(locationInfo)? locationInfo.getOutlook() : "-");
            taskAssignment.setRemark(String.format("根据定时任务:%s 于 %s 创建", task.getName(),
                    new DateTime(task.getStartAt()).toString("yyyy-MM-dd HH:mm:ss")));
            taskAssignment.setStatus(UN_RECEIVE.ordinal());
            this.create(taskAssignment);
            log.info("维护任务自动分配 - 创建新的任务分配:", taskAssignment.getId());
            // 添加任务分配详情
            for (MaintenanceMethod method : methods) {
                ReTaskAssignmentMethod re = new ReTaskAssignmentMethod();
                re.setId(UUIDGenerator.get(ReTaskAssignmentMethod.class));
                re.setIsProcessed(false);
                re.setMethodId(method.getId());
                re.setTaskAssignmentId(taskAssignment.getId());
                reTaskAssignmentMethodMapper.insertSelective(re);
                log.info("维护任务自动分配 - 创建新的任务分配详情:", re.getId());
            }
        }
    }

    /**
     * 领取任务分配
     * 对于单独的任务分配 更改状态 为待处理
     * 对于抢单的任务分配 取消其它任务分配
     *
     * @param taskAssignmentId 任务分配id
     */
    @Override @Transactional
    public void receive(String taskAssignmentId) {
        // 查询当前任务分配
        TaskAssignment assignment = this.select(taskAssignmentId);
        if (Objects.isNull(assignment) || assignment.getStatus() != UN_RECEIVE.ordinal())
            throw new BusinessVerifyFailedException("taskAssignmentId 非法");
        // 查询该任务所有的分配
        TaskAssignmentQueryCondition condition = new TaskAssignmentQueryCondition();
        condition.setIsEnable(true);
        condition.setTaskId(assignment.getTaskId());
        condition.setEquipmentId(assignment.getEquipmentId());
        condition.setStatus(UN_RECEIVE.ordinal());
        List<TaskAssignment> search = this.search(condition);
        if (Objects.nonNull(search) && search.size() > 1){
            search.stream().filter(taskAssignment -> !taskAssignment.getId().equals(assignment.getId()))
                    // 对 其余的任务分配进行取消 操作
                    .forEach(taskAssignment -> this.cancelTaskAssignment(taskAssignment, String.format("该任务已由工程师 %s 抢先领取", assignment.getEngineerName())));
        }
        // 更新当前任务分配为待处理
        assignment.setStatus(UN_PROCESS.ordinal());
        this.update(assignment);
    }

    /**
     * 处理任务分配
     * 创建维护记录 及维护记录详情, 更改任务分配状态为 处理中
     *
     * @param taskAssignmentId 任务分配id
     */
    @Override
    public MaintenanceRecord process(String taskAssignmentId) {
        // 查询当前任务分配
        TaskAssignment assignment = this.select(taskAssignmentId);
        if (Objects.isNull(assignment) || assignment.getStatus() != UN_PROCESS.ordinal())
            throw new BusinessVerifyFailedException("taskAssignmentId 非法");
        // 创建维护记录 及 其详情
        MaintenanceRecord record = maintenanceRecordService.createMaintenanceRecord(assignment);
        // 更新当前任务分配状态 为 处理中
        assignment.setStatus(PROCESSING.ordinal());
        assignment.setRecordId(record.getId());
        this.update(assignment);
        // 修改设备状态
        Equipment equipment = new Equipment();
        equipment.setId(assignment.getEquipmentId());
        equipment.setMaintainStatus(EquipmentMaintainStatusConst.UN_FINISH.ordinal());
        equipmentService.update(equipment);
        return record;
    }

    /**
     * 取消任务分配
     */
    @Override
    public void cancel(String taskAssignmentId, String reason) {
        TaskAssignment taskAssignment = this.select(taskAssignmentId);
        if (Objects.isNull(taskAssignment))
            throw new BusinessVerifyFailedException("taskAssignmentId 非法");
        this.cancelTaskAssignment(taskAssignment, reason);
    }

    /**
     * 取消该任务分配及其详情信息
     * @param taskAssignment 任务分配
     * @param reason 任务分配取消原因
     */
    @Transactional
    public void cancelTaskAssignment(TaskAssignment taskAssignment, String reason){
        if (taskAssignment.getStatus() >= PROCESSED.ordinal())
            throw new BusinessVerifyFailedException("taskAssignment 当前状态无法取消");
        int finishCount = 0;
        // 更改备注
        String remark = taskAssignment.getRemark();
        if (StringUtils.isBlank(remark))
            remark = reason;
        else
            remark += '\n' + reason;
        taskAssignment.setRemark(remark);
        // 禁用分配详情 对 未处理且未禁用的 进行 禁用, 对处理过的不进行禁用
        ReTaskAssignmentMethodQueryCondition condition = new ReTaskAssignmentMethodQueryCondition();
        condition.setEquipmentId(taskAssignment.getEquipmentId());
        condition.setTaskAssignmentId(taskAssignment.getId());
        condition.setIsEnable(true);
        List<ReTaskAssignmentMethod> reTaskAssignmentMethods = reTaskAssignmentMethodMapper.selectByCondition(condition);
        for (ReTaskAssignmentMethod reTaskAssignmentMethod : reTaskAssignmentMethods) {
            if (reTaskAssignmentMethod.getIsProcessed())
                finishCount ++;
            else
                reTaskAssignmentMethodMapper.disable(reTaskAssignmentMethod.getId());
        }
        // 禁用未完成的维护方式数据记录
        if (StringUtils.isNotBlank(taskAssignment.getRecordId())){
            ReMaintenanceRecordMethodQueryCondition recordQuery = new ReMaintenanceRecordMethodQueryCondition();
            recordQuery.setIsEnable(true);
            recordQuery.setEquipmentId(taskAssignment.getEquipmentId());
            recordQuery.setRecordId(taskAssignment.getRecordId());
            List<ReMaintenanceRecordMethod> reMaintenanceRecordMethods = reMaintenanceRecordMethodMapper.selectByCondition(recordQuery);
            for (ReMaintenanceRecordMethod reMaintenanceRecordMethod : reMaintenanceRecordMethods) {
                // 对无 dataJson 数据 的数据记录 进行 禁用
                if (StringUtils.isBlank(reMaintenanceRecordMethod.getDataJson())){
                    reMaintenanceRecordMethodMapper.disable(reMaintenanceRecordMethod.getId());
                }
            }
        }
        // 未接单 或 未处理状态的 维护任务分配 直接取消, 处理中但是完成数量为 0 的 也直接取消
        if (taskAssignment.getStatus() <= UN_PROCESS.ordinal() || finishCount == 0)
            taskAssignment.setStatus(CANCELED.ordinal());
        // 其余情况下 设置状态为部分完成
        else
            taskAssignment.setStatus(PARTLY_PROCESSED.ordinal());
        this.update(taskAssignment);
    }
}
