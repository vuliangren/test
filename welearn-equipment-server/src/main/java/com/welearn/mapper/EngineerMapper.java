package com.welearn.mapper;

import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.qo.equipment.EngineerQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Engineer Mapper Interface : ryme_equipment : engineer
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/1/8 17:20:14
 * @see com.welearn.entity.po.equipment.Engineer
 */
@Mapper 
public interface EngineerMapper extends BaseMapper<Engineer, EngineerQueryCondition> {

    /**
     * 根据公司id 查询工程师信息
     * @param serveCompanyId 服务公司id
     * @return 统计分析用数据
     */
    List<Engineer> engineerStatistic(@Param("serveCompanyId") String serveCompanyId);
}