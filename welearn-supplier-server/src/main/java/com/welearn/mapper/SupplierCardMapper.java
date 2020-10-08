package com.welearn.mapper;

import com.welearn.dictionary.api.ServiceConst;
import com.welearn.generator.ControllerGenerator;
import com.welearn.service.SupplierCardService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.supplier.SupplierCard;
import com.welearn.entity.qo.supplier.SupplierCardQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * SupplierCard Mapper Interface : ryme_supplier : supplier_card
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/8 18:20:24
 * @see com.welearn.entity.po.supplier.SupplierCard
 */
@Mapper 
public interface SupplierCardMapper extends BaseMapper<SupplierCard, SupplierCardQueryCondition> {


    
    
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "supplierCardMapper", key = "'supplierCard:'+#id", unless = "#result == null")
    SupplierCard selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "supplierCardMapper", key = "'supplierCard:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(SupplierCard entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "supplierCardMapper", key = "'supplierCard:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(SupplierCard entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "supplierCardMapper", key = "'supplierCard:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "supplierCardMapper", key = "'supplierCard:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "supplierCardMapper", key = "'supplierCard:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "supplierCardMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
