package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.common.LinkCode;
import com.welearn.entity.qo.common.LinkCodeQueryCondition;
import com.welearn.entity.vo.request.common.BatchBindingIdTypeCreate;
import com.welearn.entity.vo.request.common.BatchUpdateCompanyId;
import com.welearn.entity.vo.request.common.Binding;
import com.welearn.entity.vo.response.CommonResponse;
import java.lang.String;
import java.util.List;

/**
 * Description : welearn-common-service / com.welearn.controller.LinkCodeController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface LinkCodeFeignClient {

    @RequestMapping(value = "/linkCode/batchUpdateCompanyId")
    CommonResponse batchUpdateCompanyId(BatchUpdateCompanyId arg0);

    @RequestMapping(value = "/linkCode/selectBySerialNumber")
    CommonResponse<LinkCode> selectBySerialNumber(String arg0);

    @RequestMapping(value = "/linkCode/batchBindingIdTypeCreate")
    CommonResponse batchBindingIdTypeCreate(BatchBindingIdTypeCreate arg0);

    @RequestMapping(value = "/linkCode/binding")
    CommonResponse binding(Binding arg0);

    @RequestMapping(value = "/linkCode/unBinding")
    CommonResponse unBinding(String arg0);

    @RequestMapping(value = "/linkCode/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/linkCode/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/linkCode/update")
    CommonResponse update(LinkCode entity);

    @RequestMapping(value = "/linkCode/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/linkCode/create")
    CommonResponse<LinkCode> create(LinkCode entity);

    @RequestMapping(value = "/linkCode/search")
    CommonResponse<List<LinkCode>> search(LinkCodeQueryCondition queryCondition);

    @RequestMapping(value = "/linkCode/select")
    CommonResponse<LinkCode> select(String id);
}