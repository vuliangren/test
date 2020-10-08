package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.Equipment;
import com.welearn.entity.po.equipment.EquipmentAccessory;
import com.welearn.entity.po.equipment.SparePart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description : 设备采购初始化响应
 * Created by Setsuna Jin on 2018/12/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentInitResult {
    private List<Equipment> equipments;
    private List<SparePart> spareParts;
    private List<EquipmentAccessory> accessories;
}
