package com.welearn.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.finance.FixedAssets;
import com.welearn.entity.qo.finance.FixedAssetsQueryCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * FixedAssets Mapper Interface : ryme_finance : fixed_assets
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/3/14 17:43:06
 * @see com.welearn.entity.po.finance.FixedAssets
 */
@Mapper 
public interface FixedAssetsMapper extends BaseMapper<FixedAssets, FixedAssetsQueryCondition> {


    /**
     * 设备类型固定资产统计
     * @param companyId 公司id
     * @return 统计用数据
     */
    List<FixedAssets> equipmentValueStatistic(String companyId);
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "fixedAssetsMapper", key = "'fixedAssets:'+#id", unless = "#result == null")
    FixedAssets selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "fixedAssetsMapper", key = "'fixedAssets:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(FixedAssets entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "fixedAssetsMapper", key = "'fixedAssets:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(FixedAssets entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "fixedAssetsMapper", key = "'fixedAssets:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "fixedAssetsMapper", key = "'fixedAssets:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "fixedAssetsMapper", key = "'fixedAssets:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "fixedAssetsMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
