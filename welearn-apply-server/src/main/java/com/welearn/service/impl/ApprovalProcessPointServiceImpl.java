package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.qo.apply.ApprovalProcessPointQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.error.exception.DbOperationFailedException;
import com.welearn.mapper.ApprovalProcessPointMapper;
import com.welearn.service.ApprovalProcessPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Description : ApprovalProcessPointService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ApprovalProcessPointServiceImpl extends BaseServiceImpl<ApprovalProcessPoint,ApprovalProcessPointQueryCondition,ApprovalProcessPointMapper>
        implements ApprovalProcessPointService{
    
    @Autowired
    private ApprovalProcessPointMapper approvalProcessPointMapper;
    
    @Override
    ApprovalProcessPointMapper getMapper() {
        return approvalProcessPointMapper;
    }


    public void update(ApprovalProcessPoint point){
        approvalProcessPointMapper.updateByPK(point);
    }

    @Override
    public void delete(String id){
        ApprovalProcessPoint point = this.select(id);
        if (Objects.isNull(point))
            throw new BusinessVerifyFailedException("id 非法");
        // 删除节点
        super.delete(id);
        // 查询相关节点
        ApprovalProcessPointQueryCondition condition = new ApprovalProcessPointQueryCondition();
        condition.setIsEnable(true);
        condition.setProcessId(point.getProcessId());
        List<ApprovalProcessPoint> points = this.search(condition);

        // 更新相关节点 sort
        for (int i = 0; i < points.size(); i++) {
            ApprovalProcessPoint p = points.get(i);
            if (p.getSort() != i){
                p.setSort(i);
                this.update(p);
            }
        }
    }

    /**
     * 获取申请的审批节点数量
     *
     * @param companyId 公司id
     * @param code      申请的编码
     * @return 节点数量
     */
    @Override
    public Integer pointCount(String companyId, String code) {
        return approvalProcessPointMapper.countByCompanyAndProcessCode(companyId, code);
    }
}
