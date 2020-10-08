package com.welearn.feign.common;

import com.welearn.config.FeignConfiguration;
import com.welearn.entity.vo.response.common.DepartmentInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.common.ReUserDepartment;
import com.welearn.entity.qo.common.DepartmentQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;

import java.util.List;

/**
 * Description : welearn-common-service / com.welearn.controller.DepartmentController
 * Feign Client Created by FeignClientGenerator
 */
@Component
@FeignClient(value = "welearn-common-server", configuration = FeignConfiguration.class)
public interface DepartmentFeignClient {

    @RequestMapping(value = "/department/position")
    CommonResponse<String> position(String departmentId);

    @RequestMapping(value = "/department/searchInfo")
    CommonResponse<List<DepartmentInfo>> searchInfo(DepartmentQueryCondition condition);

    @RequestMapping(value = "/department/userDepartments")
    CommonResponse<List<Department>> userDepartments(String string);

    @RequestMapping(value = "/department/createAndInitPosition")
    CommonResponse<Department> createAndInitPosition(Department department);

    @RequestMapping(value = "/department/disable")
    CommonResponse disable(String id);

    @RequestMapping(value = "/department/enable")
    CommonResponse enable(String id);

    @RequestMapping(value = "/department/update")
    CommonResponse update(Department entity);

    @RequestMapping(value = "/department/delete")
    CommonResponse delete(String id);

    @RequestMapping(value = "/department/create")
    CommonResponse<Department> create(Department entity);

    @RequestMapping(value = "/department/search")
    CommonResponse<List<Department>> search(DepartmentQueryCondition queryCondition);

    @RequestMapping(value = "/department/select")
    CommonResponse<Department> select(String id);
}