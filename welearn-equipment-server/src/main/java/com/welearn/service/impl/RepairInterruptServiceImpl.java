package com.welearn.service.impl;

import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.equipment.InsideDispatchStatusConst;
import com.welearn.dictionary.equipment.RepairRequestStatusConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.RepairDispatchInside;
import com.welearn.entity.po.equipment.RepairInterrupt;
import com.welearn.entity.po.equipment.RepairRequest;
import com.welearn.entity.qo.equipment.RepairDispatchInsideQueryCondition;
import com.welearn.entity.qo.equipment.RepairInterruptQueryCondition;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.apply.RepairInterruptApplyFeignClient;
import com.welearn.mapper.RepairInterruptMapper;
import com.welearn.mapper.RepairRequestMapper;
import com.welearn.service.RepairDispatchInsideService;
import com.welearn.service.RepairInterruptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.equipment.RepairRequestStatusConst.*;

/**
 * Description : RepairInterruptService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairInterruptServiceImpl extends BaseServiceImpl<RepairInterrupt,RepairInterruptQueryCondition,RepairInterruptMapper>
        implements RepairInterruptService{
    
    @Autowired
    private RepairInterruptMapper repairInterruptMapper;

    @Autowired
    private RepairRequestMapper repairRequestMapper;

    @Autowired
    private RepairDispatchInsideService repairDispatchInsideService;

    @Autowired
    private RepairInterruptApplyFeignClient repairInterruptApplyFeignClient;

    @Override
    RepairInterruptMapper getMapper() {
        return repairInterruptMapper;
    }


    /**
     * 当申请审批通过后执行的回调
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String contentId) {
        RepairInterrupt repairInterrupt = this.select(contentId);
        if (Objects.isNull(repairInterrupt))
            throw new BusinessVerifyFailedException("contentId 非法");
        RepairRequest repairRequest = repairRequestMapper.selectByPK(repairInterrupt.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != WILL_INTERRUPT.ordinal())
            throw new BusinessVerifyFailedException("repairInterrupt.requestId 非法");
        // 更新报修状态
        repairRequest.setStatus(INTERRUPT.ordinal());
        repairRequest.setFinishedAt(new Date());
        repairRequest.setRemark(repairInterrupt.getReason());
        repairRequestMapper.updateByPK(repairRequest);
        // 更改工程师派工状态为已中止
        RepairDispatchInsideQueryCondition condition = new RepairDispatchInsideQueryCondition();
        condition.setIsEnable(true);
        condition.setRequestId(repairRequest.getId());
        condition.setStatus(InsideDispatchStatusConst.RECEIVED.ordinal());
        List<RepairDispatchInside> dispatchInsides = repairDispatchInsideService.search(condition);
        for (RepairDispatchInside dispatchInside : dispatchInsides) {
            dispatchInside.setStatus(InsideDispatchStatusConst.INTERRUPT.ordinal());
            repairDispatchInsideService.update(dispatchInside);
            // 尝试取消内部派工工程师申请的配件更换
            repairDispatchInsideService.cancelDispatchInsideReplacement(dispatchInside);
        }
        // TODO: 通知工程师和报修人 维修中止
        // 标记中止申请审批通过
        repairInterrupt.setResult(true);
        this.update(repairInterrupt);
    }

    /**
     * 当申请审批失败后执行的回调
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String contentId) {
        RepairInterrupt repairInterrupt = this.select(contentId);
        if (Objects.isNull(repairInterrupt))
            throw new BusinessVerifyFailedException("contentId 非法");
        RepairRequest repairRequest = repairRequestMapper.selectByPK(repairInterrupt.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != WILL_INTERRUPT.ordinal())
            throw new BusinessVerifyFailedException("repairInterrupt.requestId 非法");
        // 更新报修状态
        repairRequest.setStatus(REPAIRING.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
        // TODO: 通知维修工程师 中止申请被否决
        // 标记中止申请审批通过
        repairInterrupt.setResult(false);
        this.update(repairInterrupt);
    }

    /**
     * 自动提交维修中止申请
     * @param repairInterrupt 维修中止申请
     * @return 申请
     */
    @Override
    public ApprovalApplication repairInterruptAutoSubmit(RepairInterrupt repairInterrupt, User user) {
        RepairRequest repairRequest = repairRequestMapper.selectByPK(repairInterrupt.getRequestId());
        if (Objects.isNull(repairRequest) || repairRequest.getStatus() != REPAIRING.ordinal())
            throw new BusinessVerifyFailedException("repairInterrupt.requestId 非法");
        repairRequest.setStatus(WILL_INTERRUPT.ordinal());
        repairRequestMapper.updateByPK(repairRequest);
        // 提交入库申请
        Apply<RepairInterrupt> request = new Apply<>();
        request.setContent(repairInterrupt);
        request.setApplicantId(user.getId());
        request.setType(ApplicationTypeConst.FORM_APPLICATION.ordinal());
        return repairInterruptApplyFeignClient.apply(request).getData();
    }
}
