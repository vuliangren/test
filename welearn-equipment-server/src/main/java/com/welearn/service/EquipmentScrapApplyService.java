package com.welearn.service;

import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.EquipmentScrapApply;
import com.welearn.entity.qo.equipment.EquipmentScrapApplyQueryCondition;
import com.welearn.entity.vo.response.equipment.EquipmentScrapInfo;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : EquipmentScrapApplyService Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface EquipmentScrapApplyService extends BaseService<EquipmentScrapApply, EquipmentScrapApplyQueryCondition>{
    /**
     * 根据条件查询设备报废信息
     * @param condition 查询条件
     * @return 设备报废申请和设备信息
     */
    List<EquipmentScrapInfo> searchInfo(EquipmentScrapApplyQueryCondition condition);

    /**
     * 当报废申请通过审批后执行的回调
     * @param contentId     报废申请id
     */
    void afterApplicationPass(@NotBlank String contentId);


    /**
     * 设备报废申请自动提交
     * @param equipmentScrapApply 设备报废申请
     * @return 申请信息
     */
    ApprovalApplication equipmentScrapApplyAutoSubmit(@NotNull @Valid EquipmentScrapApply equipmentScrapApply, @EntityCheck(checkId = true) User user);

    /**
     * 报废设备封存入库, 等待报废处理
     * @param equipmentId 报废设备id
     */
    void sealedStorage(@NotBlank String equipmentId);

    /**
     * 对报废设备进行报废处理
     * @param equipmentId 报废设备id
     */
    void scrapProcess(@NotBlank String equipmentId);
}