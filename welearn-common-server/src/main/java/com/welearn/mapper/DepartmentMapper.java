package com.welearn.mapper;

import com.welearn.entity.po.common.Department;
import com.welearn.entity.qo.common.DepartmentQueryCondition;
import com.welearn.entity.vo.response.common.DepartmentInfo;
import com.welearn.entity.vo.response.common.DepartmentUserCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Department Mapper Interface : ryme_common : department
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/2 19:02:50
 * @see com.welearn.entity.po.common.Department
 */
@Mapper 
public interface DepartmentMapper extends BaseMapper<Department, DepartmentQueryCondition> {

    /**
     * 分页获取部门信息
     * @param companyId 公司id
     * @return 部门信息列表
     */
    List<DepartmentInfo> searchInfo(@Param("companyId") String companyId, @Param("departmentName") String departmentName, @Param("tagIds") List<String> tagIds);

    /**
     * 用户所在的部门列表
     * @param userId 用户id
     * @return 部门列表 
     */
    List<Department> userDepartments(@Param("userId") String userId);


    /**
     * 获取部门用户数量
     * @param companyId 公司id
     * @return 公司每个部门的用户数量
     */
    List<DepartmentUserCount> userCount(@Param("companyId") String companyId);
}