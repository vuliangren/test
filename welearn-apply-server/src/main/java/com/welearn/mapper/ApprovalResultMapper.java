package com.welearn.mapper;

import com.welearn.entity.po.apply.ApprovalResult;
import com.welearn.entity.qo.apply.ApprovalResultQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * ApprovalResult Mapper Interface : ryme_apply : approval_result
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/18 17:12:07
 * @see ApprovalResult
 */
@Mapper 
public interface ApprovalResultMapper extends BaseMapper<ApprovalResult, ApprovalResultQueryCondition> {
    
}