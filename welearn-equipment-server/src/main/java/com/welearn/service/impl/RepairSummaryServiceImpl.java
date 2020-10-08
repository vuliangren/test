package com.welearn.service.impl;

import com.welearn.dictionary.equipment.RepairRequestStatusConst;
import com.welearn.entity.po.equipment.RepairRequest;
import com.welearn.entity.po.equipment.RepairSummary;
import com.welearn.entity.qo.equipment.RepairSummaryQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.RepairRequestMapper;
import com.welearn.mapper.RepairSummaryMapper;
import com.welearn.service.RepairSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Description : RepairSummaryService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairSummaryServiceImpl extends BaseServiceImpl<RepairSummary,RepairSummaryQueryCondition,RepairSummaryMapper>
        implements RepairSummaryService{
    
    @Autowired
    private RepairSummaryMapper repairSummaryMapper;

    @Autowired
    private RepairRequestMapper repairRequestMapper;

    @Override
    RepairSummaryMapper getMapper() {
        return repairSummaryMapper;
    }

    @Override
    public RepairSummary create(RepairSummary repairSummary){
        RepairRequest repairRequest = repairRequestMapper.selectByPK(repairSummary.getRequestId());
        // 仅能在 RepairRequestStatusConst 6/7/8/9/10 状态下提交总结
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() < RepairRequestStatusConst.UN_EVALUATE.ordinal() || repairRequest.getStatus() >= RepairRequestStatusConst.CANCEL.ordinal())
            throw new BusinessVerifyFailedException("evaluation.requestId 非法");
        // 更新报修状态
        repairRequest.setIsSummarize(true);
        repairRequestMapper.updateByPK(repairRequest);
        // 创建维修评价
        super.create(repairSummary);
        return repairSummary;
    }
}
