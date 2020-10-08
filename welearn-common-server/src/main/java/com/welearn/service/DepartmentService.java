package com.welearn.service;

import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.common.ReUserDepartment;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.DepartmentQueryCondition;
import com.welearn.entity.vo.response.common.DepartmentInfo;
import com.welearn.entity.vo.response.common.DepartmentUserCount;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Description : Service Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface DepartmentService extends BaseService<Department, DepartmentQueryCondition>{

    /**
     * 创建部门并初始化其默认职位
     * 部门主管 / 部门成员
     * @param department 部门信息
     * @return 带Id的部门信息
     */
    Department createAndInitPosition(@EntityCheck @Valid Department department, @EntityCheck(checkId = true) User user) throws BusinessVerifyFailedException;

    /**
     * 分页获取部门信息
     *
     * @param companyId 公司id
     * @param departmentName 部门名称模糊查询
     * @param tagIds 相关标签
     * @return 部门信息列表
     */
    List<DepartmentInfo> searchInfo(String companyId, String departmentName, List<String> tagIds);

    /**
     * 获取部门的详细位置
     * @param departmentId 部门id
     * @return 部门详细位置
     */
    @Deprecated
    String position(@NotBlank String departmentId);

    /**
     * 用户所在的部门列表
     * @param userId 用户id
     * @return 部门列表
     */
    List<Department> userDepartments(@NotBlank String userId);

    /**
     * 统计各个部门的人数信息
     * @param companyId 公司id
     * @return List<DepartmentUserCount>
     */
    List<DepartmentUserCount> userCount(@NotBlank String companyId);
}