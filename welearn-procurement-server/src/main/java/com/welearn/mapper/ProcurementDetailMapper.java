package com.welearn.mapper;

import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.procurement.ProcurementDetailQueryCondition;
import com.welearn.entity.vo.response.procurement.ProcurementDetailInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ProcurementDetail Mapper Interface : ryme_procurement : procurement_detail
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/10/29 15:36:46
 * @see com.welearn.entity.po.procurement.ProcurementDetail
 */
@Mapper 
public interface ProcurementDetailMapper extends BaseMapper<ProcurementDetail, ProcurementDetailQueryCondition> {
    /**
     * 根据 采购项目id 和 isEnable 查询
     * @param procurementId 采购项目id
     * @param isEnable isEnable
     * @return List<ProcurementDetailInfo>
     */
    List<ProcurementDetailInfo> selectByProcurementIdAndIsEnable(@Param("procurementId") String procurementId, @Param("isEnable") Boolean isEnable);
}