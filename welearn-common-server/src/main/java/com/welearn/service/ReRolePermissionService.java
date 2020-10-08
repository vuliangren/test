package com.welearn.service;

import com.welearn.entity.po.common.ReRolePermission;
import com.welearn.entity.qo.common.ReRolePermissionQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : Service Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReRolePermissionService extends BaseService<ReRolePermission, ReRolePermissionQueryCondition>{

}