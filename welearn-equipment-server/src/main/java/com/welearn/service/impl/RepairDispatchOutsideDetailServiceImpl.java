package com.welearn.service.impl;

import com.welearn.entity.po.equipment.Engineer;
import com.welearn.entity.po.equipment.RepairDispatchOutsideDetail;
import com.welearn.entity.qo.equipment.RepairDispatchOutsideDetailQueryCondition;
import com.welearn.entity.vo.response.equipment.RepairDispatchOutsideDetailInfo;
import com.welearn.mapper.RepairDispatchOutsideDetailMapper;
import com.welearn.service.RepairDispatchOutsideDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : RepairDispatchOutsideDetailService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class RepairDispatchOutsideDetailServiceImpl extends BaseServiceImpl<RepairDispatchOutsideDetail,RepairDispatchOutsideDetailQueryCondition,RepairDispatchOutsideDetailMapper>
        implements RepairDispatchOutsideDetailService{
    
    @Autowired
    private RepairDispatchOutsideDetailMapper repairDispatchOutsideDetailMapper;
    
    @Override
    RepairDispatchOutsideDetailMapper getMapper() {
        return repairDispatchOutsideDetailMapper;
    }

    /**
     * 查询外部派工关联的工程师信息
     *
     * @param dispatchOutsideId 外部派工id
     * @return 工程师信息
     */
    @Override
    public List<RepairDispatchOutsideDetailInfo> searchDispatchOutsideEngineers(String dispatchOutsideId) {
        return repairDispatchOutsideDetailMapper.searchDispatchOutsideEngineers(dispatchOutsideId);
    }
}
