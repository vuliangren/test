package com.welearn.service;

import com.welearn.entity.po.storage.StatisticsRecord;
import com.welearn.entity.qo.storage.StatisticsRecordQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : StatisticsRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface StatisticsRecordService extends BaseService<StatisticsRecord, StatisticsRecordQueryCondition>{

}