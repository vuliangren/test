package com.welearn.mapper;

import com.welearn.entity.po.common.ReUserRole;
import com.welearn.entity.qo.common.ReUserRoleQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ReUserRole Mapper Interface : welearn-common : re_user_role
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/1/6 23:08:28
 * @see ReUserRole
 */
@Mapper public interface ReUserRoleMapper extends BaseMapper<ReUserRole, ReUserRoleQueryCondition> {

    /**
     * 根据 userId 和 roleId 获取关联数量
     * @param userId 用户id
     * @param roleId 角色id
     * @return 关联数量
     */
    ReUserRole selectByUserIdAndRoleId(
            @Param("userId") String userId,
            @Param("roleId") String roleId);

    List<ReUserRole> selectByUserId(@Param("userId") String userId);
}