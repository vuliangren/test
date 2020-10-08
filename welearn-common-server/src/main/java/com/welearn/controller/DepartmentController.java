package com.welearn.controller;

import com.welearn.annotation.RequestUser;
import com.welearn.entity.vo.response.common.DepartmentUserCount;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.DepartmentQueryCondition;
import com.welearn.entity.vo.response.CommonResponse;
import com.welearn.entity.vo.response.common.DepartmentInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.service.DepartmentService;
import java.lang.String;
import java.util.List;

/**
 * Description :
 * Created by Setsuna Jin on 2018/1/3.
 */
@Slf4j
@RestController
@Validated
@RequestMapping(value = "/department")
public class DepartmentController extends BaseController<Department, DepartmentQueryCondition, DepartmentService>{

    @RequestMapping(value = "/createAndInitPosition")
    @ApiOperation(value = "createAndInitPosition", httpMethod = "POST")
    public CommonResponse<Department> createAndInitPosition(@RequestBody Department department, @RequestUser User user) throws BusinessVerifyFailedException {
        Department result = service.createAndInitPosition(department, user);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/userDepartments")
    @ApiOperation(value = "用户关联的所有部门", httpMethod = "POST")
    public CommonResponse<List<Department>> userDepartments(@RequestBody String string)  {
        List<Department> result = service.userDepartments(string);
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/searchInfo")
    @ApiOperation(value = "searchInfo", httpMethod = "POST")
    public CommonResponse<List<DepartmentInfo>> searchInfo(@RequestBody DepartmentQueryCondition condition)  {
        List<DepartmentInfo> result = service.searchInfo(condition.getCompanyId(), condition.getName(), condition.getTagIds());
        return new CommonResponse<>(result);
    }

    @RequestMapping(value = "/position")
    @ApiOperation(value = "获取部门的详细位置信息", httpMethod = "POST")
    public CommonResponse<String> position(@RequestBody String departmentId)  {
        return new CommonResponse<>(service.position(departmentId));
    }

    @RequestMapping(value = "/userCount")
    @ApiOperation(value = "获取公司所有部门人员数量", httpMethod = "POST")
    public CommonResponse<List<DepartmentUserCount>> userCount(@RequestBody String companyId)  {
        List<DepartmentUserCount> result = service.userCount(companyId);
        return new CommonResponse<>(result);
    }
}