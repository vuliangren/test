package com.welearn.service.impl;

import com.welearn.dictionary.equipment.EquipmentPermissionCodeConst;
import com.welearn.dictionary.equipment.EquipmentPermissionTypeConst;
import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentPermission;
import com.welearn.entity.po.equipment.EquipmentProduct;
import com.welearn.entity.po.equipment.EquipmentTypeItem;
import com.welearn.entity.qo.equipment.EquipmentPermissionQueryCondition;
import com.welearn.entity.vo.response.common.LocationInfo;
import com.welearn.entity.vo.response.equipment.EquipmentInfo;
import com.welearn.entity.vo.response.equipment.EquipmentTypeItemInfo;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.CompanyFeignClient;
import com.welearn.feign.common.DepartmentFeignClient;
import com.welearn.mapper.EquipmentMapper;
import com.welearn.mapper.EquipmentPermissionMapper;
import com.welearn.service.EquipmentPermissionService;
import com.welearn.util.PaginateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.welearn.dictionary.equipment.EquipmentPermissionCodeConst.*;
import static com.welearn.dictionary.equipment.EquipmentPermissionTypeConst.*;

/**
 * Description : EquipmentPermissionService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class EquipmentPermissionServiceImpl extends BaseServiceImpl<EquipmentPermission,EquipmentPermissionQueryCondition,EquipmentPermissionMapper>
        implements EquipmentPermissionService{
    
    @Autowired
    private EquipmentPermissionMapper equipmentPermissionMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    EquipmentPermissionMapper getMapper() {
        return equipmentPermissionMapper;
    }

    /**
     * 分配设备权限
     *
     * @param equipmentPermission 设备权限
     */
    @Override
    public void allot(EquipmentPermission equipmentPermission) {
        // 验证是否已存在该权限
        Boolean isValid = false;
        switch (EquipmentPermissionTypeConst.values()[equipmentPermission.getType()]){
            case COMPANY:
                isValid = this.verify(equipmentPermission.getType(), equipmentPermission.getPermission(), equipmentPermission.getEquipmentId(), equipmentPermission.getCompanyId());
                break;
            case DEPARTMENT:
                isValid = this.verify(equipmentPermission.getType(), equipmentPermission.getPermission(), equipmentPermission.getEquipmentId(), equipmentPermission.getDepartmentId());
                break;
            case EMPLOYEE:
                isValid = this.verify(equipmentPermission.getType(), equipmentPermission.getPermission(), equipmentPermission.getEquipmentId(), equipmentPermission.getEmployeeId());
                break;
            default: throw new BusinessVerifyFailedException("equipmentPermission.type 非法");
        }
        if (isValid)
            throw new BusinessVerifyFailedException("equipmentPermission 已被分配, 请勿重复分配");
        if ((equipmentPermission.getPermission() <= MANAGEMENT.ordinal() && !equipmentPermission.getPermission().equals(equipmentPermission.getType())) ||
                (equipmentPermission.getPermission() >= USE.ordinal() && equipmentPermission.getType() != EquipmentPermissionTypeConst.EMPLOYEE.ordinal()))
            throw new BusinessVerifyFailedException("permission 与 type 不匹配");
        // 设备所有权同一时间只能存在一个, 管理权不做限制
        if (equipmentPermission.getPermission() == OWNERSHIP.ordinal()){
            EquipmentPermissionQueryCondition condition = new EquipmentPermissionQueryCondition();
            condition.setPermission(OWNERSHIP.ordinal());
            condition.setEquipmentId(equipmentPermission.getEquipmentId());
            condition.setIsEnable(true);
            List<EquipmentPermission> permissionList = this.search(condition);
            if (Objects.nonNull(permissionList) && !permissionList.isEmpty())
                throw new BusinessVerifyFailedException("同一时间只能由一个公司对该设备具有所有权");
        }
        // 验证设备是否合法
        Equipment equipment = equipmentMapper.selectByPK(equipmentPermission.getEquipmentId());
        if (Objects.isNull(equipment))
            throw new BusinessVerifyFailedException("equipmentId 非法");
        int type = equipmentPermission.getType();
        // 防止少设置参数
        if (type >= EMPLOYEE.ordinal()){
            if (StringUtils.isBlank(equipmentPermission.getEmployeeId()) || StringUtils.isBlank(equipmentPermission.getEmployeeName()))
                throw new BusinessVerifyFailedException("employee id 或 name 缺失");
        }
        if (type >= DEPARTMENT.ordinal()){
            if (StringUtils.isBlank(equipmentPermission.getDepartmentId()) || StringUtils.isBlank(equipmentPermission.getDepartmentName()))
                throw new BusinessVerifyFailedException("department id 或 name 缺失");
        }
        // 防止多设置参数
        if (type <= DEPARTMENT.ordinal()){
            equipmentPermission.setEmployeeId(null);
            equipmentPermission.setEmployeeName(null);
        }
        if (type <= COMPANY.ordinal()){
            equipmentPermission.setDepartmentId(null);
            equipmentPermission.setDepartmentName(null);
        }
        equipmentPermission.setObtainAt(new Date());
        equipmentPermission.setLoseAt(null);
        equipmentPermission.setLoseReason(null);
        this.create(equipmentPermission);
    }

    private void permissionLose(EquipmentPermission permission, Date now, String reason){
        permission.setLoseAt(now);
        permission.setLoseReason(reason);
        permission.setIsEnable(false);
        this.update(permission);
    }

    /**
     * 撤回设备权限
     *
     * @param equipmentPermissionId 设备权限id
     * @param loseReason            测绘原因
     */
    @Override @Transactional
    public void cancel(String equipmentPermissionId, String loseReason) {
        Date now = new Date();
        EquipmentPermission equipmentPermission = this.select(equipmentPermissionId);
        if (Objects.isNull(equipmentPermission))
            throw new BusinessVerifyFailedException("equipmentPermissionId 非法");
        this.permissionLose(equipmentPermission, now, loseReason);
        // 如果取消的是 使用权或维护权 则跳过后续流程
        if (equipmentPermission.getPermission() > MANAGEMENT.ordinal())
            return;
        // 判断取消的权限类型
        EquipmentPermissionQueryCondition condition = new EquipmentPermissionQueryCondition();
        condition.setEquipmentId(equipmentPermission.getEquipmentId());
        condition.setCompanyId(equipmentPermission.getCompanyId());
        condition.setIsEnable(true);
        EquipmentPermissionCodeConst permissionCode = EquipmentPermissionCodeConst.values()[equipmentPermission.getPermission()];
        String reason = loseReason;
        List<EquipmentPermission> permissions;
        switch (permissionCode){
            case OWNERSHIP:
                // 所有权丢失后 其余的权限均取消
                permissions = this.search(condition);
                reason += "因公司对该设备的所有权被取消 连带取消该权限, 详请: ";
                for (EquipmentPermission permission : permissions) {
                    this.permissionLose(permission, now, reason);
                }
                break;
            case MANAGEMENT:
                // 管理权丢失后 部门的使用权均取消
                condition.setDepartmentId(equipmentPermission.getDepartmentId());
                condition.setPermission(USE.ordinal());
                permissions = this.search(condition);
                reason += "因部门对该设备的管理权被取消 连带取消该权限, 详请: ";
                for (EquipmentPermission permission : permissions) {
                    this.permissionLose(permission, now, reason);
                }
                break;
        }
    }

    /**
     * 查询权限关联的设备
     *
     * @param permissionCode 权限编码
     * @param type           关联类型
     * @param typeId         关联id
     * @return 设备列表
     */
    @Override
    public List<Equipment> selectRefEquipments(Integer permissionCode, Integer type, String typeId) {
        return PaginateUtil.page(()->equipmentPermissionMapper.selectRefEquipments(permissionCode, type, typeId));
    }

    /**
     * 查询权限关联的设备产品
     *
     * @param permissionCode 权限编码
     * @param type           关联类型
     * @param typeId         关联id
     * @return 设备产品列表
     */
    @Override
    public List<EquipmentProduct> selectRefEquipmentProducts(Integer permissionCode, Integer type, String typeId) {
        return PaginateUtil.page(()->equipmentPermissionMapper.selectRefEquipmentProducts(permissionCode, type, typeId));
    }

    /**
     * 查询权限关联的设备类型
     *
     * @param permissionCode 权限编码
     * @param type           关联类型
     * @param typeId         关联id
     * @return 设备类型列表
     */
    @Override
    public List<EquipmentTypeItemInfo> selectRefEquipmentTypes(Integer permissionCode, Integer type, String typeId) {
        return PaginateUtil.page(()->equipmentPermissionMapper.selectRefEquipmentTypes(permissionCode, type, typeId));
    }

    /**
     * 验证 type 对应的 id 是否具备 equipmentId 对应设备的 permissionCode 权限
     *
     * @param type           设备权限类型
     * @param permissionCode 权限编码
     * @param equipmentId    设备id
     * @param typeId         类型关联的id
     * @return 是否具备权限
     */
    @Override
    public Boolean verify(Integer type, Integer permissionCode, String equipmentId, String typeId) {
        EquipmentPermissionQueryCondition condition = new EquipmentPermissionQueryCondition();
        condition.setType(type);
        condition.setPermission(permissionCode);
        condition.setEquipmentId(equipmentId);
        condition.setIsEnable(true);
        switch (EquipmentPermissionTypeConst.values()[type]){
            case COMPANY:
                condition.setCompanyId(typeId);
                break;
            case DEPARTMENT:
                condition.setDepartmentId(typeId);
                break;
            case EMPLOYEE:
                condition.setEmployeeId(typeId);
                break;
            default:
                throw new BusinessVerifyFailedException("type 非法");
        }
        List<EquipmentPermission> searchResult = this.search(condition);
        return Objects.nonNull(searchResult) && !searchResult.isEmpty();
    }
}
