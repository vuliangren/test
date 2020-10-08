package com.welearn.service.impl;

import com.welearn.dictionary.apply.ApplicationTypeConst;
import com.welearn.dictionary.equipment.*;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentPermission;
import com.welearn.entity.po.equipment.EquipmentScrapApply;
import com.welearn.entity.qo.equipment.EquipmentPermissionQueryCondition;
import com.welearn.entity.qo.equipment.EquipmentScrapApplyQueryCondition;
import com.welearn.entity.vo.request.apply.Apply;
import com.welearn.entity.vo.response.equipment.EquipmentScrapInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.apply.EquipmentScrapApplyFeignClient;
import com.welearn.mapper.EquipmentPermissionMapper;
import com.welearn.mapper.EquipmentScrapApplyMapper;
import com.welearn.service.EquipmentScrapApplyService;
import com.welearn.service.EquipmentService;
import com.welearn.util.PaginateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Description : EquipmentScrapApplyService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentScrapApplyServiceImpl extends BaseServiceImpl<EquipmentScrapApply,EquipmentScrapApplyQueryCondition,EquipmentScrapApplyMapper>
        implements EquipmentScrapApplyService{

    @Autowired
    private EquipmentPermissionMapper equipmentPermissionMapper;

    @Autowired
    private EquipmentScrapApplyMapper equipmentScrapApplyMapper;

    @Autowired
    private EquipmentScrapApplyFeignClient equipmentScrapApplyFeignClient;

    @Autowired
    private EquipmentService equipmentService;

    @Override
    EquipmentScrapApplyMapper getMapper() {
        return equipmentScrapApplyMapper;
    }

    /**
     * 根据条件查询设备报废信息
     *
     * @param condition 查询条件
     * @return 设备报废申请和设备信息
     */
    @Override
    public List<EquipmentScrapInfo> searchInfo(EquipmentScrapApplyQueryCondition condition) {
        return PaginateUtil.page(() -> equipmentScrapApplyMapper.searchInfo(condition));
    }

    /**
     * 当报废申请通过审批后执行的回调
     *
     * @param contentId 报废申请id
     */
    @Override @Transactional
    public void afterApplicationPass(String contentId) {
        EquipmentScrapApply scrapApply = this.select(contentId);
        if (Objects.isNull(scrapApply) || scrapApply.getStatus() != EquipmentScrapApplyStatusConst.UN_APPROVAL.ordinal())
            throw new BusinessVerifyFailedException("id 非法 或 状态异常");
        Equipment equipment = equipmentService.select(scrapApply.getEquipmentId());
        if (Objects.isNull(equipment) || equipment.getScrapStatus() != EquipmentScrapStatusConst.UN_APPROVAL.ordinal())
            throw new BusinessVerifyFailedException("equipmentId 非法 或 状态异常");
        equipment.setScrapStatus(EquipmentScrapStatusConst.UN_SEAL.ordinal());
        equipmentService.update(equipment);
        scrapApply.setStatus(EquipmentScrapApplyStatusConst.SUCCESS.ordinal());
        this.update(scrapApply);
    }

    /**
     * 设备报废申请自动提交
     *
     * @param equipmentScrapApply 设备报废申请
     * @return 申请信息
     */
    @Override
    public ApprovalApplication equipmentScrapApplyAutoSubmit(EquipmentScrapApply equipmentScrapApply, User user) {
        this.create(equipmentScrapApply);
        // 修改设备报废状态
        Equipment equipment = equipmentService.select(equipmentScrapApply.getEquipmentId());
        if (Objects.isNull(equipment) || equipment.getScrapStatus() != EquipmentScrapStatusConst.OK.ordinal())
            throw new BusinessVerifyFailedException("equipmentId 非法 或 状态异常");
        // 查询设备所属部门
        EquipmentPermissionQueryCondition condition = new EquipmentPermissionQueryCondition();
        condition.setIsEnable(true);
        condition.setEquipmentId(equipmentScrapApply.getEquipmentId());
        condition.setType(EquipmentPermissionTypeConst.DEPARTMENT.ordinal());
        condition.setPermission(EquipmentPermissionCodeConst.MANAGEMENT.ordinal());
        List<EquipmentPermission> equipmentPermissions = equipmentPermissionMapper.selectByCondition(condition);
        if (Objects.nonNull(equipmentPermissions) && !equipmentPermissions.isEmpty()){
            equipmentScrapApply.setEquipmentDepartmentId(equipmentPermissions.get(0).getDepartmentId());
        }
        // 系统自动提交申请
        Apply<EquipmentScrapApply> request = new Apply<>();
        request.setContent(equipmentScrapApply);
        request.setApplicantId(user.getId());
        request.setType(ApplicationTypeConst.FORM_APPLICATION.ordinal());
        ApprovalApplication application = equipmentScrapApplyFeignClient.apply(request).getData();
        // 更新设备状态
        equipment.setScrapStatus(EquipmentScrapStatusConst.UN_APPROVAL.ordinal());
        equipmentService.update(equipment);
        return application;
    }

    /**
     * 报废设备封存入库, 等待报废处理
     * @param equipmentId 报废设备
     */
    @Override
    public void sealedStorage(String equipmentId) {
        // 修改设备报废状态
        Equipment equipment = equipmentService.select(equipmentId);
        if (Objects.isNull(equipment) || equipment.getScrapStatus() != EquipmentScrapStatusConst.UN_SEAL.ordinal())
            throw new BusinessVerifyFailedException("equipmentId 非法 或 状态异常");
        equipment.setScrapStatus(EquipmentScrapStatusConst.UN_PROCESS.ordinal());
        equipmentService.update(equipment);
    }

    /**
     * 对报废设备进行报废处理
     *
     * @param equipmentId 报废设备id
     */
    @Override
    public void scrapProcess(String equipmentId) {
        // 修改设备报废状态
        Equipment equipment = equipmentService.select(equipmentId);
        if (Objects.isNull(equipment) || equipment.getScrapStatus() != EquipmentScrapStatusConst.UN_PROCESS.ordinal())
            throw new BusinessVerifyFailedException("equipmentId 非法 或 状态异常");
        equipment.setScrapStatus(EquipmentScrapStatusConst.OK.ordinal());
        equipment.setEquipmentStatus(EquipmentStatusConst.SCRAP.ordinal());
        equipmentService.update(equipment);
    }


}
