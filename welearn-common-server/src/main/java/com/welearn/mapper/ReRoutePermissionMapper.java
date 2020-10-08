package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.common.ReRoutePermission;
import com.welearn.entity.qo.common.ReRoutePermissionQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ReRoutePermission Mapper Interface : ryme_common : re_route_permission
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/5 10:02:48
 * @see com.welearn.entity.po.common.ReRoutePermission
 */
@Mapper 
public interface ReRoutePermissionMapper extends BaseMapper<ReRoutePermission, ReRoutePermissionQueryCondition> {

    /**
     * 根据角色编码 查询与之关联的路由 所 关联权限编码
     * @param roleCode 角色编码
     * @param clientType 客户端类型
     * @return 权限编码
     */
    List<String> selectPermissionCodeByRoleCode(@Param("roleCode") String roleCode, @Param("clientType") Integer clientType);
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "reRoutePermissionMapper", key = "'reRoutePermission:'+#id", unless = "#result == null")
    ReRoutePermission selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reRoutePermissionMapper", key = "'reRoutePermission:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ReRoutePermission entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reRoutePermissionMapper", key = "'reRoutePermission:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ReRoutePermission entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRoutePermissionMapper", key = "'reRoutePermission:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRoutePermissionMapper", key = "'reRoutePermission:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRoutePermissionMapper", key = "'reRoutePermission:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRoutePermissionMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
