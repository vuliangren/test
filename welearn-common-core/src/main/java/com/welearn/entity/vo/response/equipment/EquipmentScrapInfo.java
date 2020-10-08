package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.po.equipment.EquipmentScrapApply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description : 设备报废详情
 * Created by Setsuna Jin on 2019/3/26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentScrapInfo extends EquipmentScrapApply {

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
