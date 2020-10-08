package com.welearn.entity.vo.response.apply;

import com.alibaba.fastjson.JSON;
import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.apply.ApprovalProcess;
import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.po.apply.ApprovalResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/19.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationInfo<T extends BasePersistant> extends ApprovalApplication {
    private ApprovalProcess process;
    private List<ApprovalProcessPoint> points;
    private List<ApprovalResult> results;
    private T content;

    public ApplicationInfo(ApprovalApplication application) {
        this.setApplicantId(application.getApplicantId());
        this.setApplicantName(application.getApplicantName());
        this.setCompanyId(application.getCompanyId());
        this.setDepartmentId(application.getDepartmentId());
        this.setDepartmentName(application.getDepartmentName());
        this.setApplyAt(application.getApplyAt());
        this.setStatus(application.getStatus());
        this.setContentId(application.getContentId());
        this.setOutlook(application.getOutlook());
        this.setProcessId(application.getProcessId());
        String processJson = application.getProcessJson();
        if (StringUtils.isNotBlank(processJson)){
            this.setPoints(JSON.parseArray(processJson, ApprovalProcessPoint.class));
        }
    }
}
