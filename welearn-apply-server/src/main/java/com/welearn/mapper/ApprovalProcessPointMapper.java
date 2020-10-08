package com.welearn.mapper;

import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.qo.apply.ApprovalProcessPointQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ApprovalProcessPoint Mapper Interface : ryme_apply : approval_process_point
 * @author Setsuna Jin Generate By CodeSmith 7.0 At 2018/9/18 17:12:07
 * @see ApprovalProcessPoint
 */
@Mapper 
public interface ApprovalProcessPointMapper extends BaseMapper<ApprovalProcessPoint, ApprovalProcessPointQueryCondition> {
    /**
     * 获取申请的审批节点数量
     * @param companyId 公司id
     * @param code 申请的编码
     * @return 节点数量
     */
    Integer countByCompanyAndProcessCode(@Param("companyId") String companyId, @Param("code") String code);
}