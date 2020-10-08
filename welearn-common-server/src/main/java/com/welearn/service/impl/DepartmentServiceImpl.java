package com.welearn.service.impl;

import com.alibaba.fastjson.JSON;
import com.welearn.dictionary.common.*;
import com.welearn.entity.po.common.*;
import com.welearn.entity.qo.common.DepartmentQueryCondition;
import com.welearn.entity.qo.common.PositionQueryCondition;
import com.welearn.entity.qo.common.ReUserDepartmentQueryCondition;
import com.welearn.entity.qo.common.ReUserPositionQueryCondition;
import com.welearn.entity.vo.response.common.DepartmentInfo;
import com.welearn.entity.vo.response.common.DepartmentUserCount;
import com.welearn.error.exception.BusinessVerifyFailedException;
import com.welearn.mapper.DepartmentMapper;
import com.welearn.mapper.ReUserDepartmentMapper;
import com.welearn.service.*;
import com.welearn.util.PaginateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description : DepartmentService Implement
 * Created by Setsuna Jin on 2018/1/8.
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,DepartmentQueryCondition,DepartmentMapper>
        implements DepartmentService{
    
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private ReUserDepartmentMapper reUserDepartmentMapper;

    @Autowired
    private FloorService floorService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CompanyService companyService;

    @Override
    DepartmentMapper getMapper() {
        return departmentMapper;
    }

    /**
     * 创建部门
     * 部门主管 / 部门成员
     *
     * @param department 部门信息
     * @return 带Id的部门信息
     */
    @Override
    public Department createAndInitPosition(Department department, User user) throws BusinessVerifyFailedException {
        Company company = companyService.select(department.getCompanyId());
        if (Objects.isNull(company))
            throw new BusinessVerifyFailedException("所传参数不符合接口的业务要求");
        // 检验部门创建时 tag 是否已经存在 TODO: 待更新为使用 Tag 标签进行查询和验证
//        Map<String, DepartmentTagConst> tags = DepartmentTagConst.search(CompanyTypeConst.get(company.getType()));
//        DepartmentTagConst departmentTagConst = tags.get(department.getTags());
//        if (departmentTagConst.getLimit() > 0){
//            DepartmentQueryCondition condition = new DepartmentQueryCondition();
//            condition.setCompanyId(company.getId());
//            condition.setTags(department.getTags());
//            condition.setIsEnable(true);
//            List<Department> departments = this.search(condition);
//            if (departments.size() >= departmentTagConst.getLimit())
//                throw new BusinessVerifyFailedException("部门创建失败! 部门标签:%s 限制数量为: %d 个, 目前已达到限制数量, 请更换标签后重试",
//                        departmentTagConst.getDescription(), departmentTagConst.getLimit());
//        }
        department.setCreatorId(user.getId());
        this.create(department);
        return department;
    }

    /**
     * 分页获取部门信息
     *
     * @param companyId 公司id
     * @param departmentName 部门名称模糊查询
     * @param tagIds 相关标签
     * @return 部门信息列表
     */
    @Override
    public List<DepartmentInfo> searchInfo(String companyId, String departmentName, List<String> tagIds) {
        return PaginateUtil.page(()->departmentMapper.searchInfo(companyId, departmentName, tagIds));
    }

    /**
     * 获取部门的详细位置
     * @param departmentId 部门id
     * @return 部门详细位置
     */
    @Override
    @Deprecated
    public String position(String departmentId){
        Department department = this.select(departmentId);
        if (Objects.isNull(department))
            throw new BusinessVerifyFailedException("departmentId 非法");
        Company company = companyService.select(department.getCompanyId());
        if (Objects.isNull(company))
            throw new BusinessVerifyFailedException("department.companyId 非法");
        Floor floor = floorService.select(department.getFloorId());
        if (Objects.isNull(floor))
            throw new BusinessVerifyFailedException("department.floorId 非法");
        Building building = buildingService.select(floor.getBuildingId());
        if (Objects.isNull(building))
            throw new BusinessVerifyFailedException("department.floor.buildingId 非法");
        String companyAddress = "-";
        if (StringUtils.isNotBlank(company.getAddressId())){
            Address address = addressService.select(company.getAddressId());
            if (Objects.nonNull(address)){
                companyAddress = String.format("%s %s", address.getAddressPrefix(), address.getAddressDetail());
            }
        }
        // 楼 - 层 - 部门 / 公司 / 地址
        return String.format("%s - %s - %s / %s / %s",
                building.getName(),
                floor.getName(),
                department.getName(),
                company.getName(),
                companyAddress);
    }

    /**
     * 用户所在的部门列表
     *
     * @param userId 用户id
     * @return 部门列表
     */
    @Override
    public List<Department> userDepartments(String userId) {
        return departmentMapper.userDepartments(userId);
    }

    /**
     * 统计各个部门的人数信息
     *
     * @param companyId 公司id
     * @return Map<departmentId, userCount>
     */
    @Override
    public List<DepartmentUserCount> userCount(String companyId) {
        return departmentMapper.userCount(companyId);
    }
}
