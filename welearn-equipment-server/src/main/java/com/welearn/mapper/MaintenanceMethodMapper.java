package com.welearn.mapper;

import com.welearn.entity.po.equipment.MaintenanceMethod;
import com.welearn.entity.qo.equipment.MaintenanceMethodQueryCondition;
import com.welearn.entity.vo.response.equipment.MaintenanceRecordDetail;
import com.welearn.entity.vo.response.equipment.TaskAssignmentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MaintenanceMethod Mapper Interface : ryme_equipment : maintenance_method
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:39
 * @see com.welearn.entity.po.equipment.MaintenanceMethod
 */
@Mapper 
public interface MaintenanceMethodMapper extends BaseMapper<MaintenanceMethod, MaintenanceMethodQueryCondition> {

    /**
     * 根据维护任务id查询其关联的维护方式
     * @param taskId 维护任务id
     * @return 维护方式列表
     */
    List<MaintenanceMethod> selectByTaskId(@Param("taskId") String taskId);

    /**
     * 根据维护任务分配id查询维护任务分配详情
     * @param assignmentId 维护任务分配id
     * @return 任务分配详情列表
     */
    List<TaskAssignmentDetail> selectByAssignmentId(@Param("assignmentId") String assignmentId);

    /**
     * 根据维护记录id查询维护记录详情
     * @param recordId 维护记录id
     * @return 维护记录详情列表
     */
    List<MaintenanceRecordDetail> selectByRecordId(@Param("recordId") String recordId);

    /**
     * 根据公司id查询数量, 数据统计分析时使用
     * @param companyId 公司id
     * @return 维护方式数量
     */
    Integer countByCompanyId(@Param("companyId") String companyId);

}