package com.welearn.service;

import com.welearn.entity.po.equipment.SparePartUsage;
import com.welearn.entity.qo.equipment.SparePartUsageQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : SparePartUsageService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SparePartUsageService extends BaseService<SparePartUsage, SparePartUsageQueryCondition>{

}