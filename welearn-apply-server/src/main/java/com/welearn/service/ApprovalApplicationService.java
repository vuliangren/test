package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.qo.apply.ApprovalApplicationQueryCondition;
import com.welearn.entity.vo.response.apply.ApplicationShow;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : ApprovalApplicationService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ApprovalApplicationService extends BaseService<ApprovalApplication, ApprovalApplicationQueryCondition>{
    /**
     * 根据查询条件获取申请审批相关数据
     * @param condition ApprovalApplicationQueryCondition
     * @return List<ApplicationShow>
     */
    List<ApplicationShow> searchInfoByCondition(ApprovalApplicationQueryCondition condition);

    /**
     * 根据查询条件获取用户提交的申请数据
     * @param condition ApprovalApplicationQueryCondition
     * @return List<ApplicationShow>
     */
    List<ApplicationShow> selectUserApplicationByCondition(ApprovalApplicationQueryCondition condition);
}