package com.welearn.mapper;

import com.welearn.entity.po.storage.SignatureRecord;
import com.welearn.entity.qo.storage.SignatureRecordQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SignatureRecord Mapper Interface : ryme_storage : signature_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/25 10:48:55
 * @see SignatureRecord
 */
@Mapper 
public interface SignatureRecordMapper extends BaseMapper<SignatureRecord, SignatureRecordQueryCondition> {
    
}