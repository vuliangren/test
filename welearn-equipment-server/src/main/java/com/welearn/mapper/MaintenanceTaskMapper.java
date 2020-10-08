package com.welearn.mapper;

import com.welearn.entity.po.equipment.MaintenanceTask;
import com.welearn.entity.qo.equipment.MaintenanceTaskQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MaintenanceTask Mapper Interface : ryme_equipment : maintenance_task
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:39
 * @see com.welearn.entity.po.equipment.MaintenanceTask
 */
@Mapper 
public interface MaintenanceTaskMapper extends BaseMapper<MaintenanceTask, MaintenanceTaskQueryCondition> {

    /**
     * 根据公司id 查询维护任务数量
     * @param companyId 公司id
     * @return 维护任务数量
     */
    Integer countByCompanyId(@Param("companyId") String companyId);
}