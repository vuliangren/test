package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.common.CompanyConfigConst;
import com.welearn.dictionary.common.CompanyTypeConst;
import com.welearn.dictionary.common.PersistantConst;
import com.welearn.dictionary.equipment.MaintenanceServeTypeConst;
import com.welearn.entity.dto.common.IdAndName;
import com.welearn.entity.po.common.*;
import com.welearn.entity.po.equipment.RepairPrecept;
import com.welearn.entity.qo.common.CompanyQueryCondition;
import com.welearn.entity.vo.response.common.LocationInfo;
import com.welearn.entity.vo.response.common.Option;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.feign.equipment.RepairPreceptFeignClient;
import com.welearn.mapper.*;
import com.welearn.service.CompanyService;
import com.welearn.service.UserService;
import com.welearn.util.UUIDGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Description : CompanyService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company,CompanyQueryCondition,CompanyMapper>
        implements CompanyService{
    
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private FloorMapper floorMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RepairPreceptFeignClient repairPreceptFeignClient;

    @Override
    CompanyMapper getMapper() {
        return companyMapper;
    }

    @Override
    public Company create(Company company){
        // 设置默认的配置文件内容
        Map<String, Object> defaultConfig = CompanyConfigConst.getDefaultConfig(CompanyTypeConst.values()[company.getType()]);
        company.setConfigJson(JSON.toJSONString(defaultConfig));
        company = super.create(company);
        // 根据公司类型决定后续操作
        if (company.getType() == CompanyTypeConst.HOSPITAL.ordinal()){
            this.afterCreateHospital(company);
        }
        return company;
    }

    /**
     * 创建完医院后需要对医院进行的初始化操作
     * @param company 医院
     */
    private void afterCreateHospital(Company company){
        // 设备报修预案 中 需要添加 默认的预案内容 > 其它
        RepairPrecept precept = new RepairPrecept();
        precept.setServeType(MaintenanceServeTypeConst.ALL.ordinal());
        precept.setCompanyId(company.getId());
        precept.setServeName("所有设备");
        precept.setOutlook("其它问题");
        precept.setDetail("设备报修时默认选择的报修预案");
        repairPreceptFeignClient.create(precept);
    }

    /**
     * 创建公司, 并创建默认部门 和 公司管理员
     * @param company 公司
     * @param department 部门
     * @param admin 管理员
     * @param roleIds 管理员拥有角色
     * @param positionIds 管理员拥有职位
     * @param operator 操作人员
     */
    @Override
    @Transactional
    public void createAndInit(Company company, Department department, User admin, List<String> roleIds, List<String> positionIds, User operator) {
        String departmentId = UUIDGenerator.get(Department.class);
        String userId = UUIDGenerator.get(User.class);
        // 创建公司
        company = this.create(company);
        // 创建部门
        department.setId(departmentId);
        department.setAdminId(userId);
        department.setCompanyId(company.getId());
        department.setCreatorId(operator.getId());
        department.setIsEnable(true);
        department.setState(0);
        department.setCreatedAt(new Date());
        department.setUpdatedAt(new Date());
        departmentMapper.insert(department);
        // 用户入职
        admin.setCompanyId(company.getId());
        admin.setDepartmentId(departmentId);
        userService.entry(admin, roleIds, positionIds, operator);
    }

    private void updateLocationFloorInfo(LocationInfo locationInfo, String floorId) {
        Floor floor = floorMapper.selectByPK(floorId);
        if (Objects.nonNull(floor)) {
            locationInfo.setFloor(new IdAndName(floor.getId(), floor.getName()));
            if (StringUtils.isNotBlank(floor.getBuildingId()))
                this.updateLocationBuildingInfo(locationInfo, floor.getBuildingId());
        }
    }

    private void updateLocationBuildingInfo(LocationInfo locationInfo, String buildingId) {
        Building building = buildingMapper.selectByPK(buildingId);
        if (Objects.nonNull(building)){
            locationInfo.setBuilding(new IdAndName(building.getId(), building.getName()));
            if (StringUtils.isNotBlank(building.getCompanyId()))
                this.updateLocationCompanyInfo(locationInfo, building.getCompanyId());
        }
    }

    private void updateLocationCompanyInfo(LocationInfo locationInfo, String companyId) {
        Company company = companyMapper.selectByPK(companyId);
        if (Objects.nonNull(company)) {
            locationInfo.setCompany(new IdAndName(company.getId(), company.getName()));
            if (StringUtils.isNotBlank(company.getAddressId())){
                Address address = addressMapper.selectByPK(company.getAddressId());
                if (Objects.nonNull(address))
                    locationInfo.setAddress(new IdAndName(address.getId(), String.format("%s %s", address.getAddressPrefix(), address.getAddressDetail())));
            }
        }
    }

    /**
     * 根据id 获取位置信息
     *
     * @param locationId 建筑id / 楼层id / 房间id
     * @return LocationInfo
     */
    @Override
    @Cacheable(value = "location", key = "'location:'+#locationId")
    public LocationInfo searchLocationInfo(String locationId) {
        LocationInfo locationInfo = new LocationInfo();
        PersistantConst type = PersistantConst.getByUUID(locationId);
        switch (type) {
            case Building:
                this.updateLocationBuildingInfo(locationInfo, locationId);
                break;
            case Floor:
                this.updateLocationFloorInfo(locationInfo, locationId);
                break;
            case Room:
                Room room = roomMapper.selectByPK(locationId);
                if (Objects.nonNull(room)){
                    locationInfo.setRoom(new IdAndName(room.getId(), room.getName()));
                    if (StringUtils.isNotBlank(room.getFloorId()))
                        this.updateLocationFloorInfo(locationInfo, room.getFloorId());
                }
                break;
             default:
                 throw new BusinessVerifyFailedException("locationId 类型非法, 不属于( 建筑 / 楼层 / 房间 )");
        }
        // 生成地址格式化字符串
        locationInfo.setOutlook(locationInfo.toString());
        return locationInfo;
    }
}
