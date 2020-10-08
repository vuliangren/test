package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : 设备详情
 * Created by Setsuna Jin on 2019/1/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentInfo extends Equipment {
    // EquipmentPermission 相关信息
    private Date obtainAt;
    private String obtainReason;
    private String companyId;
    private String companyName;
    private String departmentId;
    private String departmentName;
    private String employeeId;
    private String employeeName;


}
