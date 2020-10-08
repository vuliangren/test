package com.welearn.service;

import com.welearn.entity.po.common.ReUserRole;
import com.welearn.entity.qo.common.ReUserRoleQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : Service Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReUserRoleService extends BaseService<ReUserRole, ReUserRoleQueryCondition>{

}