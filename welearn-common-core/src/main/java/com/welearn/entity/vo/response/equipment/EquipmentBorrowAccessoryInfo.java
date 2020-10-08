package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.EquipmentBorrow;
import com.welearn.entity.po.equipment.EquipmentBorrowAccessory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description : 设备借用附件详情
 * Created by Setsuna Jin on 2019/3/26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentBorrowAccessoryInfo extends EquipmentBorrowAccessory {
    private Integer type;
    private Boolean canBorrow;
    private Integer accessoryStatus;
    private String name;
    private String specification;
    private String description;
    private String photos;
    private String remark;
}
