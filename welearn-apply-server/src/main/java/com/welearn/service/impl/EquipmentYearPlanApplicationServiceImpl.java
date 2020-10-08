package com.welearn.service.impl;

import com.welearn.dictionary.apply.EquipmentYearPlanApplicationStatusConst;
import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import com.welearn.entity.qo.apply.EquipmentYearPlanApplicationQueryCondition;
import com.welearn.entity.vo.response.apply.EquipmentYearPlanApplicationInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.EquipmentYearPlanApplicationMapper;
import com.welearn.service.EquipmentYearPlanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.apply.EquipmentYearPlanApplicationStatusConst.*;

/**
 * Description : EquipmentYearPlanApplicationService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentYearPlanApplicationServiceImpl extends BaseServiceImpl<EquipmentYearPlanApplication,EquipmentYearPlanApplicationQueryCondition,EquipmentYearPlanApplicationMapper>
        implements EquipmentYearPlanApplicationService{
    
    @Autowired
    private EquipmentYearPlanApplicationMapper equipmentYearPlanApplicationMapper;
    
    @Override
    EquipmentYearPlanApplicationMapper getMapper() {
        return equipmentYearPlanApplicationMapper;
    }

    /**
     * 根据 查询条件 查询 设备计划申请信息
     *
     * @param condition 查询条件
     * @return List<EquipmentYearPlanApplicationInfo>
     */
    @Override
    public List<EquipmentYearPlanApplicationInfo> searchInfo(EquipmentYearPlanApplicationQueryCondition condition) {
        return equipmentYearPlanApplicationMapper.searchInfo(condition);
    }

    /**
     * 装备委员会评审 设备计划申请
     *
     * @param equipmentYearPlanApplicationId 设备计划申请id
     * @param isPassed                       是否通过
     * @param resultJson                     未通过原因 委员会通过与否决人数 等 Json信息
     */
    @Override
    public void committeeApproval(String equipmentYearPlanApplicationId, Boolean isPassed, String resultJson) {
        EquipmentYearPlanApplication application = this.select(equipmentYearPlanApplicationId);
        if (Objects.isNull(application))
            throw new BusinessVerifyFailedException("equipmentYearPlanApplicationId 非法");
        application.setStatus(isPassed ? COMMITTEE_PASS.ordinal() : COMMITTEE_FAILED.ordinal());
        application.setCommitteeApprovalResultJson(resultJson);
        this.update(application);
    }

    /**
     * 院长评审 设备计划申请
     *
     * @param equipmentYearPlanApplicationId 设备计划申请id
     * @param isPassed                       是否通过
     * @param resultJson                     未通过原因 Json信息
     */
    @Override
    public void directorApproval(String equipmentYearPlanApplicationId, Boolean isPassed, String resultJson) {
        EquipmentYearPlanApplication application = this.select(equipmentYearPlanApplicationId);
        if (Objects.isNull(application))
            throw new BusinessVerifyFailedException("equipmentYearPlanApplicationId 非法");
        application.setStatus(isPassed ? DIRECTOR_PASS.ordinal() : DIRECTOR_FAILED.ordinal());
        application.setDirectorApprovalResultJson(resultJson);
        this.update(application);
    }
}
