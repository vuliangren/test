package com.welearn.mapper;

import com.welearn.entity.po.common.Permission;
import com.welearn.entity.qo.common.PermissionQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Permission Mapper Interface : welearn-common : permission
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/1/6 23:08:28
 * @see Permission
 */
@Mapper public interface PermissionMapper extends BaseMapper<Permission, PermissionQueryCondition> {

    /**
     * 获取全部可用的权限信息
     * @return List<Permission>
     */
    List<Permission> selectAllEnabled();

    /**
     * 根据权限的编码查询权限
     * @param code 权限编码
     * @return 权限
     */
    Permission selectByCode(@Param("code") String code);

    /**
     * 根据服务名称和类型查询权限
     * @param service 服务名称
     * @return 权限列表
     */
    List<Permission> selectByService(@Param("service") String service);


}