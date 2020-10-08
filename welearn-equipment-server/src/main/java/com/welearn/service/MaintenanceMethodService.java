package com.welearn.service;

import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.MaintenanceMethod;
import com.welearn.entity.qo.equipment.MaintenanceMethodQueryCondition;
import com.welearn.entity.vo.response.equipment.MaintenanceRecordDetail;
import com.welearn.entity.vo.response.equipment.TaskAssignmentDetail;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : MaintenanceMethodService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface MaintenanceMethodService extends BaseService<MaintenanceMethod, MaintenanceMethodQueryCondition>{

    /**
     * 查找设备拥有的维护方式
     * @param equipmentId 设备id
     * @return 维护方式列表
     */
    List<MaintenanceMethod> selectByEquipmentId(String equipmentId, String companyId);

    /**
     * 查找产品拥有的维护方式
     * @param equipmentProductId 产品id
     * @return 维护方式列表
     */
    List<MaintenanceMethod> selectByEquipmentProductId(String equipmentProductId, String companyId);

    /**
     * 查找设备类型拥有的维护方式
     * @param equipmentTypeId 设备类型id
     * @return 维护方式列表
     */
    List<MaintenanceMethod> selectByEquipmentTypeId(String equipmentTypeId, String companyId);

    /**
     * 根据维护任务id查询其关联的维护方式
     * @param taskId 维护任务id
     * @return 维护方式列表
     */
    List<MaintenanceMethod> selectByTaskId(String taskId);

    /**
     * 根据维护任务分配id查询维护任务分配详情
     * @param assignmentId 维护任务分配id
     * @return 任务分配详情列表
     */
    List<TaskAssignmentDetail> selectTaskAssignmentDetail(String assignmentId);

    /**
     * 根据维护记录id查询维护记录详情
     * @param recordId 维护记录id
     * @return 维护记录详情列表
     */
    List<MaintenanceRecordDetail> selectMaintenanceRecordDetail(String recordId);
}