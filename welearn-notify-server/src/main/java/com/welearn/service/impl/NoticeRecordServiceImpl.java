package com.welearn.service.impl;

import com.welearn.entity.po.notify.NoticeRecord;
import com.welearn.entity.qo.notify.NoticeRecordQueryCondition;
import com.welearn.mapper.NoticeRecordMapper;
import com.welearn.service.NoticeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : NoticeRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class NoticeRecordServiceImpl extends BaseServiceImpl<NoticeRecord,NoticeRecordQueryCondition,NoticeRecordMapper>
        implements NoticeRecordService{
    
    @Autowired
    private NoticeRecordMapper noticeRecordMapper;
    
    @Override
    NoticeRecordMapper getMapper() {
        return noticeRecordMapper;
    }

}
