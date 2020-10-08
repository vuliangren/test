package com.welearn.service.impl;

import com.welearn.dictionary.equipment.MaintenanceServeTypeConst;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.po.equipment.MaintenanceMethod;
import com.welearn.entity.qo.equipment.MaintenanceMethodQueryCondition;
import com.welearn.entity.vo.response.equipment.MaintenanceRecordDetail;
import com.welearn.entity.vo.response.equipment.TaskAssignmentDetail;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.MaintenanceMethodMapper;
import com.welearn.service.EquipmentProductService;
import com.welearn.service.EquipmentService;
import com.welearn.service.MaintenanceMethodService;
import com.welearn.util.PaginateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Description : MaintenanceMethodService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class MaintenanceMethodServiceImpl extends BaseServiceImpl<MaintenanceMethod,MaintenanceMethodQueryCondition,MaintenanceMethodMapper>
        implements MaintenanceMethodService{
    
    @Autowired
    private MaintenanceMethodMapper maintenanceMethodMapper;

    @Autowired
    private EquipmentProductService equipmentProductService;

    @Autowired
    private EquipmentService equipmentService;

    @Override
    MaintenanceMethodMapper getMapper() {
        return maintenanceMethodMapper;
    }

    /**
     * 查找设备拥有的维护方式
     *
     * @param equipmentId 设备id
     * @return 维护方式列表
     */
    @Override
    public List<MaintenanceMethod> selectByEquipmentId(String equipmentId, String companyId) {
        Equipment equipment = equipmentService.select(equipmentId);
        if (Objects.isNull(equipment))
            throw new BusinessVerifyFailedException("equipmentProductId 非法");
        // 查询产品和类型以及全部相关的
        List<MaintenanceMethod> serveProductAndTypeAndAllResults = this.selectByEquipmentProductId(equipment.getEquipmentProductId(), companyId);
        // 查询设备相关的
        MaintenanceMethodQueryCondition condition = new MaintenanceMethodQueryCondition();
        condition.setIsEnable(true);
        condition.setCompanyId(companyId);
        condition.setServeType(MaintenanceServeTypeConst.EQUIPMENT.ordinal());
        condition.setServeId(equipmentId);
        List<MaintenanceMethod> serveEquipmentResults = this.search(condition);
        serveEquipmentResults.addAll(serveProductAndTypeAndAllResults);
        return serveEquipmentResults;
    }

    /**
     * 查找产品拥有的维护方式
     *
     * @param equipmentProductId 产品id
     * @return 维护方式列表
     */
    @Override
    public List<MaintenanceMethod> selectByEquipmentProductId(String equipmentProductId, String companyId) {
        EquipmentProduct equipmentProduct = equipmentProductService.select(equipmentProductId);
        if (Objects.isNull(equipmentProduct))
            throw new BusinessVerifyFailedException("equipmentProductId 非法");
        // 查询类型和全部相关的
        List<MaintenanceMethod> serveTypeAndAllResults = this.selectByEquipmentTypeId(equipmentProduct.getEquipmentTypeId(), companyId);
        // 查询产品相关的
        MaintenanceMethodQueryCondition condition = new MaintenanceMethodQueryCondition();
        condition.setIsEnable(true);
        condition.setCompanyId(companyId);
        condition.setServeType(MaintenanceServeTypeConst.EQUIPMENT_PRODUCT.ordinal());
        condition.setServeId(equipmentProductId);
        List<MaintenanceMethod> serveProductResults = this.search(condition);
        serveProductResults.addAll(serveTypeAndAllResults);
        return serveProductResults;
    }

    /**
     * 查找设备类型拥有的维护方式
     *
     * @param equipmentTypeId 设备类型id
     * @return 维护方式列表
     */
    @Override
    public List<MaintenanceMethod> selectByEquipmentTypeId(String equipmentTypeId, String companyId) {
        MaintenanceMethodQueryCondition condition = new MaintenanceMethodQueryCondition();
        condition.setIsEnable(true);
        condition.setCompanyId(companyId);
        condition.setServeType(MaintenanceServeTypeConst.ALL.ordinal());
        List<MaintenanceMethod> serveAllResults = this.search(condition);
        condition.setServeType(MaintenanceServeTypeConst.EQUIPMENT_TYPE.ordinal());
        condition.setServeId(equipmentTypeId);
        List<MaintenanceMethod> serveTypeResults = this.search(condition);
        serveTypeResults.addAll(serveAllResults);
        return serveTypeResults;
    }

    /**
     * 根据维护任务id查询其关联的维护方式
     *
     * @param taskId 维护任务id
     * @return 维护方式列表
     */
    @Override
    public List<MaintenanceMethod> selectByTaskId(String taskId) {
        return maintenanceMethodMapper.selectByTaskId(taskId);
    }

    /**
     * 根据维护任务分配id查询维护任务分配详情
     *
     * @param assignmentId 维护任务分配id
     * @return 任务分配详情列表
     */
    @Override
    public List<TaskAssignmentDetail> selectTaskAssignmentDetail(String assignmentId) {
        return maintenanceMethodMapper.selectByAssignmentId(assignmentId);
    }

    /**
     * 根据维护记录id查询维护记录详情
     *
     * @param recordId 维护记录id
     * @return 维护记录详情列表
     */
    @Override
    public List<MaintenanceRecordDetail> selectMaintenanceRecordDetail(String recordId) {
        return maintenanceMethodMapper.selectByRecordId(recordId);
    }
}
