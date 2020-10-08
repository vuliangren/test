package com.welearn.mapper;

import com.welearn.entity.po.equipment.MaintenanceRecord;
import com.welearn.entity.qo.equipment.MaintenanceRecordQueryCondition;
import com.welearn.entity.vo.response.equipment.MaintenanceRecordInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * MaintenanceRecord Mapper Interface : ryme_equipment : maintenance_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/3 13:23:39
 * @see com.welearn.entity.po.equipment.MaintenanceRecord
 */
@Mapper 
public interface MaintenanceRecordMapper extends BaseMapper<MaintenanceRecord, MaintenanceRecordQueryCondition> {

    /**
     * 根据id查询启用的维护记录详情
     * @param id 记录id
     * @return 维护记录详情
     */
    MaintenanceRecordInfo selectEnabledInfoById(@Param("id") String id);

    /**
     * 根据公司id 查询维护记录数量
     * @param companyId 公司id
     * @return 维护记录数量
     */
    Integer countByCompanyId(@Param("companyId") String companyId, @Param("startAt") Date startAt);

    /**
     * 根据工程师id 查询维护记录数量
     * @param engineerId 工程师id
     * @return 维护记录数量
     */
    Integer countByEngineerId(@Param("engineerId") String engineerId, @Param("startAt") Date startAt);
}