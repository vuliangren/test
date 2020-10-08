package com.welearn.service;

import com.welearn.entity.po.equipment.SparePartReplacement;
import com.welearn.entity.qo.equipment.SparePartReplacementQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : SparePartReplacementService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SparePartReplacementService extends BaseService<SparePartReplacement, SparePartReplacementQueryCondition>{

}