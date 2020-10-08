package com.welearn.service;

import com.welearn.entity.po.storage.SignatureRecord;
import com.welearn.entity.qo.storage.SignatureRecordQueryCondition;
import org.springframework.validation.annotation.Validated;

/**
 * Description : SignatureRecordService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface SignatureRecordService extends BaseService<SignatureRecord, SignatureRecordQueryCondition>{

}