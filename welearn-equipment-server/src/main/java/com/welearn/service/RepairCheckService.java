package com.welearn.service;

import com.welearn.entity.po.equipment.RepairCheck;
import com.welearn.entity.qo.equipment.RepairCheckQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : RepairCheckService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairCheckService extends BaseService<RepairCheck, RepairCheckQueryCondition>{

}