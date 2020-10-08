package com.welearn.feign.apply;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.apply.Contract;
import com.welearn.entity.qo.apply.ContractQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-apply-service / com.welearn.controller.ContractController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-apply-server", configuration = FeignConfiguration.class)
public interface ContractFeignClient {

    @RequestMapping(value = "/contract/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/contract/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/contract/update")
    CommonResponse update(Contract entity);

    @RequestMapping(value = "/contract/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/contract/create")
    CommonResponse<Contract> create(Contract entity);

    @RequestMapping(value = "/contract/search")
    CommonResponse<List<Contract>> search(ContractQueryCondition queryCondition);

    @RequestMapping(value = "/contract/select")
    CommonResponse<Contract> select(String id);
}