package com.welearn.entity.vo.response.apply;

import com.welearn.entity.po.apply.EquipmentYearPlanApplication;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Description :
 * Created by Setsuna Jin on 2018/10/30.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EquipmentYearPlanApplicationInfo extends EquipmentYearPlanApplication {
    private String companyId;
    private String applicantId;
    private String applicantName;
    private String departmentId;
    private String departmentName;
    private Integer applicationStatus;
    private String applicationId;
    private Date applicationApplyAt;
    private Integer year;
}
