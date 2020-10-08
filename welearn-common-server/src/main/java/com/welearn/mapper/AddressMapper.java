package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.common.Address;
import com.welearn.entity.qo.common.AddressQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * Address Mapper Interface : ryme_common : address
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/16 18:24:17
 * @see Address
 */
@Mapper 
public interface AddressMapper extends BaseMapper<Address, AddressQueryCondition> {
    
    
    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "addressMapper", key = "'address:'+#id", unless = "#result == null")
    Address selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "addressMapper", key = "'address:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(Address entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "addressMapper", key = "'address:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(Address entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "addressMapper", key = "'address:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "addressMapper", key = "'address:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "addressMapper", key = "'address:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "addressMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
