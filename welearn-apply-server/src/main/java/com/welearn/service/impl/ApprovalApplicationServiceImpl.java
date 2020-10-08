package com.welearn.service.impl;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.qo.apply.ApprovalApplicationQueryCondition;
import com.welearn.entity.vo.response.apply.ApplicationShow;
import com.welearn.generator.ControllerGenerator;
import com.welearn.mapper.ApprovalApplicationMapper;
import com.welearn.service.ApprovalApplicationService;
import com.welearn.service.EquipmentYearPlanService;
import com.welearn.util.PaginateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : ApprovalApplicationService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ApprovalApplicationServiceImpl extends BaseServiceImpl<ApprovalApplication,ApprovalApplicationQueryCondition,ApprovalApplicationMapper>
        implements ApprovalApplicationService{
    
    @Autowired
    private ApprovalApplicationMapper approvalApplicationMapper;
    
    @Override
    ApprovalApplicationMapper getMapper() {
        return approvalApplicationMapper;
    }

    /**
     * 根据查询条件获取申请审批相关数据
     *
     * @param condition ApprovalApplicationQueryCondition
     * @return List<ApplicationShow>
     */
    @Override
    public List<ApplicationShow> searchInfoByCondition(ApprovalApplicationQueryCondition condition) {
        return PaginateUtil.page(()-> approvalApplicationMapper.selectInfoByCondition(condition));
    }

    /**
     * 根据查询条件获取用户提交的申请数据
     *
     * @param condition ApprovalApplicationQueryCondition
     * @return List<ApplicationShow>
     */
    @Override
    public List<ApplicationShow> selectUserApplicationByCondition(ApprovalApplicationQueryCondition condition) {
        return PaginateUtil.page(()-> approvalApplicationMapper.selectUserApplicationByCondition(condition));
    }
}
