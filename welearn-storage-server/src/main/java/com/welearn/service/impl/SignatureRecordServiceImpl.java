package com.welearn.service.impl;

import com.welearn.entity.po.storage.SignatureRecord;
import com.welearn.entity.qo.storage.SignatureRecordQueryCondition;
import com.welearn.mapper.SignatureRecordMapper;
import com.welearn.service.SignatureRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : SignatureRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class SignatureRecordServiceImpl extends BaseServiceImpl<SignatureRecord,SignatureRecordQueryCondition,SignatureRecordMapper>
        implements SignatureRecordService{
    
    @Autowired
    private SignatureRecordMapper signatureRecordMapper;
    
    @Override
    SignatureRecordMapper getMapper() {
        return signatureRecordMapper;
    }

}
