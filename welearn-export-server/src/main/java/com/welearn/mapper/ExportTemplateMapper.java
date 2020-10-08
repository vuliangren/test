package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.export.ExportTemplate;
import com.welearn.entity.qo.export.ExportTemplateQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * ExportTemplate Mapper Interface : ryme_export : export_template
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/11 11:39:04
 * @see com.welearn.entity.po.export.ExportTemplate
 */
@Mapper 
public interface ExportTemplateMapper extends BaseMapper<ExportTemplate, ExportTemplateQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "exportTemplateMapper", key = "'exportTemplate:'+#id", unless = "#result == null")
    ExportTemplate selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "exportTemplateMapper", key = "'exportTemplate:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ExportTemplate entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "exportTemplateMapper", key = "'exportTemplate:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ExportTemplate entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "exportTemplateMapper", key = "'exportTemplate:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "exportTemplateMapper", key = "'exportTemplate:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "exportTemplateMapper", key = "'exportTemplate:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "exportTemplateMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
