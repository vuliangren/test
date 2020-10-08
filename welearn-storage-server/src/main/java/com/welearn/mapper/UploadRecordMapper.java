package com.welearn.mapper;

import com.welearn.entity.po.storage.UploadRecord;
import com.welearn.entity.qo.storage.UploadRecordQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * UploadRecord Mapper Interface : ryme_storage : upload_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/30 9:36:19
 * @see com.welearn.entity.po.storage.UploadRecord
 */
@Mapper 
public interface UploadRecordMapper extends BaseMapper<UploadRecord, UploadRecordQueryCondition> {
    
}