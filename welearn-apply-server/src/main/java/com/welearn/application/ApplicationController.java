package com.welearn.application;

import com.welearn.entity.po.BasePersistant;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.vo.request.apply.*;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.apply.ApplicationInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.util.UUIDGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description : 抽象申请处理控制器
 * Created by Setsuna Jin on 2018/10/8.
 */
public abstract class ApplicationController<T extends BasePersistant, S extends ApplicationService<T>> {

    @Autowired
    protected S service;

    @RequestMapping(value = "/apply")
    @ApiOperation(value = "提交申请", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> apply(@RequestBody Apply<T> apply) {
        ApprovalApplication result = service.apply(apply.getContent(), apply.getApplicantId(), apply.getType(), apply.getLinkId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/delete")
    @ApiOperation(value = "删除申请", httpMethod = "POST")
    public CommonResponse delete(@RequestBody Delete delete) {
        service.delete(delete.getApplicationId(), delete.getApplicantId());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/save")
    @ApiOperation(value = "保存申请", httpMethod = "POST")
    public CommonResponse<ApprovalApplication> save(@RequestBody Save<T> save) {
        ApprovalApplication result = service.save(save.getContent(), save.getApplicantId(), save.getType(), save.getLinkId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/view")
    @ApiOperation(value = "查看申请", httpMethod = "POST")
    public CommonResponse<ApplicationInfo<T>> view(@RequestBody View view) {
        ApplicationInfo<T> result = service.view(view.getApplicationId(), view.getUserId());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/modify")
    @ApiOperation(value = "修改申请", httpMethod = "POST")
    public CommonResponse modify(@RequestBody Modify<T> modify) {
        service.modify(modify.getApplicationId(), modify.getContent(), modify.getApplicantId());
        return CommonResponse.getSuccessResponse();
    }

    @RequestMapping(value = "/approval")
    @ApiOperation(value = "申请审批", httpMethod = "POST")
    public CommonResponse approval(@RequestBody Approval approval) {
        service.approval(approval.getPoints(), approval.getResult(), approval.getApproverId());
        return CommonResponse.getSuccessResponse();
    }

    // TODO: 待更新 web 的 Application 组件
    @RequestMapping(value = "/cancel")
    @ApiOperation(value = "取消申请", httpMethod = "POST")
    public CommonResponse<Boolean> cancel(@RequestBody String applicationId) {
        Boolean result = service.cancel(applicationId);
        return new CommonResponse<>(result);
    }
}
