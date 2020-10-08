package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.notify.SmsSendRecord;
import com.welearn.entity.qo.notify.SmsSendRecordQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SmsSendRecord Mapper Interface : ryme_notify : sms_send_record
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/18 17:42:38
 * @see com.welearn.entity.po.notify.SmsSendRecord
 */
@Mapper 
public interface SmsSendRecordMapper extends BaseMapper<SmsSendRecord, SmsSendRecordQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "smsSendRecordMapper", key = "'smsSendRecord:'+#id", unless = "#result == null")
    SmsSendRecord selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "smsSendRecordMapper", key = "'smsSendRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(SmsSendRecord entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "smsSendRecordMapper", key = "'smsSendRecord:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(SmsSendRecord entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "smsSendRecordMapper", key = "'smsSendRecord:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "smsSendRecordMapper", key = "'smsSendRecord:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "smsSendRecordMapper", key = "'smsSendRecord:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "smsSendRecordMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
