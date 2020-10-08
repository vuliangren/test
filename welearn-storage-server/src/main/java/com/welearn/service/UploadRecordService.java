package com.welearn.service;

import com.welearn.entity.po.storage.UploadRecord;
import com.welearn.entity.qo.storage.UploadRecordQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : UploadRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface UploadRecordService extends BaseService<UploadRecord, UploadRecordQueryCondition>{

}