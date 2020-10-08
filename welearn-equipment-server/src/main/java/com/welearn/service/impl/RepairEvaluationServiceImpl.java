package com.welearn.service.impl;

import com.welearn.dictionary.equipment.RepairRequestStatusConst;
import com.welearn.entity.po.equipment.RepairEvaluation;
import com.welearn.entity.po.equipment.RepairRequest;
import com.welearn.entity.qo.equipment.RepairEvaluationQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.RepairEvaluationMapper;
import com.welearn.mapper.RepairRequestMapper;
import com.welearn.service.RepairEvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Description : RepairEvaluationService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class RepairEvaluationServiceImpl extends BaseServiceImpl<RepairEvaluation,RepairEvaluationQueryCondition,RepairEvaluationMapper>
        implements RepairEvaluationService{
    
    @Autowired
    private RepairEvaluationMapper repairEvaluationMapper;

    @Autowired
    private RepairRequestMapper repairRequestMapper;

    @Override
    RepairEvaluationMapper getMapper() {
        return repairEvaluationMapper;
    }

    @Override
    public RepairEvaluation create(RepairEvaluation evaluation){
        RepairRequest repairRequest = repairRequestMapper.selectByPK(evaluation.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != RepairRequestStatusConst.UN_EVALUATE.ordinal())
            throw new BusinessVerifyFailedException("evaluation.requestId 非法");
        // 更新报修状态
        repairRequest.setStatus(RepairRequestStatusConst.FINISH.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
        // 创建维修评价
        super.create(evaluation);
        return evaluation;
    }

}
