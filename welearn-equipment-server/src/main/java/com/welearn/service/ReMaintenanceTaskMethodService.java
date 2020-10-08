package com.welearn.service;

import com.welearn.entity.po.equipment.ReMaintenanceTaskMethod;
import com.welearn.entity.qo.equipment.ReMaintenanceTaskMethodQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ReMaintenanceTaskMethodService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReMaintenanceTaskMethodService extends BaseService<ReMaintenanceTaskMethod, ReMaintenanceTaskMethodQueryCondition>{

}