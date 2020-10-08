package com.welearn.mapper;

import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.UserQueryCondition;
import com.welearn.entity.vo.response.common.ContactInfo;
import com.welearn.entity.vo.response.common.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User Mapper Interface : welearn-common : user
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/1/6 23:08:29
 * @see User
 */
@Mapper public interface UserMapper extends BaseMapper<User, UserQueryCondition> {

    /**
     * 根据条件查询用户信 
     *
     * @param condition 条件
     * @return 用户详细信息
     */
    List<UserInfo> searchInfo(UserQueryCondition condition);

    /**
     * 查询部门用户的联系方式等基本信息
     * @param companyId 公司id
     * @param departmentId 部门id
     * @return 用户基本信息
     */
    List<ContactInfo> contacts(@Param("companyId") String companyId, @Param("departmentId") String departmentId);
}