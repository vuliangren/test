package com.welearn.service;

import com.welearn.entity.po.equipment.ReTaskAssignmentMethod;
import com.welearn.entity.qo.equipment.ReTaskAssignmentMethodQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : ReTaskAssignmentMethodService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ReTaskAssignmentMethodService extends BaseService<ReTaskAssignmentMethod, ReTaskAssignmentMethodQueryCondition>{

}