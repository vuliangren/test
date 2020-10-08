package com.welearn.entity.vo.response.equipment;

import com.welearn.entity.po.equipment.EquipmentTypeItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Description :
 * Created by Setsuna Jin on 2019/3/28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentTypeItemInfo extends EquipmentTypeItem {
    private String dSort;
    private String uSort;
    private String description;
    private String usage;
}
