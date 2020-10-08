package com.welearn.service;

import com.welearn.entity.po.common.ReUserDepartment;
import com.welearn.entity.qo.common.ReUserDepartmentQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : Service Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReUserDepartmentService extends BaseService<ReUserDepartment, ReUserDepartmentQueryCondition>{

}