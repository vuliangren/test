package com.welearn.service.impl;

import com.welearn.entity.po.storage.StatisticsRecord;
import com.welearn.entity.qo.storage.StatisticsRecordQueryCondition;
import com.welearn.mapper.StatisticsRecordMapper;
import com.welearn.service.StatisticsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : StatisticsRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class StatisticsRecordServiceImpl extends BaseServiceImpl<StatisticsRecord,StatisticsRecordQueryCondition,StatisticsRecordMapper>
        implements StatisticsRecordService{
    
    @Autowired
    private StatisticsRecordMapper statisticsRecordMapper;
    
    @Override
    StatisticsRecordMapper getMapper() {
        return statisticsRecordMapper;
    }

}
