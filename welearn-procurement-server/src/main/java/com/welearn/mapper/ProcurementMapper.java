package com.welearn.mapper;

import com.welearn.entity.po.procurement.Procurement;
import com.welearn.entity.qo.procurement.ProcurementQueryCondition;
import com.welearn.entity.vo.response.procurement.ProcurementInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Procurement Mapper Interface : ryme_procurement : procurement
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/29 15:36:46
 * @see com.welearn.entity.po.procurement.Procurement
 */
@Mapper 
public interface ProcurementMapper extends BaseMapper<Procurement, ProcurementQueryCondition> {

    /**
     * 根据 查询条件 查询 采购项目详情
     * @param condition 查询条件
     * @return 采购项目详情列表
     */
    List<ProcurementInfo> searchInfo(ProcurementQueryCondition condition);
}