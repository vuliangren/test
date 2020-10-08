package com.welearn.mapper;

import com.welearn.entity.po.notify.NoticeRecord;
import com.welearn.entity.qo.notify.NoticeRecordQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * NoticeRecord Mapper Interface : ryme_notify : notice_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/11 15:11:19
 * @see com.welearn.entity.po.notify.NoticeRecord
 */
@Mapper 
public interface NoticeRecordMapper extends BaseMapper<NoticeRecord, NoticeRecordQueryCondition> {
    
}