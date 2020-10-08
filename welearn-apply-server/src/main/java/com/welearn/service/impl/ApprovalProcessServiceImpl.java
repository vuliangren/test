package com.welearn.service.impl;

import com.welearn.entity.po.apply.ApprovalProcess;
import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.qo.apply.ApprovalProcessPointQueryCondition;
import com.welearn.entity.qo.apply.ApprovalProcessQueryCondition;
import com.welearn.entity.vo.response.apply.ApprovalProcessInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.CompanyFeignClient;
import com.welearn.mapper.ApprovalProcessMapper;
import com.welearn.mapper.ApprovalProcessPointMapper;
import com.welearn.service.ApprovalProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Description : ApprovalProcessService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class ApprovalProcessServiceImpl extends BaseServiceImpl<ApprovalProcess,ApprovalProcessQueryCondition,ApprovalProcessMapper>
        implements ApprovalProcessService{
    
    @Autowired
    private ApprovalProcessMapper approvalProcessMapper;

    @Autowired
    private ApprovalProcessPointMapper approvalProcessPointMapper;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @Override
    ApprovalProcessMapper getMapper() {
        return approvalProcessMapper;
    }

    /**
     * 根据审批流程的编码和公司id获取申请流程详情
     *
     * @param code      流程编码
     * @param companyId 公司id
     * @return ApprovalProcessInfo
     */
    @Override
    public ApprovalProcessInfo selectInfoByCodeAndCompanyId(String code, String companyId) throws BusinessVerifyFailedException {
        Company company = companyFeignClient.select(companyId).getData();
        if (Objects.isNull(company) || !company.getIsEnable())
            throw new BusinessVerifyFailedException("companyId 非法");
        ApprovalProcessQueryCondition condition = new ApprovalProcessQueryCondition();
        condition.setCode(code);
        condition.setVisitorCompanyType(company.getType());
        condition.setIsEnable(true);
        List<ApprovalProcess> processes = approvalProcessMapper.selectByCondition(condition);
        if (processes.size() > 0){
            ApprovalProcess process = processes.get(0);
            ApprovalProcessPointQueryCondition q = new ApprovalProcessPointQueryCondition();
            q.setProcessId(process.getId());
            q.setIsEnable(true);
            // 判断是否使用系统设定的流程
            if (process.getIsUseDefaultProcessPoint()){
                q.setCompanyId(process.getCreatorCompanyId());
            } else {
                q.setCompanyId(companyId);
            }
            List<ApprovalProcessPoint> points = approvalProcessPointMapper.selectByCondition(q);
            return new ApprovalProcessInfo(process, points);
        }
        else
            return null;
    }

    /**
     * 获取分步申请的所有步骤的申请基本信息
     *
     * @param codeList  所有申请的code
     * @param companyId 公司id
     * @return Map<String, ApprovalProcess>
     */
    @Override
    public Map<String, ApprovalProcess> selectInfoByCodeListAndCompanyId(List<String> codeList, String companyId) {
        Company company = companyFeignClient.select(companyId).getData();
        if (Objects.isNull(company) || !company.getIsEnable())
            throw new BusinessVerifyFailedException("companyId 非法");
        Map<String, ApprovalProcess> result = new HashMap<>();
        codeList.forEach(code -> {
            ApprovalProcessQueryCondition condition = new ApprovalProcessQueryCondition();
            condition.setCode(code);
            condition.setVisitorCompanyType(company.getType());
            condition.setIsEnable(true);
            List<ApprovalProcess> processes = approvalProcessMapper.selectByCondition(condition);
            result.put(code, processes.get(0));
        });
        return result;
    }
}
