package com.welearn.service;

import com.welearn.entity.po.equipment.ReMaintenanceRecordMethod;
import com.welearn.entity.qo.equipment.ReMaintenanceRecordMethodQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ReMaintenanceRecordMethodService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReMaintenanceRecordMethodService extends BaseService<ReMaintenanceRecordMethod, ReMaintenanceRecordMethodQueryCondition>{

}