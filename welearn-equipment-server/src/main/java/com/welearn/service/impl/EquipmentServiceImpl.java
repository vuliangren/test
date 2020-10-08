package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.welearn.dictionary.equipment.*;
import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.common.User;
import com.welearn.entity.po.equipment.*;
import com.welearn.entity.po.procurement.ProcurementDetail;
import com.welearn.entity.qo.equipment.EquipmentQueryCondition;
import com.welearn.entity.qo.equipment.SparePartTypeQueryCondition;
import com.welearn.entity.vo.response.common.LocationInfo;
import com.welearn.entity.vo.response.equipment.EquipmentInfo;
import com.welearn.entity.vo.response.equipment.EquipmentInitResult;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.common.CompanyFeignClient;
import com.welearn.feign.common.DepartmentFeignClient;
import com.welearn.feign.common.TagFeignClient;
import com.welearn.mapper.EquipmentMapper;
import com.welearn.mapper.EquipmentPermissionMapper;
import com.welearn.service.*;
import com.welearn.util.PaginateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description : EquipmentService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Slf4j
@Service
public class EquipmentServiceImpl extends BaseServiceImpl<Equipment,EquipmentQueryCondition,EquipmentMapper>
        implements EquipmentService{

    @Autowired
    private EquipmentProductService equipmentProductService;

    @Autowired
    private EquipmentAccessoryService equipmentAccessoryService;

    @Autowired
    private SparePartService sparePartService;

    @Autowired
    private SparePartTypeService sparePartTypeService;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    @Autowired
    private EquipmentPermissionService equipmentPermissionService;

    @Autowired
    private EquipmentPermissionMapper equipmentPermissionMapper;

    @Autowired
    private TagFeignClient tagFeignClient;

    @Override
    EquipmentMapper getMapper() {
        return equipmentMapper;
    }

    /**
     * 带检查的更新, 先根据id 获取设备
     * 比对新设备的 产品id 是否和旧设备的 产品id 一致
     * 如果 产品id 不一致则根据 新产品id 更新设备信息
     *
     * @param equipment 新设备信息
     */
    @Override
    public void updateWithCheck(Equipment equipment) {
        String productId = equipment.getEquipmentProductId();
        if (StringUtils.isNotBlank(productId)) {
            Equipment select = this.select(equipment.getId());
            if (!select.getEquipmentProductId().equals(productId)) {
                EquipmentProduct product = equipmentProductService.select(productId);
                if (Objects.isNull(product) || !product.getIsEnable())
                    throw new BusinessVerifyFailedException("产品类型 非法");
                equipment.updateWithProduct(product);
            }
        }
        this.update(equipment);
    }

    /**
     * 根据设备的权限信息, 查询设备当前的所在位置
     *
     * @param equipmentId 设备id
     * @return 设备的位置信息
     */
    @Override
    @Cacheable(value = "equipmentLocation", key = "'equipmentLocation:'+#equipmentId")
    public LocationInfo locationInfo(String equipmentId) {
        Equipment equipment = equipmentMapper.selectByPK(equipmentId);
        if (Objects.isNull(equipment) || !equipment.getIsEnable() || StringUtils.isBlank(equipment.getLocationId()))
            throw new BusinessVerifyFailedException("equipmentId 非法");
        return companyFeignClient.searchLocationInfo(equipment.getLocationId()).getData();
    }

    /**
     * 根据设备的权限信息, 查询设备当前的所在位置
     *
     * @param equipment 设备
     * @return 设备的位置信息
     */
    @Override
    @Cacheable(value = "equipmentLocation", key = "'equipmentLocation:'+#equipment.id")
    public LocationInfo locationInfo(Equipment equipment) {
        LocationInfo locationInfo = null;
        if (StringUtils.isNotBlank(equipment.getLocationId())) {
            locationInfo = companyFeignClient.searchLocationInfo(equipment.getLocationId()).getData();
        } else {
            locationInfo = this.locationInfo(equipment.getId());
        }
        return locationInfo;
    }

    /**
     * 根据 设备产品 初始化设备信息
     * @param productId 产品id
     * @param initCount 初始化数量
     * @param procurementId 采购项目id
     * @param detailId 采购详情id
     * @param guaranteeRepairExpiredAt 设备保修期限
     * @return EquipmentInitResult
     */
    @Override @Transactional
    public EquipmentInitResult productEquipmentInit(String productId, Integer initCount, String procurementId,
                                                    String detailId, Date guaranteeRepairExpiredAt, String companyId, String departmentId, String obtainReason){
        EquipmentProduct product = equipmentProductService.select(productId);
        if (Objects.isNull(product) || !product.getIsEnable())
            throw new BusinessVerifyFailedException("productId: %s 非法", productId);
        // 获取公司
        Company company = companyFeignClient.select(companyId).getData();
        if (Objects.isNull(company) || !company.getIsEnable())
            throw new BusinessVerifyFailedException("companyId: %s 非法", companyId);
        // 根据参数决定是否查询部门信息
        Department department = null;
        if (StringUtils.isNotBlank(departmentId)){
            department = departmentFeignClient.select(departmentId).getData();
            if (Objects.isNull(department) || !department.getCompanyId().equals(companyId) || !department.getIsEnable())
                throw new BusinessVerifyFailedException("departmentId: %s 非法", departmentId);
        }
        // 查询公司当前拥有的设备数量
        Integer currentEquipmentCount = equipmentPermissionMapper.selectRefEquipmentsCount(EquipmentPermissionCodeConst.OWNERSHIP.ordinal(), EquipmentPermissionTypeConst.COMPANY.ordinal(), companyId);

        // 设置默认的过期时间 为当前时间
        if (Objects.isNull(guaranteeRepairExpiredAt)){
            guaranteeRepairExpiredAt = new Date();
        }
        List<Equipment> equipments = new ArrayList<>();
        List<SparePart> spareParts = new ArrayList<>();
        List<EquipmentAccessory> accessories = new ArrayList<>();
        Date current = new Date();
        JSONArray sparePartList = JSON.parseArray(product.getSparePartsListJson());
        JSONArray accessoryList = JSON.parseArray(product.getAccessoryListJson());
        for (Integer i = 0; i < initCount; i++) {
            // 构建设备
            Equipment equipment = new Equipment();
            equipment.setUdi(String.format("#%06d", ++currentEquipmentCount));
            equipment.setSupplierId(product.getCompanyId());
            equipment.setCommodityName(product.getCommodityName());
            equipment.setProcurementId(procurementId);
            equipment.setProcurementDetailId(detailId);
            equipment.setEquipmentProductId(product.getId());
            // 导致数据量过大 且暂未用到此字段 故而不再进行设置
            // equipment.setEquipmentProductJson(JSON.toJSONString(product));
            equipment.setEquipmentTypeId(product.getEquipmentTypeId());
            equipment.setEquipmentTypeName(product.getEquipmentTypeName());
            equipment.setManagementLever(product.getManagementLever());
            equipment.setSpecification(product.getSpecification());
            equipment.setModelNumber(product.getModelNumber());
            equipment.setAcceptanceCheckedAt(current);
            equipment.setGuaranteeRepairExpiredAt(guaranteeRepairExpiredAt);
            equipment.setManufacturerName(product.getManufacturerName());
            equipment.setIsSterileProvide(product.getIsSterileProvide());
            equipment.setIsLargeEquipment(product.getIsLargeEquipment());
            equipment.setIsImportProduct(product.getIsImportProduct());
            this.create(equipment);
            equipments.add(equipment);
            // 创建设备的公司权限分配
            if (StringUtils.isNotBlank(companyId)){
                EquipmentPermission permission = new EquipmentPermission();
                permission.setCompanyId(company.getId());
                permission.setCompanyName(company.getName());
                permission.setEquipmentId(equipment.getId());
                permission.setType(EquipmentPermissionTypeConst.COMPANY.ordinal());
                if (StringUtils.isBlank(obtainReason))
                    permission.setObtainReason("医院根据 自行创建的设备产品 添加的设备");
                else
                    permission.setObtainReason(obtainReason);
                permission.setPermission(EquipmentPermissionCodeConst.OWNERSHIP.ordinal());
                equipmentPermissionService.allot(permission);
            }
            // 创建设备的部门权限分配
            if (Objects.nonNull(department)){
                EquipmentPermission permission = new EquipmentPermission();
                permission.setCompanyId(company.getId());
                permission.setCompanyName(company.getName());
                permission.setDepartmentId(departmentId);
                permission.setDepartmentName(department.getName());
                permission.setEquipmentId(equipment.getId());
                permission.setType(EquipmentPermissionTypeConst.DEPARTMENT.ordinal());
                if (StringUtils.isBlank(obtainReason))
                    permission.setObtainReason("医院根据 自行创建的设备产品 添加的设备");
                else
                    permission.setObtainReason(obtainReason);
                permission.setPermission(EquipmentPermissionCodeConst.MANAGEMENT.ordinal());
                equipmentPermissionService.allot(permission);
            }
            // 构建配件
            for (int j = 0; j < sparePartList.size(); j++) {
                JSONObject sparePart = sparePartList.getJSONObject(i);
                Integer count = sparePart.getInteger("count");
                // 确定是否需要创建 配件类型
                String typeName = String.format("%s[%s] - %s",equipment.getEquipmentTypeName(), equipment.getSpecification(), sparePart.getString("name"));
                String typeSpecification = sparePart.getString("specification");
                SparePartTypeQueryCondition typeQueryCondition = new SparePartTypeQueryCondition();
                typeQueryCondition.setName(typeName);
                typeQueryCondition.setSpecification(typeSpecification);
                List<SparePartType> types = sparePartTypeService.search(typeQueryCondition);
                SparePartType spt;
                if (Objects.isNull(types) || types.isEmpty()){
                    // 创建新的
                    spt = new SparePartType();
                    spt.setPriceType(SparePartPriceType.LOW.ordinal());
                    spt.setName(typeName);
                    spt.setSpecification(typeSpecification);
                    spt.setDescription("设备购买时附带备件");
                    spt.setRetailPurchases(count);
                    spt.setInTransitInCount(0);
                    spt.setInTransitOutCount(0);
                    spt.setConsumption(0);
                    spt.setRetailPurchases(0);
                    sparePartTypeService.create(spt);
                } else {
                    // 使用现有类型 并更新进货量
                    spt = types.get(0);
                    spt.setRetailPurchases(spt.getRetailPurchases() + count);
                    sparePartTypeService.update(spt);
                }
                // 添加配件批次信息
                SparePart sp = new SparePart();
                sp.setOrigin(SparePartOriginConst.ACCESSORY.ordinal());
                sp.setEquipmentId(equipment.getId());
                sp.setTypeId(spt.getId());
                sp.setPrice(0d);
                sp.setCount(count);
                sp.setRemain(count);
                sp.setManufacturerName(equipment.getManufacturerName());
                sp.setRemark(sparePart.getString("description"));
                sp.setProcurementId(procurementId);
                sparePartService.create(sp);
                spareParts.add(sp);
            }
            // 构建附件 0-操作附件 1-设备文档
            for (int j = 0; j < accessoryList.size(); j++) {
                JSONObject accessoryInfo = accessoryList.getJSONObject(i);
                Integer count = accessoryInfo.getInteger("count");
                Integer type = accessoryInfo.getInteger("type");
                for (Integer integer = 0; integer < count; integer++) {
                    EquipmentAccessory accessory = new EquipmentAccessory();
                    accessory.setEquipmentId(equipment.getId());
                    accessory.setType(type);
                    accessory.setCanBorrow(true);
                    accessory.setStatus(AccessoryStatusConst.UN_INIT.ordinal());
                    accessory.setName(accessoryInfo.getString("name"));
                    accessory.setSpecification(accessoryInfo.getString("specification"));
                    accessory.setDescription(accessoryInfo.getString("description"));
                    equipmentAccessoryService.create(accessory);
                    accessories.add(accessory);
                }
            }
        }
        return new EquipmentInitResult(equipments, spareParts, accessories);
    }


    /**
     * 查询设备详情信息
     *
     * @param condition 查询条件
     * @return List<EquipmentInfo>
     */
    @Override
    public List<EquipmentInfo> searchInfo(EquipmentQueryCondition condition) {
        if (Objects.isNull(condition.getPermissionCode()))
            condition.setPermissionCode(EquipmentPermissionCodeConst.OWNERSHIP.ordinal());
        // 如果存在按标签查询设备 则先查询标签关联的设备id 再查询设备
        if (Objects.nonNull(condition.getTagIds()) && !condition.getTagIds().isEmpty()) {
            List<String> equipmentIds = tagFeignClient.itemIds(condition.getTagIds()).getData();
            if (Objects.isNull(equipmentIds) || equipmentIds.isEmpty()) {
                return Collections.emptyList();
            } else {
                condition.setEquipmentIds(equipmentIds);
            }
        }
        return PaginateUtil.page(()->equipmentMapper.searchInfo(condition));
    }

    /**
     * 查询部门设备简述信息
     *
     * @param condition 查询条件
     * @return List<EquipmentInfo> 仅部分字段
     */
    @Override
    public List<EquipmentInfo> searchDepartmentEquipmentOutlook(EquipmentQueryCondition condition) {
        return PaginateUtil.page(()->equipmentMapper.searchDepartmentEquipment(condition));
    }

    /**
     * 采购项目验收通过后设备初始化构建
     *
     * @param details 采购项目详情
     * @return EquipmentInitResult
     */
    @Override @Transactional
    public EquipmentInitResult procurementEquipmentInit(List<ProcurementDetail> details) {
        List<Equipment> equipments = new ArrayList<>();
        List<SparePart> spareParts = new ArrayList<>();
        List<EquipmentAccessory> accessories = new ArrayList<>();

        for (ProcurementDetail detail : details) {
            // 初始化 创建设备
            EquipmentInitResult initResult = this.productEquipmentInit(detail.getProductId(), detail.getCount(),
                    detail.getProcurementId(), detail.getId(), new DateTime().plusMonths(detail.getGuaranteeTime()).toDate(),
                    detail.getCompanyId(), detail.getDepartmentId(), String.format("设备采购申请: %s 采购的设备", detail.getApplicantName()));
            equipments.addAll(initResult.getEquipments());
            spareParts.addAll(initResult.getSpareParts());
            accessories.addAll(initResult.getAccessories());
        }
        return new EquipmentInitResult(equipments, spareParts, accessories);
    }
}
