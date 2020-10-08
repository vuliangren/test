package com.welearn.service;

import com.welearn.entity.po.common.Company;
import com.welearn.entity.po.common.Department;
import com.welearn.entity.po.common.User;
import com.welearn.entity.qo.common.CompanyQueryCondition;
import com.welearn.entity.vo.response.common.LocationInfo;
import com.welearn.validate.annotation.common.EntityCheck;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description : Service Interface
 * Created by Setsuna Jin on 2018/1/8.
 */
@Validated
public interface CompanyService extends BaseService<Company, CompanyQueryCondition>{

    /**
     * 根据id 获取位置信息
     * @param locationId 建筑id / 楼层id / 房间id
     * @return LocationInfo
     */
    LocationInfo searchLocationInfo(String locationId);

    /**
     * 创建公司, 并创建默认部门 和 公司管理员
     * @param company 公司
     * @param department 部门
     * @param admin 管理员
     * @param roleIds 管理员拥有角色
     * @param positionIds 管理员拥有职位
     * @param operator 操作人员
     */
    void createAndInit(@NotNull Company company, @NotNull Department department, @NotNull User admin,
                       @NotEmpty List<String> roleIds, @NotNull List<String> positionIds, @EntityCheck(checkId = true) User operator);
}