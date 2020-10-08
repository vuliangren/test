package com.welearn.mapper;

import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.po.equipment.RepairDispatchOutsideDetail;
import com.welearn.entity.qo.equipment.RepairDispatchOutsideDetailQueryCondition;
import com.welearn.entity.vo.response.equipment.RepairDispatchOutsideDetailInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RepairDispatchOutsideDetail Mapper Interface : ryme_equipment : repair_dispatch_outside_detail
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2019/2/26 15:23:31
 * @see RepairDispatchOutsideDetail
 */
@Mapper 
public interface RepairDispatchOutsideDetailMapper extends BaseMapper<RepairDispatchOutsideDetail, RepairDispatchOutsideDetailQueryCondition> {
    /**
     * 查询外部派工关联的工程师信息
     * @param dispatchOutsideId 外部派工id
     * @return 工程师信息
     */
    List<RepairDispatchOutsideDetailInfo> searchDispatchOutsideEngineers(String dispatchOutsideId);
}