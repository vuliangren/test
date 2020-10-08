package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalProcess;
import com.welearn.entity.qo.apply.ApprovalProcessQueryCondition;
import com.welearn.entity.vo.response.apply.ApprovalProcessInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * Description : ApprovalProcessService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface ApprovalProcessService extends BaseService<ApprovalProcess, ApprovalProcessQueryCondition>{
    /**
     * 根据审批流程的编码和公司id获取申请流程详情
     * @param code 流程编码
     * @param companyId 公司id
     * @return ApprovalProcessInfo
     */
    ApprovalProcessInfo selectInfoByCodeAndCompanyId(String code, String companyId);

    /**
     * 获取分步申请的所有步骤的申请基本信息
     * @param codeList 所有申请的code
     * @param companyId 公司id
     * @return Map<String, ApprovalProcess>
     */
    Map<String, ApprovalProcess> selectInfoByCodeListAndCompanyId(List<String> codeList, String companyId);
}