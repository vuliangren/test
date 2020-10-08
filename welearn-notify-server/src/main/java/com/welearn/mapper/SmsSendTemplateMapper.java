package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.notify.SmsSendTemplate;
import com.welearn.entity.qo.notify.SmsSendTemplateQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SmsSendTemplate Mapper Interface : ryme_notify : sms_send_template
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/18 17:42:40
 * @see com.welearn.entity.po.notify.SmsSendTemplate
 */
@Mapper 
public interface SmsSendTemplateMapper extends BaseMapper<SmsSendTemplate, SmsSendTemplateQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "smsSendTemplateMapper", key = "'smsSendTemplate:'+#id", unless = "#result == null")
    SmsSendTemplate selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "smsSendTemplateMapper", key = "'smsSendTemplate:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(SmsSendTemplate entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "smsSendTemplateMapper", key = "'smsSendTemplate:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(SmsSendTemplate entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "smsSendTemplateMapper", key = "'smsSendTemplate:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "smsSendTemplateMapper", key = "'smsSendTemplate:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "smsSendTemplateMapper", key = "'smsSendTemplate:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "smsSendTemplateMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
