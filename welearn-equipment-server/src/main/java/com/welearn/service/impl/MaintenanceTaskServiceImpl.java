package com.welearn.service.impl;

import com.welearn.dictionary.equipment.MaintenanceServeTypeConst;
import com.welearn.dictionary.equipment.MaintenanceTaskAssignmentStatusConst;
import com.welearn.dictionary.equipment.ManagementLeverConst;
import com.welearn.entity.po.equipment.*;
import com.welearn.entity.qo.equipment.*;
import com.welearn.entity.vo.response.equipment.MaintenanceTaskInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.*;
import com.welearn.service.MaintenanceTaskService;
import com.welearn.service.TaskAssignmentService;
import com.welearn.util.PaginateUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Description : MaintenanceTaskService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class MaintenanceTaskServiceImpl extends BaseServiceImpl<MaintenanceTask,MaintenanceTaskQueryCondition,MaintenanceTaskMapper>
        implements MaintenanceTaskService{
    
    @Autowired
    private MaintenanceTaskMapper maintenanceTaskMapper;

    @Autowired
    private ReMaintenanceTaskUserMapper reMaintenanceTaskUserMapper;

    @Autowired
    private MaintenanceMethodMapper maintenanceMethodMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private TaskAssignmentService taskAssignmentService;

    @Override
    MaintenanceTaskMapper getMapper() {
        return maintenanceTaskMapper;
    }


    private List<ReMaintenanceTaskUser> selectByTaskId(String taskId){
        ReMaintenanceTaskUserQueryCondition taskUserQueryCondition = new ReMaintenanceTaskUserQueryCondition();
        taskUserQueryCondition.setIsEnable(true);
        taskUserQueryCondition.setTaskId(taskId);
        return reMaintenanceTaskUserMapper.selectByCondition(taskUserQueryCondition);
    }

    /**
     * 查询维护方式详情
     *
     * @param condition 查询条件
     * @return 维护方式详情
     */
    @Override
    public List<MaintenanceTaskInfo> searchInfo(MaintenanceTaskQueryCondition condition) {
        // 查询维护方式详情信息
        List<MaintenanceTask> maintenanceTasks = PaginateUtil.page(() -> maintenanceTaskMapper.selectByCondition(condition));
        // 查询相关的工程师信息
        List<MaintenanceTaskInfo> result = new ArrayList<>();
        for (MaintenanceTask maintenanceTask : maintenanceTasks) {
            result.add(new MaintenanceTaskInfo(maintenanceTask, this.selectByTaskId(maintenanceTask.getId())));
        }
        return result;
    }

    /**
     * 每隔10分钟时
     * 查询数据库在任务时间范围内的待分配维护任务 进行分配
     */
    @Scheduled(cron = "0 0/10 * * * *")
    public void assignment(){
        log.info("维护任务自动分配 - 开始启动");
        // 查询 前5分钟 至 后5分钟 符合条件的维护任务
        DateTime current = new DateTime();
        MaintenanceTaskQueryCondition taskQuery = new MaintenanceTaskQueryCondition();
        taskQuery.setIsEnable(true);
        taskQuery.setStartAtFrom(current.minusMinutes(5).toDate());
        taskQuery.setStartAtEnd(current.plusMinutes(5).toDate());
        List<MaintenanceTask> tasks = this.search(taskQuery);
        log.info("维护任务自动分配 - 共计 {} 个任务需分配", tasks.size());
        // 处理任务配发 和 维护任务更新
        for (MaintenanceTask task : tasks) {
            log.info("维护任务自动分配 - 执行任务:{} 的分配", task.getId());
            // 获取任务关联用户
            List<ReMaintenanceTaskUser> engineers = this.selectByTaskId(task.getId());
            // 查询任务关联维护方式
            List<MaintenanceMethod> methods = maintenanceMethodMapper.selectByTaskId(task.getId());
            // 查询任务关联设备
            EquipmentQueryCondition condition = new EquipmentQueryCondition();
            condition.setIsEnable(true);
            MaintenanceServeTypeConst serveType = MaintenanceServeTypeConst.values()[task.getServeType()];
            switch (serveType){
                case EQUIPMENT:
                    condition.setId(task.getServeId());
                    break;
                case EQUIPMENT_PRODUCT:
                    condition.setCompanyId(task.getCompanyId());
                    condition.setEquipmentProductId(task.getServeId());
                    break;
                case EQUIPMENT_TYPE:
                    condition.setCompanyId(task.getCompanyId());
                    condition.setEquipmentTypeId(task.getServeId());
                    break;
                case ALL:
                    condition.setCompanyId(task.getCompanyId());
                    break;
                default:
                    throw new BusinessVerifyFailedException("(%s)task.serveType 非法", task.getId());
            }
            List<Equipment> equipments = equipmentMapper.searchOutlook(condition);
            // 派发维护任务
            for (Equipment equipment : equipments) {
                for (ReMaintenanceTaskUser engineer : engineers) {
                    log.info("维护任务自动分配 - 任务:{} 设备:{} 工程师: {}", task.getId(), equipment.getId(), engineer.getId());
                    taskAssignmentService.createTaskAssignment(task, methods, equipment, engineer);
                }
            }
            // 更新维护任务信息
            if (task.getInterval() == 0){
                task.setIsEnable(false);
                log.info("维护任务自动分配 - 单次任务:{} 已禁用", task.getId());
            } else {
                DateTime startAt = new DateTime(task.getStartAt());
                task.setStartAt(startAt.plusMinutes(task.getInterval() * task.getTimeUnit()).toDate());
                log.info("维护任务自动分配 - 定时任务:{} 已更新 新:{} 旧:{}", task.getId(), task.getStartAt(), startAt);
            }
            this.update(task);
        }
        log.info("维护任务自动分配 - 全部完成");
    }

}
