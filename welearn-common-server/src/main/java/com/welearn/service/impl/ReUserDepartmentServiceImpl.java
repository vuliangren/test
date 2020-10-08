package com.welearn.service.impl;

import com.welearn.entity.po.common.ReUserDepartment;
import com.welearn.entity.qo.common.ReUserDepartmentQueryCondition;
import com.welearn.mapper.ReUserDepartmentMapper;
import com.welearn.service.ReUserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description : ReUserDepartmentService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ReUserDepartmentServiceImpl extends BaseServiceImpl<ReUserDepartment,ReUserDepartmentQueryCondition,ReUserDepartmentMapper>
        implements ReUserDepartmentService{
    
    @Autowired
    private ReUserDepartmentMapper reUserDepartmentMapper;
    
    @Override
    ReUserDepartmentMapper getMapper() {
        return reUserDepartmentMapper;
    }

}
