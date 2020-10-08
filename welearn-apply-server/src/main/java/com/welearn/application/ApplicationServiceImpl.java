package com.welearn.application;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.apply.ApplicationStatusConst;
import com.welearn.dictionary.apply.ApprovalPointTypeConst;
import com.welearn.dictionary.apply.ApprovalProcessTypeConst;
import com.welearn.dictionary.apply.ApprovalResultConst;
import com.welearn.dictionary.common.PositionConst;
import com.welearn.dictionary.common.PositionTypeConst;
import com.welearn.dictionary.notify.NoticeMethodConst;
import com.welearn.dictionary.notify.NoticeRefTypeConst;
import com.welearn.dictionary.notify.NoticeTypeConst;
import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.apply.ApprovalProcess;
import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.po.apply.ApprovalResult;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.common.Position;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.notify.Notice;
import com.welearn.entity.qo.apply.ApprovalApplicationQueryCondition;
import com.welearn.entity.qo.apply.ApprovalProcessPointQueryCondition;
import com.welearn.entity.qo.apply.ApprovalProcessQueryCondition;
import com.welearn.entity.qo.apply.ApprovalResultQueryCondition;
import com.welearn.entity.qo.common.PositionQueryCondition;
import com.welearn.entity.qo.common.UserQueryCondition;
import com.welearn.entity.vo.request.common.UserSearch;
import com.welearn.entity.vo.response.apply.ApplicationInfo;
import com.welearn.entity.vo.response.authorization.AuthResult;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.PositionFeignClient;
import com.welearn.feign.common.UserFeignClient;
import com.welearn.feign.notify.NoticeFeignClient;
import com.welearn.service.ApprovalApplicationService;
import com.welearn.service.ApprovalProcessPointService;
import com.welearn.service.ApprovalProcessService;
import com.welearn.service.ApprovalResultService;
import com.welearn.util.GlobalContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.welearn.dictionary.apply.ApplicationStatusConst.*;
import static com.welearn.dictionary.apply.ApprovalPointTypeConst.APPLICANT;
import static com.welearn.dictionary.apply.ApprovalPointTypeConst.POSITION_ANY;
import static com.welearn.dictionary.apply.ApprovalPointTypeConst.POSITION_EVERY;
import static com.welearn.dictionary.apply.ApprovalResultConst.MODIFY_AND_RE_APPROVAL;
import static com.welearn.dictionary.apply.ApprovalResultConst.NO_APPROVAL;
import static com.welearn.dictionary.apply.ApprovalResultConst.UN_APPROVAL;

/**
 * Description : 申请审批 核心流程抽象业务实现
 * Created by Setsuna Jin on 2018/9/19.
 */
@Slf4j
public abstract class ApplicationServiceImpl<T extends BasePersistant> implements ApplicationService<T> {

    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private ApprovalApplicationService approvalApplicationService;
    @Autowired
    private ApprovalProcessService approvalProcessService;
    @Autowired
    private ApprovalResultService approvalResultService;
    @Autowired
    private ApprovalProcessPointService approvalProcessPointService;
    @Autowired
    private NoticeFeignClient noticeFeignClient;
    @Autowired
    private PositionFeignClient positionFeignClient;

    public Position getSystemPositionByCode(String code){
        PositionQueryCondition condition = new PositionQueryCondition();
        condition.setIsEnable(true);
        condition.setCode(code);
        List<Position> positions = positionFeignClient.search(condition).getData();
        if (Objects.isNull(positions) || positions.isEmpty())
            throw new BusinessVerifyFailedException("系统基础职位缺失: %s", code);
        return positions.get(0);
    }


    private ApprovalProcess getProcess(int companyType) {
        String processCode = getApplicationType().getCode();
        ApprovalProcessQueryCondition condition = new ApprovalProcessQueryCondition();
        condition.setIsEnable(true);
        condition.setCode(processCode);
        condition.setVisitorCompanyType(companyType);
        List<ApprovalProcess> processes = approvalProcessService.search(condition);
        if (Objects.nonNull(processes) && processes.size() > 0){
            return processes.get(0);
        } else
            throw new BusinessVerifyFailedException("ApplicationType 无效");
    }

    private AuthResult getAuthResult() { 
        try {
            AuthResult authResult = GlobalContextUtil.getAuthResult();
            if (Objects.isNull(authResult) || Objects.isNull(authResult.getCompany()) || Objects.isNull(authResult.getDepartment()))
                throw new IllegalStateException();
            return authResult;
        } catch (Exception e) {
            throw new BusinessVerifyFailedException(e, "用户的认证信息无效");
        }
    }

    @Override
    public List<ApprovalProcessPoint> getProcessPoint(ApprovalProcess process, Company company, T content) {
        ApprovalProcessPointQueryCondition condition = new ApprovalProcessPointQueryCondition();
        condition.setProcessId(process.getId());
        condition.setIsEnable(true);
        // 判断是否使用系统设定的流程
        if (process.getIsUseDefaultProcessPoint()){
            condition.setCompanyId(process.getCreatorCompanyId());
        } else {
            condition.setCompanyId(company.getId());
        }
        return approvalProcessPointService.search(condition);
    }

    private List<ApprovalResult> getUnApprovalResult(List<ApprovalResult> approvalResults, Integer sort){
        return approvalResults.stream()
                .filter(r -> r.getSort().equals(sort) && r.getResult().equals(UN_APPROVAL.ordinal()))
                .collect(Collectors.toList());
    }

    private List<ApprovalResult> searchApprovalResults(String applicationId){
        ApprovalResultQueryCondition approvalResultQueryCondition = new ApprovalResultQueryCondition();
        approvalResultQueryCondition.setApplicationId(applicationId);
        return approvalResultService.search(approvalResultQueryCondition);
    }

    private void sendNotice(String outlook, String info, String userId, String userName, String applicationId){
        Notice notice = new Notice();
        notice.setType(NoticeTypeConst.USER_NOTICE.ordinal());
        notice.setMethod(NoticeMethodConst.MESSAGE.ordinal());
        notice.setTitle(String.format("[%s]%s", info, outlook));
        notice.setReceiverId(userId);
        notice.setReceiverName(userName);
        notice.setRefId(applicationId);
        notice.setRefType(NoticeRefTypeConst.APPLICATION.name());
        notice.setRemark(this.getApplicationType().getCode());
        noticeFeignClient.create(notice);
    }

    /**
     * 执行一步审批节点
     * 无审批结果->根据 首个 审批流程节点 创建 审批结果
     * 有审批结果->根据 后个 审批流程节点 创建 审批结果
     * 处理抄送通知类型审批判断 -> 自动递归
     * @param processPoints 审批流程
     * @param application 申请
     * @param approvalResults 现有审批结果
     */
    private void nextStep(List<ApprovalProcessPoint> processPoints, ApprovalApplication application, List<ApprovalResult> approvalResults) {
        if (Objects.isNull(approvalResults)){
            approvalResults = searchApprovalResults(application.getId());
        }
        // 获取当前所在审批节点
        int sort = -1;
        for (ApprovalResult r : approvalResults) {
            if (r.getSort() > sort)
                sort = r.getSort();
        }
        // 查找下一节点
        sort = sort + 1;
        // 审批通过
        if (processPoints.size() == sort){
            application.setStatus(PASS.ordinal());
            try {
                this.afterApplicationPass(application.getId(), application.getContentId());
                // 通知申请人
                this.sendNotice(application.getOutlook(), "审批通过", application.getApplicantId(), application.getApplicantName(), application.getId());
            } catch (Exception e) {
                log.error("Exception", e);
                log.error("申请id:{} outlook:{} 审批成功调用回调时发生异常:{}",application.getId(), application.getOutlook(), e.getMessage());
            }
        }
        // 执行下一步
        else if (processPoints.size() > sort) {
            ApprovalProcessPoint nextProcess = processPoints.get(sort);
            ApprovalPointTypeConst approvalPointType = ApprovalPointTypeConst.values()[nextProcess.getType()];
            switch (approvalPointType) {
                case APPLICANT:
                    // 添加待审批记录
                    ApprovalResult result = new ApprovalResult();
                    result.setSort(sort);
                    result.setApplicationId(application.getId());
                    result.setDepartmentId(nextProcess.getDepartmentId());
                    result.setDepartmentName(nextProcess.getDepartmentName());
                    result.setApprovalType(nextProcess.getApprovalType());
                    result.setApproverId(nextProcess.getApproverId());
                    result.setApproverName(nextProcess.getApproverName());
                    // 如为 抄送通知 类型
                    if (nextProcess.getApprovalType().equals(ApprovalProcessTypeConst.NOTIFY.ordinal())){
                        result.setResult(NO_APPROVAL.ordinal());
                        result.setApprovalAt(new Date());
                        // 通知申请人
                        this.sendNotice(application.getOutlook(), "抄送通知", nextProcess.getApproverId(), nextProcess.getApproverName(), application.getId());
                    }
                    else
                        result.setResult(UN_APPROVAL.ordinal());
                    approvalResultService.create(result);
                    break;
                case POSITION_ANY:
                case POSITION_EVERY:
                    // 查询职位相关用户
                    UserSearch userSearch = new UserSearch();
                    userSearch.setCompanyId(nextProcess.getCompanyId());
                    userSearch.setDepartmentId(nextProcess.getDepartmentId());
                    userSearch.setPositionId(nextProcess.getPositionId());
                    List<User> users = positionFeignClient.userSearch(userSearch).getData();
                    if (users.isEmpty())
                        throw new BusinessVerifyFailedException("职位:%s / %s 无人担任,后续审批流程无法执行,请联系相关负责人安排人员入职该职位", nextProcess.getDepartmentName(), nextProcess.getPositionName());
                    // 添加多个待审批记录
                    for (User user : users) {
                        ApprovalResult approvalResult = new ApprovalResult();
                        approvalResult.setSort(sort);
                        approvalResult.setApplicationId(application.getId());
                        approvalResult.setDepartmentId(nextProcess.getDepartmentId());
                        approvalResult.setDepartmentName(nextProcess.getDepartmentName());
                        approvalResult.setApprovalType(nextProcess.getApprovalType());
                        approvalResult.setApproverId(user.getId());
                        approvalResult.setApproverName(user.getName());
                        // 如为 抄送通知 类型
                        if (nextProcess.getApprovalType().equals(ApprovalProcessTypeConst.NOTIFY.ordinal())){
                            approvalResult.setResult(NO_APPROVAL.ordinal());
                            approvalResult.setApprovalAt(new Date());
                            // 通知申请人
                            this.sendNotice(application.getOutlook(), "抄送通知", user.getId(), user.getName(), application.getId());
                        }
                        else
                            approvalResult.setResult(UN_APPROVAL.ordinal());
                        approvalResultService.create(approvalResult);
                    }
                    break;
            }
            // 如为 抄送通知 类型, 递归 直接进行下一步
            if (nextProcess.getApprovalType().equals(ApprovalProcessTypeConst.NOTIFY.ordinal())){
                nextStep(processPoints, application, null);
            }
        } else {
            throw new BusinessVerifyFailedException("approvalResults 非法");
        }
    }

    @Transactional
    public ApprovalApplication saveOrApply(T content, User applicant, ApplicationStatusConst status, Integer type, String linkId) {
        // 获取前置信息
        AuthResult authResult = getAuthResult();
        Company company = authResult.getCompany();
        Department department = authResult.getDepartment();
        ApprovalProcess process = getProcess(company.getType());
        if (Objects.isNull(process))
            throw new BusinessVerifyFailedException("获取审批流程数据失败");
        List<ApprovalProcessPoint> processPoints = getProcessPoint(process, company, content);
        if (Objects.isNull(processPoints))
            throw new BusinessVerifyFailedException("获取审批流程节点列表数据失败");
        // 保存申请内容
        if (Objects.nonNull(content.getId())){
            ApprovalApplicationQueryCondition condition = new ApprovalApplicationQueryCondition();
            condition.setContentId(content.getId());
            condition.setProcessId(process.getId());
            condition.setIsEnable(true);
            List<ApprovalApplication> applications = approvalApplicationService.search(condition);
            if (Objects.nonNull(applications) && applications.size() == 1){
                ApprovalApplication application = applications.get(0);
                updateContent(content);
                // 更新申请状态
                if (application.getStatus() <= status.ordinal()){
                    application.setStatus(status.ordinal());
                    application.setApplyAt(new Date());
                    approvalApplicationService.update(application);
                }
                else {
                    throw new BusinessVerifyFailedException("已经提交的申请无法进行保存操作");
                }
                // 执行一个审批节点
                if (application.getStatus().equals(ApplicationStatusConst.UN_APPROVAL.ordinal())) {
                    this.nextStep(processPoints, application, Collections.emptyList());
                    approvalApplicationService.update(application);
                }

                return application;
            }
        }
        // 创建申请信息
        if (Objects.nonNull(content.getId()))
            updateContent(content);
        else
            createContent(content);
        ApprovalApplication application = new ApprovalApplication();
        application.setProcessId(process.getId());
        application.setLinkId(linkId);
        application.setType(type);
        application.setApplyAt(new Date());
        application.setCompanyId(company.getId());
        application.setDepartmentId(department.getId());
        application.setDepartmentName(department.getName());
        application.setContentId(content.getId());
        application.setApplicantId(applicant.getId());
        application.setApplicantName(applicant.getName());
        application.setOutlook(getOutlook(content));
        application.setProcessJson(JSON.toJSONString(processPoints));
        application.setStatus(status.ordinal());
        approvalApplicationService.create(application);
        // 执行回调
        afterCreateContent(content, application);
        // 执行一个审批节点
        if (application.getStatus().equals(ApplicationStatusConst.UN_APPROVAL.ordinal())) {
            this.nextStep(processPoints, application, Collections.emptyList());
            if (application.getStatus() != status.ordinal())
                approvalApplicationService.update(application);
        }
        return application;
    }

    private void updateResultList(List<ApprovalResult> results, ApprovalResult result){
        result.setApprovalAt(new Date());
        approvalResultService.update(result);
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getId().equals(result.getId()))
                results.set(i, result);
        }
    }

    /**
     * 查看申请
     *
     * @param applicationId 申请id
     * @param userId          查看人
     * @return 申请详情
     */
    @Override @Transactional
    public ApplicationInfo<T> view(String applicationId, String userId) {
        ApprovalApplication application = approvalApplicationService.select(applicationId);
        if (Objects.isNull(application))
            throw new BusinessVerifyFailedException("applicationId 非法");
        // 获取申请结果
        ApprovalResultQueryCondition condition = new ApprovalResultQueryCondition();
        condition.setIsEnable(true);
        condition.setApplicationId(applicationId);
        List<ApprovalResult> approvalResults = approvalResultService.search(condition);
        if (!application.getApplicantId().equals(userId)){
            List<ApprovalResult> collect = approvalResults.stream().filter(r -> r.getApproverId().equals(userId)).collect(Collectors.toList());
            // TODO: 暂时取消对其限制, 以后再做修改
//            if (collect.size() == 0)
//                throw new BusinessVerifyFailedException("您无权限查看此申请信息");
        }
        // 获取申请流程
        ApprovalProcess process = approvalProcessService.select(application.getProcessId());
        ApplicationInfo<T> applicationInfo = new ApplicationInfo<>(application);
        // 获取申请内容
        applicationInfo.setContent(selectContent(application.getContentId()));
        applicationInfo.setProcess(process);
        applicationInfo.setResults(approvalResults);
        return applicationInfo;
    }

    /**
     * 发起申请
     *
     * @param content   申请内容
     * @param applicantId 申请人
     */
    @Override @Transactional
    public ApprovalApplication save(T content, String applicantId, Integer type, String linkId) {
        User applicant = userFeignClient.select(applicantId).getData();
        if (Objects.isNull(applicant))
            throw new BusinessVerifyFailedException("applicantId 非法");
        // 创建申请 创建或保存申请内容
        return saveOrApply(content, applicant, UN_SUBMIT, type, linkId);
    }

    /**
     * 发起申请
     *
     * @param content   申请内容
     * @param applicantId 申请人
     */
    @Override @Transactional
    public ApprovalApplication apply(T content, String applicantId, Integer type, String linkId) {
        User applicant = userFeignClient.select(applicantId).getData();
        if (Objects.isNull(applicant))
            throw new BusinessVerifyFailedException("applicantId 非法");
        // 根据状态确定创建或更新申请 创建或保存申请内容 执行一个审批节点
        return saveOrApply(content, applicant, ApplicationStatusConst.UN_APPROVAL, type, linkId);
    }

    /**
     * 审批申请
     *
     * @param points   当前审批节点信息
     * @param result   审批结果
     * @param approverId 审批人
     */
    @Override @Transactional
    public void approval(List<ApprovalProcessPoint> points, ApprovalResult result, String approverId) {
        // 验证申请人身份
        ApprovalResult select = approvalResultService.select(result.getId());
        if (Objects.isNull(select) || !select.getApproverId().equals(approverId) || !select.getApplicationId().equals(result.getApplicationId()))
            throw new BusinessVerifyFailedException("result 与 approvalId 非法");
        // 获取申请信息
        ApprovalApplication application = approvalApplicationService.select(result.getApplicationId());
        ApprovalProcessPoint approvalProcessPoint = points.get(result.getSort());
        if (Objects.isNull(application))
            throw new BusinessVerifyFailedException("applicationId 无效");
        // 仅限审批节点类型为 审批人 时 修改重审 才可用
        if (result.getResult().equals(UN_APPROVAL.ordinal())
                || result.getResult().equals(NO_APPROVAL.ordinal())
                || !approvalProcessPoint.getType().equals(APPLICANT.ordinal()) && result.getResult().equals(MODIFY_AND_RE_APPROVAL.ordinal())
        )
            throw new BusinessVerifyFailedException("result 无效");
        // 获取已有审批结果
        List<ApprovalResult> approvalResults = searchApprovalResults(result.getApplicationId());
        // 验证当前审批流程
        for (ApprovalResult r : approvalResults) {
            ApprovalProcessPoint p = points.get(r.getSort());
            if (r.getDepartmentId().equals(p.getDepartmentId()) && r.getApprovalType().equals(p.getApprovalType()))
                continue;
            throw new BusinessVerifyFailedException("points 无效");
        }
        // 更新当前审批结果
        this.updateResultList(approvalResults, result);
        // 判断当前结果类型
        ApprovalResultConst approvalResult = ApprovalResultConst.get(result.getResult());
        switch (approvalResult) {
            // 审批通过
            case PASS:
                // 审批通过需要含有签字信息
                if (result.getApprovalType() == ApprovalProcessTypeConst.APPROVAL.ordinal() && Objects.isNull(result.getSignatureId()))
                    throw new BusinessVerifyFailedException("审批通过需进行签字确认");
                // 判断审批节点类型是否为 职位任意人, 当 存在一个 审批通过 则更新所有剩余 待审批 -> 无须审批
                if (approvalProcessPoint.getType().equals(POSITION_ANY.ordinal())){
                    List<ApprovalResult> unApprovalResults = getUnApprovalResult(approvalResults,result.getSort());
                    Date approvalAt = new Date();
                    unApprovalResults.forEach(r -> {
                        r.setApprovalAt(approvalAt);
                        r.setResult(NO_APPROVAL.ordinal());
                        approvalResultService.update(r);
                    });
                }
                // 判断审批节点类型是否为 职位所有人, 如为所有人判断是否存在未审批用户
                if (approvalProcessPoint.getType().equals(POSITION_EVERY.ordinal())){
                    List<ApprovalResult> unApprovalResults = getUnApprovalResult(approvalResults,result.getSort());
                    // 虽然审批成功, 但仍有待审批项
                    if (unApprovalResults.size() > 0) break;
                }
                // 进入下一个审批流程节点
                nextStep(points, application ,approvalResults);
                break;
            // 审批失败
            case REJECT:
                // 判断审批节点类型是否为 职位任意人, 如为任意人且判断是否存在未审批用户
                if (approvalProcessPoint.getType().equals(POSITION_ANY.ordinal())){
                    List<ApprovalResult> unApprovalResults = getUnApprovalResult(approvalResults,result.getSort());
                    // 虽然审批失败 但仍可能继续审批
                    if (unApprovalResults.size() > 0) break;
                }
                // 判断审批节点类型是否为 职位所有人, 如为所有人, 当 存在一个 审批失败 则更新所有剩余 待审批 -> 无须审批
                if (approvalProcessPoint.getType().equals(POSITION_EVERY.ordinal())){
                    List<ApprovalResult> unApprovalResults = getUnApprovalResult(approvalResults,result.getSort());
                    Date approvalAt = new Date();
                    unApprovalResults.forEach(r -> {
                        r.setApprovalAt(approvalAt);
                        r.setResult(NO_APPROVAL.ordinal());
                        approvalResultService.update(r);
                    });
                }
                // 修改申请状态
                application.setStatus(REJECT.ordinal());
                // 调用失败回调
                try {
                    this.afterApplicationReject(application.getId(), application.getContentId());
                    // 通知申请人
                    this.sendNotice(application.getOutlook(), "审批失败", application.getApplicantId(), application.getApplicantName(), application.getId());
                } catch (Exception e) {
                    log.error("Exception", e);
                    log.error("申请id:{} outlook:{} 审批失败调用回调时发生异常:{}",application.getId(), application.getOutlook(), e.getMessage());
                }
                break;
            // 修改重审 仅限审批节点类型为 审批人 时可用
            case MODIFY_AND_RE_APPROVAL:
                // 添加重审记录
                ApprovalResult reApprovalResult = new ApprovalResult();
                reApprovalResult.setResult(UN_APPROVAL.ordinal());
                reApprovalResult.setSort(result.getSort());
                reApprovalResult.setApplicationId(result.getApplicationId());
                reApprovalResult.setDepartmentId(result.getDepartmentId());
                reApprovalResult.setDepartmentName(result.getDepartmentName());
                reApprovalResult.setApprovalType(result.getApprovalType());
                reApprovalResult.setApproverId(result.getApproverId());
                reApprovalResult.setApproverName(result.getApproverName());
                approvalResultService.create(reApprovalResult);
                // 修改申请状态
                application.setStatus(UN_MODIFY.ordinal());
                // 通知申请人
                this.sendNotice(application.getOutlook(), "修改重审", application.getApplicantId(), application.getApplicantName(), application.getId());
                break;
        }
        // 更新审批的实际审批流程
        application.setProcessJson(JSON.toJSONString(points));
        approvalApplicationService.update(application);
    }

    /**
     * 修改申请
     *
     * @param applicationId 申请id
     * @param content       申请内容
     * @param applicantId     申请人
     */
    @Override @Transactional
    public void modify(String applicationId, T content, String applicantId) {
        ApprovalApplication application = approvalApplicationService.select(applicationId);
        if (Objects.isNull(application) || Objects.isNull(application.getContentId()) || !application.getApplicantId().equals(applicantId))
            throw new BusinessVerifyFailedException("applicationId 非法");
        updateContent(content);
        application.setStatus(ApplicationStatusConst.UN_APPROVAL.ordinal());
        approvalApplicationService.update(application);
    }

    /**
     * 删除未提交但已保存的申请
     * @param applicationId 申请id
     * @param applicantId 申请人
     */
    @Override @Transactional
    public void delete(String applicationId, String applicantId) {
        ApprovalApplication application = approvalApplicationService.select(applicationId);
        if (Objects.isNull(application) || Objects.isNull(application.getContentId()) || !application.getApplicantId().equals(applicantId))
            throw new BusinessVerifyFailedException("applicationId 非法");
        deleteContent(application.getContentId());
        approvalApplicationService.delete(applicationId);
    }

    /**
     * 申请被取消
     * @param applicationId 申请id
     * @return 是否取消成功
     */
    public Boolean cancel(String applicationId){
        ApprovalApplication application = approvalApplicationService.select(applicationId);
        if (Objects.isNull(application) || Objects.isNull(application.getContentId()))
            throw new BusinessVerifyFailedException("applicationId 非法");
        if (application.getStatus() < ApplicationStatusConst.PASS.ordinal()){
            application.setStatus(ApplicationStatusConst.CANCEL.ordinal());
            approvalApplicationService.update(application);
            try {
                this.afterApplicationCancel(applicationId, application.getContentId());
            } catch (Exception e) {
                log.error(String.format("申请id:%s outlook:%s 审批被取消后调用回调时发生异常:%s", application.getId(),  application.getOutlook(), e.getMessage()), e);
            }
            return true;
        } else
            return false;
    }
}
