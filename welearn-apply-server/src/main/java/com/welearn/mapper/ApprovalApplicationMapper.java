package com.welearn.mapper;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.qo.apply.ApprovalApplicationQueryCondition;
import com.welearn.entity.vo.response.apply.ApplicationShow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ApprovalApplication Mapper Interface : ryme_apply : approval_application
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/18 17:12:07
 * @see ApprovalApplication
 */
@Mapper 
public interface ApprovalApplicationMapper extends BaseMapper<ApprovalApplication, ApprovalApplicationQueryCondition> {

    List<ApplicationShow> selectInfoByCondition(ApprovalApplicationQueryCondition condition);

    List<ApplicationShow> selectUserApplicationByCondition(ApprovalApplicationQueryCondition condition);
}