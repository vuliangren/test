package com.welearn.mapper;

import com.welearn.entity.po.apply.ApprovalProcess;
import com.welearn.entity.qo.apply.ApprovalProcessQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * ApprovalProcess Mapper Interface : ryme_apply : approval_process
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/18 17:12:07
 * @see ApprovalProcess
 */
@Mapper 
public interface ApprovalProcessMapper extends BaseMapper<ApprovalProcess, ApprovalProcessQueryCondition> {
    
}