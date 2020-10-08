package com.welearn.service;

import com.welearn.entity.po.common.Position;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.PositionQueryCondition;
import com.welearn.entity.vo.response.common.PositionInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : Service Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface PositionService extends BaseService<Position, PositionQueryCondition>{

    /**
     * 公司职位信息查询
     * @param companyType 公司类型
     * @param companyId 公司id
     * @return 职位及关联用户信息
     */
    List<PositionInfo> companyPositionInfo(@NotNull Integer companyType, @NotBlank String companyId);

    /**
     * 查找 企业 的 某个职位 的人
     * 查找 企业 某个部门 的 某个职位 的人
     * 查找 企业 特殊职位 的人
     * @param companyId 公司id 必填
     * @param departmentId 部门id 选填
     * @param positionId 职位id 和 code 二选一
     * @param code code 和 职位id 二选一
     * @return 用户列表
     */
    List<User> userSearch(@NotBlank String companyId, String departmentId, String positionId, String code);

    /**
     * 查找可用的职位
     * @param visitorCompanyType 公司类型
     * @param companyId 公司id
     * @param departmentId 部门id
     * @return 职位列表
     */
    List<Position> available(Integer visitorCompanyType, String companyId, String departmentId);

    /**
     * 获取用户的职位列表
     * @param userId 用户id
     * @return 职位列表
     */
    List<Position> userPositions(@NotBlank String userId);

    /**
     * 给员工分配职位
     * @param userId 员工id
     * @param departmentId 部门id
     * @param positionId 职位id
     * @param operator 操作人id
     */
    void allot(@NotBlank String userId, @NotBlank String departmentId, @NotBlank String positionId, @EntityCheck(checkId = true) User operator) throws BusinessVerifyFailedException;

    /**
     * 给员工撤销职位
     * @param userId 员工id
     * @param departmentId 部门id
     * @param positionId 职位id
     * @param operator 操作人id
     */
    void takeBack(@NotBlank String userId, @NotBlank String departmentId, @NotBlank String positionId, @EntityCheck(checkId = true) User operator) throws BusinessVerifyFailedException;

    /**
     * 给员工撤销所有职位
     * @param userId 员工id
     * @param companyId 公司id
     * @param operator 操作人id
     */
    void takeBackCompany(@NotBlank String userId, @NotBlank String companyId, @EntityCheck(checkId = true) User operator);

    /**
     * 给员工撤销所有部门职位
     * @param userId 员工id
     * @param departmentId 部门id
     * @param operator 操作人id
     */
    void takeBackDepartment(@NotBlank String userId, @NotBlank String departmentId, @EntityCheck(checkId = true) User operator);

}