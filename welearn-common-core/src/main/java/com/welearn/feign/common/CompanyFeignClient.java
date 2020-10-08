package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.qo.common.CompanyQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.common.LocationInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-common-service / com.welearn.controller.CompanyController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface CompanyFeignClient {

    @RequestMapping(value = "/company/searchLocationInfo")
    CommonResponse<LocationInfo> searchLocationInfo(String locationId);

    @RequestMapping(value = "/company/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/company/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/company/update")
    CommonResponse update(Company entity);

    @RequestMapping(value = "/company/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/company/create")
    CommonResponse create(Company entity);

    @RequestMapping(value = "/company/search")
    CommonResponse<List<Company>> search(CompanyQueryCondition queryCondition);

    @RequestMapping(value = "/company/select")
    CommonResponse<Company> select(String id);
}