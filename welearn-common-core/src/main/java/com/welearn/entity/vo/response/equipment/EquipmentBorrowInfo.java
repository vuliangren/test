package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentBorrow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description : 设备借用详情
 * Created by Setsuna Jin on 2019/3/26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentBorrowInfo extends EquipmentBorrow {

    private String equipmentUdi;
    private String equipmentTypeName;
    private Integer managementLever;
    private String specification;
    private String modelNumber;
    private String manufacturerName;
    private Integer equipmentStatus;
    private Integer functionStatus;
    private Integer borrowStatus;
    private Integer adjustStatus;
    private Integer repairStatus;
    private Integer maintainStatus;
    private Integer scrapStatus;
}
