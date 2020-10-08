package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.po.common.SupplierRegister;
import com.welearn.entity.qo.common.SupplierRegisterQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Description : welearn-common-service / com.welearn.controller.SupplierRegisterController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface SupplierRegisterFeignClient {

    @RequestMapping(value = "/supplierRegister/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/supplierRegister/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/supplierRegister/update")
    CommonResponse update(SupplierRegister entity);

    @RequestMapping(value = "/supplierRegister/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/supplierRegister/create")
    CommonResponse<SupplierRegister> create(SupplierRegister entity);

    @RequestMapping(value = "/supplierRegister/search")
    CommonResponse<List<SupplierRegister>> search(SupplierRegisterQueryCondition queryCondition);

    @RequestMapping(value = "/supplierRegister/select")
    CommonResponse<SupplierRegister> select(String id);
}