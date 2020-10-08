package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.common.ReTagItem;
import com.welearn.entity.qo.common.ReTagItemQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * ReTagItem Mapper Interface : ryme_common : re_tag_item
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/5/6 9:49:15
 * @see com.welearn.entity.po.common.ReTagItem
 */
@Mapper 
public interface ReTagItemMapper extends BaseMapper<ReTagItem, ReTagItemQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "reTagItemMapper", key = "'reTagItem:'+#id", unless = "#result == null")
    ReTagItem selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reTagItemMapper", key = "'reTagItem:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ReTagItem entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "reTagItemMapper", key = "'reTagItem:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ReTagItem entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reTagItemMapper", key = "'reTagItem:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reTagItemMapper", key = "'reTagItem:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reTagItemMapper", key = "'reTagItem:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "reTagItemMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
