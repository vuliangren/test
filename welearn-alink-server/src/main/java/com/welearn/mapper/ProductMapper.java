package com.welearn.mapper;

import com.welearn.entity.vo.response.alink.StatisticConfigJsonStr;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.alink.Product;
import com.welearn.entity.qo.alink.ProductQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Product Mapper Interface : ryme_alink : product
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/6/1 11:38:45
 * @see com.welearn.entity.po.alink.Product
 */
@Mapper 
public interface ProductMapper extends BaseMapper<Product, ProductQueryCondition> {





    /**
     * 获取所有设备的统计分析相关配置信息
     * @return 设备iot 与 对应配置JSON字符串
     */
    List<StatisticConfigJsonStr> getStatisticConfigJsonStr();
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "productMapper", key = "'product:'+#id", unless = "#result == null")
    Product selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "productMapper", key = "'product:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(Product entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "productMapper", key = "'product:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(Product entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "productMapper", key = "'product:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "productMapper", key = "'product:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "productMapper", key = "'product:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "productMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
