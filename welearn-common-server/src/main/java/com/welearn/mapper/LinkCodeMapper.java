package com.welearn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.welearn.entity.po.common.LinkCode;
import com.welearn.entity.qo.common.LinkCodeQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * LinkCode Mapper Interface : ryme_common : link_code
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/4/26 9:05:40
 * @see com.welearn.entity.po.common.LinkCode
 */
@Mapper 
public interface LinkCodeMapper extends BaseMapper<LinkCode, LinkCodeQueryCondition> {

    LinkCode selectBySerialNumber(@Param("serialNumber") String serialNumber);

    LinkCode selectByNumber(@Param("number") Long number);

    /**
     * 批量授权 关联编码到企业
     * @param startNumber 起始编号(>=)
     * @param endNumber 结束编号(<=)
     * @param companyId 授权企业id
     */
    void batchUpdateCompanyId(@Param("startNumber") Long startNumber,
                              @Param("endNumber") Long endNumber,
                              @Param("companyId") String companyId);
    
    
    
    
    // --------------------------------------------------------------------------------------------
    
    /**
     * 按主键查询数据
     * @param  id entity Id
     * @return 实体 对象
     */
    @Override
    // @Cacheable(value = "linkCodeMapper", key = "'linkCode:'+#id", unless = "#result == null")
    LinkCode selectByPK(String id);
    
    /**
     * 更新数据（全部）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "linkCodeMapper", key = "'linkCode:'+#entity.id", unless = "#entity.id == null")
    int updateByPK(LinkCode entity);

    /**
     * 更新数据（选择）
     * @param  entity 实体
     * @return 操作影响行数
     */
    @Override
    // @CachePut(value = "linkCodeMapper", key = "'linkCode:'+#entity.id", unless = "#entity.id == null")
    int updateByPKSelective(LinkCode entity);

    /**
     * 标记数据可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "linkCodeMapper", key = "'linkCode:'+#id")
    int enable(String id);

    /**
     * 标记数据不可用
     * @param id 主键
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "linkCodeMapper", key = "'linkCode:'+#id")
    int disable(String id);

    /**
     * 根据主键删除
     * @param  id entityId
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "linkCodeMapper", key = "'linkCode:'+#id")
    int deleteByPK(String id);

    /**
     * 删除全部数据
     * @return 操作影响行数
     */
    @Override
    // @CacheEvict(value = "linkCodeMapper", allEntries = true)
    int deleteAll();
    
    // --------------------------------------------------------------------------------------------
}
