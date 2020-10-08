package com.welearn.service.impl;

import com.welearn.application.ApplicationServiceImpl;
import com.welearn.dictionary.apply.ApplicationHandleTypeConst;
import com.welearn.dictionary.apply.ApprovalPointTypeConst;
import com.welearn.dictionary.apply.ApprovalProcessTypeConst;
import com.welearn.dictionary.apply.EquipmentBorrowApprovalTypeConst;
import com.welearn.dictionary.common.CompanyConfigConst;
import com.welearn.dictionary.common.PositionConst;
import com.welearn.dictionary.common.PositionTypeConst;
import com.welearn.dictionary.equipment.EquipmentBorrowStatusConst;
import com.welearn.entity.po.apply.ApprovalApplication;
import com.welearn.entity.po.apply.ApprovalProcess;
import com.welearn.entity.po.apply.ApprovalProcessPoint;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.common.Position;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.qo.common.PositionQueryCondition;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.PositionFeignClient;
import com.welearn.feign.equipment.EquipmentBorrowFeignClient;
import com.welearn.feign.equipment.EquipmentFeignClient;
import com.welearn.service.EquipmentBorrowApplyHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/27.
 */
@Service
public class EquipmentBorrowApplyHandlerServiceImpl extends ApplicationServiceImpl<EquipmentBorrow> implements EquipmentBorrowApplyHandlerService {

    @Autowired
    private EquipmentBorrowFeignClient equipmentBorrowFeignClient;

    @Autowired
    private EquipmentFeignClient equipmentFeignClient;


    /**
     * 获取审批流程节点
     *
     * @param process   审批流程
     * @param company 公司id
     * @param content   审批内容
     * @return 审批流程节点
     */
    @Override
    public List<ApprovalProcessPoint> getProcessPoint(ApprovalProcess process, Company company, EquipmentBorrow content) {
        ApprovalProcessPoint point = new ApprovalProcessPoint();
        point.setCompanyId(company.getId());
        point.setDepartmentId(content.getLoanOutDepartmentId());
        point.setDepartmentName(content.getLoanOutDepartmentName());
        point.setType(ApprovalPointTypeConst.POSITION_ANY.ordinal());
        point.setSort(0);
        point.setApprovalType(ApprovalProcessTypeConst.APPROVAL.ordinal());
        Integer type = company.getConfig(CompanyConfigConst.EQUIPMENT_BORROW_APPROVAL_TYPE);
        Position position = null;
        if (type == EquipmentBorrowApprovalTypeConst.DEPARTMENT_EMPLOYEE.ordinal())
            position = getSystemPositionByCode(PositionConst.HOSPITAL_EMPLOYEE.getCode());
        else if (type == EquipmentBorrowApprovalTypeConst.DEPARTMENT_SUPERVISOR.ordinal())
            position = getSystemPositionByCode(PositionConst.HOSPITAL_SUPERVISOR.getCode());
        if (Objects.nonNull(position)){
            point.setPositionId(position.getId());
            point.setPositionName(position.getName());
        }
        return Collections.singletonList(point);
    }

    /**
     * 创建申请内容
     *
     * @param content 申请内容
     */
    @Override @Transactional
    public void createContent(EquipmentBorrow content) {
        EquipmentBorrow equipmentBorrow = equipmentBorrowFeignClient.create(content).getData();
        content.setId(equipmentBorrow.getId());
        // 更新设备状态
        Equipment equipment = new Equipment();
        equipment.setId(equipmentBorrow.getEquipmentId());
        equipment.setBorrowStatus(EquipmentBorrowStatusConst.UN_APPROVAL.ordinal());
        equipmentFeignClient.update(equipment);
    }

    /**
     * 创建申请内容后调用, 可覆盖用来及时记录申请信息到content中
     *
     * @param content     申请内容
     * @param application 申请信息
     */
    @Override
    public void afterCreateContent(EquipmentBorrow content, ApprovalApplication application) {
        content.setBorrowApplicationId(application.getId());
        this.updateContent(content);
    }

    /**
     * 删除申请内容
     *
     * @param contentId 内容id
     */
    @Override
    public void deleteContent(String contentId) {
        equipmentBorrowFeignClient.delete(contentId);
    }

    /**
     * 更新申请内容
     *
     * @param content 申请内容
     */
    @Override
    public void updateContent(EquipmentBorrow content) {
        equipmentBorrowFeignClient.update(content);
    }

    /**
     * 根据申请获取申请内容
     *
     * @param contentId 内容id
     * @return 内容
     */
    @Override
    public EquipmentBorrow selectContent(String contentId) {
        return equipmentBorrowFeignClient.select(contentId).getData();
    }

    /**
     * 根据申请内容获取申请简述
     *
     * @param content 内容
     * @return 简述
     */
    @Override
    public String getOutlook(EquipmentBorrow content) {
        Equipment equipment = equipmentFeignClient.select(content.getEquipmentId()).getData();
        return String.format("%s 申请借用 %s-%s 设备", content.getBorrowDepartmentName(), content.getLoanOutDepartmentName(), equipment.getEquipmentTypeName());
    }

    /**
     * 获取申请类型编码
     *
     * @return 申请类型编码
     */
    @Override
    public ApplicationHandleTypeConst getApplicationType() {
        return ApplicationHandleTypeConst.EQUIPMENT_BORROW_APPLY;
    }

    /**
     * 当申请通过审批后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationPass(String applicationId, String contentId) throws Exception {
        equipmentBorrowFeignClient.afterBorrowApplicationPass(contentId);
    }

    /**
     * 当申请审批失败后执行的回调
     *
     * @param applicationId 申请id
     * @param contentId     申请内容id
     */
    @Override
    public void afterApplicationReject(String applicationId, String contentId) throws Exception {
        equipmentBorrowFeignClient.afterBorrowApplicationReject(contentId);
    }
}
