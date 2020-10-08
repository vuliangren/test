package com.welearn.service;

import com.welearn.entity.po.equipment.MaintenanceTask;
import com.welearn.entity.qo.equipment.MaintenanceTaskQueryCondition;
import com.welearn.entity.vo.response.equipment.MaintenanceTaskInfo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : MaintenanceTaskService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface MaintenanceTaskService extends BaseService<MaintenanceTask, MaintenanceTaskQueryCondition>{

    /**
     * 查询维护方式详情
     * @param condition 查询条件
     * @return 维护方式详情
     */
    List<MaintenanceTaskInfo> searchInfo(MaintenanceTaskQueryCondition condition);

}