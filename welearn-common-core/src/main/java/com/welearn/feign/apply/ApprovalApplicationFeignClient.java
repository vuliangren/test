package com.welearn.feign.apply;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.qo.apply.ApprovalApplicationQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.apply.ApplicationShow;
import java.util.List;

/**
 * Description : welearn-apply-service / com.welearn.controller.ApprovalApplicationController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-apply-server", configuration = FeignConfiguration.class)
public interface ApprovalApplicationFeignClient {

    @RequestMapping(value = "/approvalApplication/selectUserApplicationByCondition")
    CommonResponse<List<ApplicationShow>> selectUserApplicationByCondition(ApprovalApplicationQueryCondition condition);

    @RequestMapping(value = "/approvalApplication/searchInfoByCondition")
    CommonResponse<List<ApplicationShow>> searchInfoByCondition(ApprovalApplicationQueryCondition condition);

    @RequestMapping(value = "/approvalApplication/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/approvalApplication/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/approvalApplication/update")
    CommonResponse update(ApprovalApplication entity);

    @RequestMapping(value = "/approvalApplication/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/approvalApplication/create")
    CommonResponse<ApprovalApplication> create(ApprovalApplication entity);

    @RequestMapping(value = "/approvalApplication/search")
    CommonResponse<List<ApprovalApplication>> search(ApprovalApplicationQueryCondition queryCondition);

    @RequestMapping(value = "/approvalApplication/select")
    CommonResponse<ApprovalApplication> select(String id);
}