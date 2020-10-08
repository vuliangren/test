package com.welearn.service;

import com.welearn.entity.po.equipment.RepairSummary;
import com.welearn.entity.qo.equipment.RepairSummaryQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : RepairSummaryService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairSummaryService extends BaseService<RepairSummary, RepairSummaryQueryCondition>{

}