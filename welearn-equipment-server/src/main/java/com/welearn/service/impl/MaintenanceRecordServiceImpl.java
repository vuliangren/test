package com.welearn.service.impl;

import com.welearn.dictionary.equipment.EquipmentMaintainStatusConst;
import com.welearn.dictionary.equipment.EquipmentRepairStatusConst;
import com.welearn.entity.po.equipment.*;
import com.welearn.entity.qo.equipment.*;
import com.welearn.entity.vo.response.common.LocationInfo;
import com.welearn.entity.vo.response.equipment.MaintenanceRecordInfo;
import com.welearn.entity.vo.response.equipment.TaskAssignmentInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.*;
import com.welearn.service.EquipmentPermissionService;
import com.welearn.service.EquipmentService;
import com.welearn.service.MaintenanceRecordService;
import com.welearn.util.UUIDGenerator;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.welearn.dictionary.equipment.MaintenanceTaskAssignmentStatusConst.*;

/**
 * Description : MaintenanceRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class MaintenanceRecordServiceImpl extends BaseServiceImpl<MaintenanceRecord,MaintenanceRecordQueryCondition,MaintenanceRecordMapper>
        implements MaintenanceRecordService{
    
    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;

    @Autowired
    private MaintenanceMethodMapper maintenanceMethodMapper;

    @Autowired
    private ReMaintenanceRecordMethodMapper reMaintenanceRecordMethodMapper;

    @Autowired
    private ReTaskAssignmentMethodMapper reTaskAssignmentMethodMapper;

    @Autowired
    private TaskAssignmentMapper taskAssignmentMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private EngineerMapper engineerMapper;

    @Autowired
    private EquipmentPermissionService equipmentPermissionService;

    @Autowired
    private EquipmentService equipmentService;

    @Override
    MaintenanceRecordMapper getMapper() {
        return maintenanceRecordMapper;
    }

    /**
     * 根据维护记录获取其详细信息
     *
     * @param recordId 维护记录id
     * @return 维护记录详细信息, 维护方法等
     */
    @Override
    public MaintenanceRecordInfo selectInfo(String recordId) {
        MaintenanceRecordInfo maintenanceRecordInfo = maintenanceRecordMapper.selectEnabledInfoById(recordId);
        if (Objects.isNull(maintenanceRecordInfo))
            throw new BusinessVerifyFailedException("recordId 非法");
        // 查询维护任务内容
        if (StringUtils.isNotBlank(maintenanceRecordInfo.getTaskId())){
            TaskAssignmentQueryCondition taskAssignmentQuery = new TaskAssignmentQueryCondition();
            taskAssignmentQuery.setIsEnable(true);
            taskAssignmentQuery.setRecordId(maintenanceRecordInfo.getId());
            List<TaskAssignmentInfo> taskAssignmentInfos = taskAssignmentMapper.searchInfo(taskAssignmentQuery);
            if (Objects.nonNull(taskAssignmentInfos) && !taskAssignmentInfos.isEmpty()){
                maintenanceRecordInfo.setTaskAssignmentInfo(taskAssignmentInfos.get(0));
                ReTaskAssignmentMethodQueryCondition reTaskAssignmentMethodQuery = new ReTaskAssignmentMethodQueryCondition();
                reTaskAssignmentMethodQuery.setIsEnable(true);
                reTaskAssignmentMethodQuery.setTaskAssignmentId(maintenanceRecordInfo.getTaskAssignmentInfo().getId());
                maintenanceRecordInfo.setTaskAssignmentDetails(reTaskAssignmentMethodMapper.selectByCondition(reTaskAssignmentMethodQuery));
            }
        }
        // 查询维护记录详情
        maintenanceRecordInfo.setMaintenanceRecordDetails(maintenanceMethodMapper.selectByRecordId(recordId));
        // 添加设备信息
        maintenanceRecordInfo.setEquipment(equipmentMapper.selectByPK(maintenanceRecordInfo.getEquipmentId()));
        return maintenanceRecordInfo;
    }

    @Override @Transactional
    public MaintenanceRecord createMaintenanceRecord(TaskAssignment taskAssignment){
        // 创建维护任务
        MaintenanceRecord record = new MaintenanceRecord();
        record.setCompanyId(taskAssignment.getCompanyId());
        record.setEngineerId(taskAssignment.getEngineerId());
        record.setEngineerName(taskAssignment.getEngineerName());
        record.setEquipmentDescription(taskAssignment.getEquipmentDescription());
        record.setEquipmentPosition(taskAssignment.getEquipmentPosition());
        record.setRemark(String.format("工程师 %s 于 %s 进行维护", taskAssignment.getEngineerName(),
                new DateTime().toString("yyyy-MM-dd HH:mm:ss")));
        record.setTaskId(taskAssignment.getTaskId());
        record.setEquipmentId(taskAssignment.getEquipmentId());
        this.create(record);
        // 查询维护任务分配关联的维护方式信息
        ReTaskAssignmentMethodQueryCondition methodQuery = new ReTaskAssignmentMethodQueryCondition();
        methodQuery.setIsEnable(true);
        methodQuery.setTaskAssignmentId(taskAssignment.getId());
        methodQuery.setIsProcessed(false);
        List<ReTaskAssignmentMethod> reTaskAssignmentMethods = reTaskAssignmentMethodMapper.selectByCondition(methodQuery);
        for (ReTaskAssignmentMethod reTaskAssignmentMethod : reTaskAssignmentMethods) {
            // 创建维护记录与维护方式的关联数据
            ReMaintenanceRecordMethod recordMethod = new ReMaintenanceRecordMethod();
            recordMethod.setId(UUIDGenerator.get(ReMaintenanceRecordMethod.class));
            recordMethod.setEquipmentId(taskAssignment.getEquipmentId());
            recordMethod.setMethodId(reTaskAssignmentMethod.getMethodId());
            recordMethod.setRecordId(record.getId());
            reMaintenanceRecordMethodMapper.insertSelective(recordMethod);
        }
        return record;
    }

    /**
     * 根据设备的维护方式 创建维护记录
     *
     * @param equipmentId 设备id
     * @param engineerId  工程师id
     * @param remark      备注
     * @param methodIds   维护方式id 列表
     * @return 维护记录
     */
    @Override @Transactional
    public MaintenanceRecord createFromMethodIds(String equipmentId, String engineerId, String remark, List<String> methodIds) {
        Equipment equipment = equipmentMapper.selectByPK(equipmentId);
        if (Objects.isNull(equipment))
            throw new BusinessVerifyFailedException("equipmentId 非法");
        Engineer engineer = engineerMapper.selectByPK(engineerId);
        if (Objects.isNull(engineer))
            throw new BusinessVerifyFailedException("engineerId 非法");
        // 创建维护记录
        MaintenanceRecord record = new MaintenanceRecord();
        record.setEquipmentId(equipmentId);
        record.setEngineerId(engineerId);
        record.setRemark(remark);
        record.setEquipmentDescription(equipment.description());
        LocationInfo locationInfo = equipmentService.locationInfo(equipment);
        record.setEquipmentPosition(Objects.nonNull(locationInfo) ? locationInfo.getOutlook(): "-");
        record.setEngineerName(engineer.getUserName());
        record.setCompanyId(engineer.getServeCompanyId());
        this.create(record);
        // 创建维护数据记录
        Set<String> methodIdsSet = new HashSet<>(methodIds);
        for (String methodId : methodIdsSet) {
            ReMaintenanceRecordMethod recordMethod = new ReMaintenanceRecordMethod();
            recordMethod.setId(UUIDGenerator.get(ReMaintenanceRecordMethod.class));
            recordMethod.setDataJson(null);
            recordMethod.setRecordId(record.getId());
            recordMethod.setMethodId(methodId);
            recordMethod.setEquipmentId(equipmentId);
            reMaintenanceRecordMethodMapper.insertSelective(recordMethod);
        }
        return record;
    }

    /**
     * 保存维护方式的数据记录
     *
     * @param id       ReMaintenanceRecordMethodId
     * @param dataJson 数据记录 JSON 格式
     */
    @Override
    public ReMaintenanceRecordMethod saveMethodRecord(String id, String dataJson) {
        ReMaintenanceRecordMethod reMaintenanceRecordMethod = reMaintenanceRecordMethodMapper.selectByPK(id);
        if (Objects.isNull(reMaintenanceRecordMethod))
            throw new BusinessVerifyFailedException("id 非法");
        reMaintenanceRecordMethod.setDataJson(dataJson);
        reMaintenanceRecordMethodMapper.updateByPK(reMaintenanceRecordMethod);
        return reMaintenanceRecordMethod;
    }

    /**
     * 完成维护方式的数据记录
     *
     * @param id       ReMaintenanceRecordMethodId
     * @param dataJson 数据记录 JSON 格式
     */
    @Override @Transactional
    public ReMaintenanceRecordMethod finishMethodRecord(String id, String dataJson) {
        // 保存数据
        ReMaintenanceRecordMethod methodRecord = reMaintenanceRecordMethodMapper.selectByPK(id);
        if (Objects.isNull(methodRecord))
            throw new BusinessVerifyFailedException("id 非法");
        MaintenanceRecord maintenanceRecord = maintenanceRecordMapper.selectByPK(methodRecord.getRecordId());
        if (Objects.isNull(maintenanceRecord))
            throw new BusinessVerifyFailedException("recordId 非法");
        // 检查是否关联任务, 如关联任务分配 判断任务分配状态是否合法
        TaskAssignmentQueryCondition assignmentQuery = new TaskAssignmentQueryCondition();
        assignmentQuery.setEquipmentId(methodRecord.getEquipmentId());
        assignmentQuery.setRecordId(methodRecord.getRecordId());
        assignmentQuery.setIsEnable(true);
        List<TaskAssignment> taskAssignments = taskAssignmentMapper.selectByCondition(assignmentQuery);
        // 如果关联了任务分配
        if (Objects.nonNull(taskAssignments) && taskAssignments.size() >= 1){
            if (taskAssignments.size() > 1)
                throw new BusinessVerifyFailedException("维护数据记录关联的任务分配信息 数量 异常");
            // 检验维护任务分配状态是否符合
            TaskAssignment taskAssignment = taskAssignments.get(0);
            if (taskAssignment.getStatus() != PROCESSING.ordinal())
                throw new BusinessVerifyFailedException("维护数据记录关联的任务分配信息 状态 非法");
            // 检查 维护任务分配详情 并确定 是否需要更新 任务分配状态
            ReTaskAssignmentMethodQueryCondition methodQuery = new ReTaskAssignmentMethodQueryCondition();
            methodQuery.setIsEnable(true);
            methodQuery.setTaskAssignmentId(taskAssignment.getId());
            List<ReTaskAssignmentMethod> assignmentMethods = reTaskAssignmentMethodMapper.selectByCondition(methodQuery);
            boolean isAllProcessed = true;
            for (ReTaskAssignmentMethod assignmentMethod : assignmentMethods) {
                if (assignmentMethod.getMethodId().equals(methodRecord.getMethodId())){
                    assignmentMethod.setIsProcessed(true);
                    reTaskAssignmentMethodMapper.updateByPK(assignmentMethod);
                }
                if (!assignmentMethod.getIsProcessed())
                    isAllProcessed = false;
            }
            // 如果全部 可用的 都处理了 则更新状态为 已处理
            if (isAllProcessed){
                taskAssignment.setStatus(PROCESSED.ordinal());
                taskAssignmentMapper.updateByPK(taskAssignment);
                // 修改设备状态
                Equipment equipment = new Equipment();
                equipment.setId(taskAssignment.getEquipmentId());
                equipment.setMaintainStatus(EquipmentMaintainStatusConst.OK.ordinal());
                equipmentService.update(equipment);
            }
        }
        // 保存维护数据
        methodRecord.setDataJson(dataJson);
        reMaintenanceRecordMethodMapper.updateByPK(methodRecord);
        // 查询相关的 未处理的维护任务分配 信息
        ReTaskAssignmentMethodQueryCondition condition = new ReTaskAssignmentMethodQueryCondition();
        condition.setIsEnable(true);
        condition.setEquipmentId(methodRecord.getEquipmentId());
        condition.setMethodId(methodRecord.getMethodId());
        condition.setIsProcessed(false);
        List<ReTaskAssignmentMethod> methodRecords = reTaskAssignmentMethodMapper.selectByCondition(condition);
        if (Objects.nonNull(methodRecords) && !methodRecords.isEmpty()){
            Set<String> taskAssignmentIds = new HashSet<>();
            String reason = String.format("工程师: %s 于 %s 对该设备进行的维护操作, 影响了该任务, 部分操作已不需要再进行处理!",
                    maintenanceRecord.getEngineerName(), new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
            // 对其它 任务分配详情信息 进行禁用
            for (ReTaskAssignmentMethod record : methodRecords) {
                reTaskAssignmentMethodMapper.disable(record.getId());
                taskAssignmentIds.add(record.getTaskAssignmentId());
            }
            // 取 任务分配 id 的 Set 集合, 并查询其 任务分配详情信息
            for (String taskAssignmentId : taskAssignmentIds) {
                boolean isAllProcessed = true;
                ReTaskAssignmentMethodQueryCondition methodQuery = new ReTaskAssignmentMethodQueryCondition();
                methodQuery.setIsEnable(true);
                methodQuery.setTaskAssignmentId(taskAssignmentId);
                methodQuery.setIsProcessed(false);
                List<ReTaskAssignmentMethod> assignmentMethods = reTaskAssignmentMethodMapper.selectByCondition(methodQuery);
                if (Objects.nonNull(assignmentMethods) && !assignmentMethods.isEmpty())
                    isAllProcessed = false;
                // 如已全部处理过, 则查询任务分配 验证是否需要更改其状态
                TaskAssignment assignment = taskAssignmentMapper.selectByPK(taskAssignmentId);
                if (isAllProcessed){
                    if (assignment.getStatus() == PROCESSING.ordinal())
                        assignment.setStatus(PARTLY_PROCESSED.ordinal());
                }
                // 追加备注信息 并 更新
                String remark = assignment.getRemark();
                if (StringUtils.isBlank(remark))
                    remark = reason;
                else remark += '\n' + reason;
                assignment.setRemark(remark);
                taskAssignmentMapper.updateByPK(assignment);
            }
        }
        return methodRecord;
    }
}
