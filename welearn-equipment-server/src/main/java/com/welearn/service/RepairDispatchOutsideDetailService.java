package com.welearn.service;

import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.po.equipment.RepairDispatchOutsideDetail;
import com.welearn.entity.qo.equipment.RepairDispatchOutsideDetailQueryCondition;
import com.welearn.entity.vo.response.equipment.RepairDispatchOutsideDetailInfo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Description : RepairDispatchOutsideDetailService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface RepairDispatchOutsideDetailService extends BaseService<RepairDispatchOutsideDetail, RepairDispatchOutsideDetailQueryCondition>{

    /**
     * 查询外部派工关联的工程师信息
     * @param dispatchOutsideId 外部派工id
     * @return 工程师信息
     */
    List<RepairDispatchOutsideDetailInfo> searchDispatchOutsideEngineers(String dispatchOutsideId);
}