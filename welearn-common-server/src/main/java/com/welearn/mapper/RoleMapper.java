package com.welearn.mapper;

import com.welearn.entity.po.common.Role;
import com.welearn.entity.qo.common.RoleQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Role Mapper Interface : welearn-common : role
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/1/6 23:08:28
 * @see Role
 */
@Mapper public interface RoleMapper extends BaseMapper<Role, RoleQueryCondition> {

    List<Role> selectByUserId(@Param("userId") String userId);

    /**
     * 根据角色的 code 获取 角色
     * @param code 角色编码
     * @return 角色
     */
    Role selectByCode(@Param("code") String code);
}