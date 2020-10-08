package com.welearn.service;

import com.welearn.entity.po.equipment.ReMaintenanceTaskUser;
import com.welearn.entity.qo.equipment.ReMaintenanceTaskUserQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ReMaintenanceTaskUserService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReMaintenanceTaskUserService extends BaseService<ReMaintenanceTaskUser, ReMaintenanceTaskUserQueryCondition>{

}