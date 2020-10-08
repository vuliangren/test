package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.export.ExportTask;
import com.welearn.entity.qo.export.ExportTaskQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * ExportTask Mapper Interface : ryme_export : export_task
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/11 11:39:02
 * @see com.welearn.entity.po.export.ExportTask
 */
@Mapper 
public interface ExportTaskMapper extends BaseMapper<ExportTask, ExportTaskQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "exportTaskMapper", key = "'exportTask:'+#id", unless = "#result == null")
    ExportTask selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "exportTaskMapper", key = "'exportTask:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(ExportTask entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "exportTaskMapper", key = "'exportTask:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(ExportTask entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "exportTaskMapper", key = "'exportTask:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "exportTaskMapper", key = "'exportTask:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "exportTaskMapper", key = "'exportTask:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "exportTaskMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
