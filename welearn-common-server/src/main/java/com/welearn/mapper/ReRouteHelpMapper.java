package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.common.ReRouteHelp;
import com.welearn.entity.qo.common.ReRouteHelpQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * ReRouteHelp Mapper Interface : ryme_common : re_route_help
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/4 16:18:22
 * @see com.welearn.entity.po.common.ReRouteHelp
 */
@Mapper 
public interface ReRouteHelpMapper extends BaseMapper<ReRouteHelp, ReRouteHelpQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "reRouteHelpMapper", key = "'reRouteHelp:'+#id", unless = "#result == null")
    ReRouteHelp selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reRouteHelpMapper", key = "'reRouteHelp:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ReRouteHelp entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reRouteHelpMapper", key = "'reRouteHelp:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ReRouteHelp entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRouteHelpMapper", key = "'reRouteHelp:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRouteHelpMapper", key = "'reRouteHelp:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRouteHelpMapper", key = "'reRouteHelp:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reRouteHelpMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
