package com.welearn.service;

import com.welearn.entity.po.equipment.MaintenanceRecord;
import com.welearn.entity.po.equipment.ReMaintenanceRecordMethod;
import com.welearn.entity.po.equipment.TaskAssignment;
import com.welearn.entity.qo.equipment.MaintenanceRecordQueryCondition;
import com.welearn.entity.vo.response.equipment.MaintenanceRecordInfo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : MaintenanceRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface MaintenanceRecordService extends BaseService<MaintenanceRecord, MaintenanceRecordQueryCondition>{

    /**
     * 根据维护记录获取其详细信息
     * @param recordId 维护记录id
     * @return 维护记录详细信息, 维护方法等
     */
    MaintenanceRecordInfo selectInfo(String recordId);


    /**
     * 内部使用
     * 根据维护任务创建维护记录及详情信息
     * @param taskAssignment 维护任务
     * @return 维护任务
     */
    MaintenanceRecord createMaintenanceRecord(TaskAssignment taskAssignment);

    /**
     * 根据设备的维护方式 创建维护记录
     * @param equipmentId 设备id
     * @param engineerId 工程师id
     * @param remark 备注
     * @param methodIds 维护方式id 列表
     * @return 维护记录
     */
    MaintenanceRecord createFromMethodIds(String equipmentId, String engineerId, String remark, List<String> methodIds);

    /**
     * 保存维护方式的数据记录
     * @param id ReMaintenanceRecordMethodId
     * @param dataJson 数据记录 JSON 格式
     */
    ReMaintenanceRecordMethod saveMethodRecord(String id, String dataJson);

    /**
     * 完成维护方式的数据记录
     * @param id ReMaintenanceRecordMethodId
     * @param dataJson 数据记录 JSON 格式
     */
    ReMaintenanceRecordMethod finishMethodRecord(String id, String dataJson);
}