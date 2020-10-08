package com.welearn.service;

import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.qo.equipment.EngineerQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : EngineerService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EngineerService extends BaseService<Engineer, EngineerQueryCondition>{

}