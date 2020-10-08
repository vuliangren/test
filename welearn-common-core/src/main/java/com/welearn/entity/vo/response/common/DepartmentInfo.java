package com.welearn.entity.vo.response.common;

import com.welearn.entity.po.common.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description :
 * Created by Setsuna Jin on 2018/9/15.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentInfo extends Department {
    private String companyAddress;
    private String buildingId;
    private String buildingName;
    private String floorName;
    private String adminName;
}
