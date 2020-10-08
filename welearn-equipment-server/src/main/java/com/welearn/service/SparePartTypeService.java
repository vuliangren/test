package com.welearn.service;

import com.welearn.entity.po.equipment.SparePartType;
import com.welearn.entity.qo.equipment.SparePartTypeQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : SparePartTypeService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SparePartTypeService extends BaseService<SparePartType, SparePartTypeQueryCondition>{

}