package com.welearn.service;

import com.welearn.entity.po.notify.NoticeRecord;
import com.welearn.entity.qo.notify.NoticeRecordQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : NoticeRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface NoticeRecordService extends BaseService<NoticeRecord, NoticeRecordQueryCondition>{

}