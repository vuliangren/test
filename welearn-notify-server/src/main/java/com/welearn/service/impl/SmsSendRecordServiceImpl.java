package com.welearn.service.impl;

import com.welearn.entity.po.notify.SmsSendRecord;
import com.welearn.entity.qo.notify.SmsSendRecordQueryCondition;
import com.welearn.mapper.SmsSendRecordMapper;
import com.welearn.service.SmsSendRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : SmsSendRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SmsSendRecordServiceImpl extends BaseServiceImpl<SmsSendRecord,SmsSendRecordQueryCondition,SmsSendRecordMapper>
        implements SmsSendRecordService{
    
    @Autowired
    private SmsSendRecordMapper smsSendRecordMapper;
    
    @Override
    SmsSendRecordMapper getMapper() {
        return smsSendRecordMapper;
    }

}
