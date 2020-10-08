package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.dto.common.RouteRole;
import com.welearn.entity.po.common.ReRouteRole;
import com.welearn.entity.qo.common.ReRouteRoleQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ReRouteRole Mapper Interface : ryme_common : re_route_role
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/26 21:23:40
 * @see com.welearn.entity.po.common.ReRouteRole
 *
 * 去除默认缓存, 改为更新后清理 antdRouteInfos 缓存
 */
@Mapper 
public interface ReRouteRoleMapper extends BaseMapper<ReRouteRole, ReRouteRoleQueryCondition> {

    /**
     * 获取所有的去重后的 path 和 role 数据
     * @return List<RouteRole>
     */
    List<RouteRole> selectAllDistinctPathAndRole(@Param("clientType") Integer clientType);

    // --------------------------------------------------------------------------------------------
    @Override
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    int insert(ReRouteRole entity);

    @Override
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    int insertSelective(ReRouteRole entity);

    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override 
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    // @Cacheable(value = "reRouteRoleMapper", key = "'reRouteRole:'+#id", unless = "#result == null")
    ReRouteRole selectByPK(String id);

    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    // @CachePut(value = "reRouteRoleMapper", key = "'reRouteRole:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ReRouteRole entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    // @CachePut(value = "reRouteRoleMapper", key = "'reRouteRole:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ReRouteRole entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    // @CacheEvict(value = "reRouteRoleMapper", key = "'reRouteRole:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    // @CacheEvict(value = "reRouteRoleMapper", key = "'reRouteRole:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    // @CacheEvict(value = "reRouteRoleMapper", key = "'reRouteRole:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    @CacheEvict(value = "antdRouteInfos", key = "'antdRouteInfos'")
    // @CacheEvict(value = "reRouteRoleMapper", allEntries = true)
    int deleteAll();

    // --------------------------------------------------------------------------------------------
}