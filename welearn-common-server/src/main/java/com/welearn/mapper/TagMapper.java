package com.welearn.mapper;

import com.welearn.entity.vo.response.common.TagInfo;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.common.Tag;
import com.welearn.entity.qo.common.TagQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Tag Mapper Interface : ryme_common : tag
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/16 18:24:41
 * @see Tag
 */
@Mapper 
public interface TagMapper extends BaseMapper<Tag, TagQueryCondition> {

    /**
     * 查询数据拥有的标签
     * @param itemId 数据id
     * @return 标签
     */
    List<TagInfo> itemTags(@Param("itemId") String itemId);


    /**
     * 获取标签关联的 数据id 列表
     * 查询结果为 每个 tagId 关联的 itemId 的 去重 并集
     * @param tagIds 标签id 列表
     * @return 数据 id 列表
     */
    List<String> itemIds(@Param("tagIds") List<String> tagIds);
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "tagMapper", key = "'tag:'+#id", unless = "#result == null")
    Tag selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "tagMapper", key = "'tag:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(Tag entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "tagMapper", key = "'tag:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(Tag entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "tagMapper", key = "'tag:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "tagMapper", key = "'tag:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "tagMapper", key = "'tag:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "tagMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
