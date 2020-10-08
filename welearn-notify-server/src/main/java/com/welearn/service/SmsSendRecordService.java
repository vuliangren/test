package com.welearn.service;

import com.welearn.entity.po.notify.SmsSendRecord;
import com.welearn.entity.qo.notify.SmsSendRecordQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : SmsSendRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SmsSendRecordService extends BaseService<SmsSendRecord, SmsSendRecordQueryCondition>{

}