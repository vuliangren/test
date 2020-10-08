package com.welearn.service.impl;

import com.welearn.entity.po.storage.UploadRecord;
import com.welearn.entity.qo.storage.UploadRecordQueryCondition;
import com.welearn.mapper.UploadRecordMapper;
import com.welearn.service.UploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : UploadRecordService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class UploadRecordServiceImpl extends BaseServiceImpl<UploadRecord,UploadRecordQueryCondition,UploadRecordMapper>
        implements UploadRecordService{
    
    @Autowired
    private UploadRecordMapper uploadRecordMapper;
    
    @Override
    UploadRecordMapper getMapper() {
        return uploadRecordMapper;
    }

}
