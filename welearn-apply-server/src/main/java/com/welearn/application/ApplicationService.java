package com.welearn.application;

import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.apply.ApprovalProcess;
import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.po.apply.ApprovalResult;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.vo.response.apply.ApplicationInfo;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/18.
 */
public interface ApplicationService<T extends BasePersistant> {


    /**
     * 获取审批流程节点
     * @param process 审批流程
     * @param company 公司
     * @param content 审批内容
     * @return 审批流程节点
     */
    List<ApprovalProcessPoint> getProcessPoint(ApprovalProcess process, Company company, T content);

    /**
     * 查看申请
     * @param applicationId 申请id
     * @param userId 查看人
     * @return 申请详情
     */
    ApplicationInfo<T> view(@NotBlank String applicationId,
                            @NotBlank String userId);

    /**
     * 发起申请
     * @param content 申请内容
     * @param applicantId 申请人
     */
    ApprovalApplication save(@EntityCheck @Valid T content,
                             @NotBlank String applicantId,
                             @Range(min = 0, max = 1) Integer type,
                             String linkId);

    /**
     * 发起申请
     * @param content 申请内容
     * @param applicantId 申请人
     */
    ApprovalApplication apply(@EntityCheck @Valid T content,
                              @NotBlank String applicantId,
                              @Range(min = 0, max = 1) Integer type,
                              String linkId);

    /**
     * 审批申请
     * @param points 当前审批节点信息
     * @param result 审批结果
     * @param approverId 审批人
     */
    void approval(@EntityCheck List<ApprovalProcessPoint> points,
                  @EntityCheck @Valid ApprovalResult result,
                  @NotBlank String approverId);

    /**
     * 修改申请
     * @param applicationId 申请id
     * @param content 申请内容
     * @param applicantId 申请人
     */
    void modify(@NotBlank String applicationId,
                @EntityCheck @Valid T content,
                @NotBlank String applicantId);

    /**
     * 删除未提交但已保存的申请
     * @param applicationId 申请id
     * @param applicantId 申请人
     */
    void delete(@NotBlank String applicationId, @NotBlank String applicantId);

    /**
     * 申请被取消
     * @param applicationId 申请id
     * @return 是否取消成功
     */
    Boolean cancel(@NotBlank String applicationId);

    /**
     * 创建申请内容
     * @param content 申请内容
     */
    void createContent(@EntityCheck @Valid T content);

    /**
     * 创建申请内容后调用, 可覆盖用来及时记录申请信息到content中
     * @param content 申请内容
     * @param application 申请信息
     */
    default void afterCreateContent(@EntityCheck(checkId = true) T content, @EntityCheck(checkId = true) ApprovalApplication application) {}

    /**
     * 删除申请内容
     * @param contentId 内容id
     */
    void deleteContent(@NotBlank String contentId);

    /**
     * 更新申请内容
     * @param content 申请内容
     */
    void updateContent(@EntityCheck(checkId = true) @Valid T content);

    /**
     * 根据申请获取申请内容
     * @param contentId 内容id
     * @return 内容
     */
    T selectContent(@NotBlank String contentId);

    /**
     * 根据申请内容获取申请简述
     * @param content 内容
     * @return 简述
     */
    String getOutlook(@EntityCheck T content);

    /**
     * 获取申请类型编码
     * @return 申请类型编码
     */
    ApplicationHandleTypeConst getApplicationType();

    /**
     * 当申请通过审批后执行的回调
     * @param applicationId 申请id
     * @param contentId 申请内容id
     */
    void afterApplicationPass(String applicationId, String contentId) throws Exception;

    /**
     * 当申请审批失败后执行的回调
     * @param applicationId 申请id
     * @param contentId 申请内容id
     */
    void afterApplicationReject(String applicationId, String contentId) throws Exception;

    /**
     * 当申请被取消后执行的回调
     * @param applicationId 申请id
     * @param contentId 内容id
     */
    default void afterApplicationCancel(String applicationId, String contentId) throws Exception {}
}
